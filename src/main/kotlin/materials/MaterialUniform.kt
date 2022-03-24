package materials

import math.Point
import scene.lights.Light
import kotlin.math.max
import kotlin.math.pow

class MaterialUniform(
    private val diffuse: Point,
    private val reflectivity: Float = 0.5f,
    private val ambient: Point = diffuse * 0.1f,
    private val specular: Point = Point.ones(),
    private val shininess: Float = 100f
): Material {
    private val color = Point()

    override fun getColor(point: Point, normal: Point, toLight: Point, toCamera: Point, light: Light): Point {
        color.set(0f, 0f, 0f)
        val diffuseFactor = toLight.dot(normal)
        if (diffuseFactor < 0)
            return color

        color.set(color + diffuse * light.intensityDiffuse * max(0f, diffuseFactor)) //Diffusion
        color.set(color + ambient * light.intensityAmbient) //Ambience
        color.set(color + specular * light.intensitySpecular * max(0f, ((toCamera + toLight).normalized()).dot(normal).pow(shininess / 4f))) //Specularity
        return color
    }

    override fun getReflectivity(point: Point): Float {
        return reflectivity
    }
}
