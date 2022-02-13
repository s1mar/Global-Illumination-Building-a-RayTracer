import math.Point
import materials.MaterialUniform
import materials.MaterialWithChecks
import objects.Cone
import objects.Plane
import objects.Sphere
import scene.Camera
import scene.Scene
import scene.lights.LightPoint
import java.io.File

val IMG_OUTPUT_PATH = "output/snapshot/"

fun main() {

    IMG_OUTPUT_PATH.also { File(it).mkdirs()
        createScene().renderToFile("$IMG_OUTPUT_PATH{System.currentTimeMillis()}.png") {
            println("$it%")
        }
    }

}

fun createScene(): Scene {
    return Scene(
        camera = Camera(
            position = Point(-2f, 2f, 3f),
            lookAt = Point(-0.8f, 1f, 0f),
            planeNormal = Point.yUnit(),
            width = 1920,
            height = 1080
        ),
        lights = listOf(
            LightPoint(Point(2f, 2f, 4f), intensityDiffuse = Point(2.0f))
        ),
        objects = listOf(
            Sphere(Point(-1f, 1.0f, 0f), 0.6f, MaterialUniform(Point(0f, 0.0f, 0f),100.0f)),
            Sphere(Point(-2f, 1.3f, 0f), 0.3f, MaterialUniform(Point(0.5f, 1f, 0f),0.0f)),
            Cone(Point(0.5f, 1.2f, 1.2f), 0.2f,1f ,MaterialUniform(Point(0.5f, 0.0f, 0f),0.2f)),
            Plane(Point.origin(), Point.yUnit(), MaterialWithChecks())
        )
    )
}