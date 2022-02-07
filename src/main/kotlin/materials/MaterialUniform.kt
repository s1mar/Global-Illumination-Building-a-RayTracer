package materials

import math.Point
import scene.lights.Light
import kotlin.math.max

class MaterialUniform(
    private val diffuse: Point,
    private val reflectivity: Float = 0.5f
): Material {
    private val color = Point()

    override fun getColor(point: Point, normal: Point, toLight: Point, toCamera: Point, light: Light): Point {
        color.set(0f, 0f, 0f)
        val diffuseFactor = toLight.dot(normal)
        if (diffuseFactor < 0)
            return color

        // diffuse
        color.set(color + diffuse * light.intensityDiffuse * max(0f, diffuseFactor))
        return color
    }

    override fun getReflectivity(point: Point): Float {
        return reflectivity
    }
}
