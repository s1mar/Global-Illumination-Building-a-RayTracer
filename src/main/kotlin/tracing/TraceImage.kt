import math.Point
import materials.MaterialUniform
import materials.MaterialWithChecks
import materials.MaterialWithChecksNR
import materials.MaterialWithChecksRY
import objects.Cone
import objects.Plane
import objects.PlaneLimited
import objects.Sphere
import scene.Camera
import scene.Scene
import scene.lights.LightPoint
import java.io.File

val IMG_OUTPUT_PATH = "output/snapshot/"

val COLOR_BLUE:Point = Point(0.25f, 0.25f, 0.5f)
val COLOR_RED:Point = Point(0.45f, 0f, 0f)
val COLOR_GREEN:Point = Point(0f, 0.5f, 0f)
val R_CORRECTOR = 0
fun main() {

    IMG_OUTPUT_PATH.also { File(it).mkdirs()
        createScene_ToneLow().renderToFile("$IMG_OUTPUT_PATH ToneLow.png") {
            println("$it%")
        }
    }

    IMG_OUTPUT_PATH.also { File(it).mkdirs()
        createScene_ToneLowR().renderToFile("$IMG_OUTPUT_PATH ToneLowR.png") {
            println("$it%")
        }
    }

    IMG_OUTPUT_PATH.also { File(it).mkdirs()
        createScene_ToneMed().renderToFile("$IMG_OUTPUT_PATH ToneMed.png") {
            println("$it%")
        }
    }

    IMG_OUTPUT_PATH.also { File(it).mkdirs()
        createScene_ToneMedR().renderToFile("$IMG_OUTPUT_PATH ToneMedR.png") {
            println("$it%")
        }
    }

    IMG_OUTPUT_PATH.also { File(it).mkdirs()
        createScene_ToneHigh().renderToFile("$IMG_OUTPUT_PATH ToneHigh.png") {
            println("$it%")
        }
    }
    IMG_OUTPUT_PATH.also { File(it).mkdirs()
        createScene_ToneHighR().renderToFile("$IMG_OUTPUT_PATH ToneHighR.png") {
            println("$it%")
        }
    }

    IMG_OUTPUT_PATH.also { File(it).mkdirs()
        createScene().renderToFile("$IMG_OUTPUT_PATH normal.png") {
            println("$it%")
        }
    }

}


fun createScene_ToneHigh(): Scene {
    return Scene(
        wardOrRein = 1,
        wdiv = 10.0f,
        camera = Camera(
            position = Point(-1f, 1f, 1f),
            lookAt = Point(0f, 0.3f, -1f),
            planeNormal = Point.yUnit(),
            width = 1280,
            height = 720
        ),
        lights = listOf(
            LightPoint(Point(-5f, 5f, -1.5f)),
            LightPoint(Point(5f, 5f, 5f), intensityDiffuse = Point(1.0f), intensitySpecular = Point(1.5f), intensityAmbient = Point(0.5f))
        ),
        objects = listOf(
            Sphere(Point(0.6f, 0.35f, -0.6f), 0.35f, MaterialUniform(COLOR_BLUE)),
            Sphere(Point(-0.8f, 0.2f, -0.7f), 0.2f, MaterialUniform(COLOR_GREEN)),
            Sphere(Point(0f, 0.3f, -1f), 0.3f, MaterialUniform(COLOR_RED)),
            Plane(Point.origin(), Point.yUnit(), MaterialWithChecks())
        )
    )
}
fun createScene_ToneHighR(): Scene {
    return Scene(
        wardOrRein = 2,
        camera = Camera(
            position = Point(-1f, 1f, 1f),
            lookAt = Point(0f, 0.3f, -1f),
            planeNormal = Point.yUnit(),
            width = 1280,
            height = 720
        ),
        lights = listOf(
            LightPoint(Point(-5f, 5f, -1.5f)),
            LightPoint(Point(5f, 5f, 5f), intensityDiffuse = Point(1.0f), intensitySpecular = Point(1.5f), intensityAmbient = Point(0.5f))
        ),
        objects = listOf(
            Sphere(Point(0.6f, 0.35f, -0.6f), 0.35f, MaterialUniform(COLOR_BLUE)),
            Sphere(Point(-0.8f, 0.2f, -0.7f), 0.2f, MaterialUniform(COLOR_GREEN)),
            Sphere(Point(0f, 0.3f, -1f), 0.3f, MaterialUniform(COLOR_RED)),
            Plane(Point.origin(), Point.yUnit(), MaterialWithChecks())
        )
    )
}


fun createScene(): Scene {
    return Scene(
        wardOrRein = 0,
        camera = Camera(
            position = Point(-1f, 1f, 1f),
            lookAt = Point(0f, 0.3f, -1f),
            planeNormal = Point.yUnit(),
            width = 1280,
            height = 720
        ),
        lights = listOf(
            LightPoint(Point(-5f, 5f, -1.5f)),
            LightPoint(Point(5f, 5f, 5f), intensityDiffuse = Point(0.5f))
        ),
        objects = listOf(
            Sphere(Point(0.6f, 0.35f, -0.6f), 0.35f, MaterialUniform(COLOR_BLUE)),
            Sphere(Point(-0.8f, 0.2f, -0.7f), 0.2f, MaterialUniform(COLOR_GREEN)),
            Sphere(Point(0f, 0.3f, -1f), 0.3f, MaterialUniform(COLOR_RED)),
            Plane(Point.origin(), Point.yUnit(), MaterialWithChecks())
        )
    )
}

fun createScene_ToneMed(): Scene {
    return Scene(
        wardOrRein = 1,
        wdiv = 1.5f,
        camera = Camera(
            position = Point(-1f, 1f, 1f),
            lookAt = Point(0f, 0.3f, -1f),
            planeNormal = Point.yUnit(),
            width = 1280,
            height = 720
        ),
        lights = listOf(
            LightPoint(Point(-5f, 5f, -1.5f)),
            LightPoint(Point(5f, 5f, 5f), intensityDiffuse = Point(0.5f))
        ),
        objects = listOf(
            Sphere(Point(0.6f, 0.35f, -0.6f), 0.35f, MaterialUniform(COLOR_BLUE)),
            Sphere(Point(-0.8f, 0.2f, -0.7f), 0.2f, MaterialUniform(COLOR_GREEN)),
            Sphere(Point(0f, 0.3f, -1f), 0.3f, MaterialUniform(COLOR_RED)),
            Plane(Point.origin(), Point.yUnit(), MaterialWithChecks())
        )
    )
}

fun createScene_ToneMedR(): Scene {
    return Scene(

        wardOrRein = 2,
        rdiv =252-R_CORRECTOR,
        camera = Camera(
            position = Point(-1f, 1f, 1f),
            lookAt = Point(0f, 0.3f, -1f),
            planeNormal = Point.yUnit(),
            width = 1280,
            height = 720
        ),
        lights = listOf(
            LightPoint(Point(-5f, 5f, -1.5f)),
            LightPoint(Point(5f, 5f, 5f), intensityDiffuse = Point(0.5f))
        ),
        objects = listOf(
            Sphere(Point(0.6f, 0.35f, -0.6f), 0.35f, MaterialUniform(COLOR_BLUE)),
            Sphere(Point(-0.8f, 0.2f, -0.7f), 0.2f, MaterialUniform(COLOR_GREEN)),
            Sphere(Point(0f, 0.3f, -1f), 0.3f, MaterialUniform(COLOR_RED)),
            Plane(Point.origin(), Point.yUnit(), MaterialWithChecks())
        )
    )
}

fun createScene_ToneLow(): Scene {
    return Scene(
        wardOrRein = 1,
        wdiv=0.1f,
        camera = Camera(
            position = Point(-1f, 1f, 1f),
            lookAt = Point(0f, 0.3f, -1f),
            planeNormal = Point.yUnit(),
            width = 1280,
            height = 720
        ),
        lights = listOf(
            LightPoint(Point(-5f, 5f, -1.5f)),
            LightPoint(Point(5f, 5f, 5f), intensityDiffuse = Point(0.15f))
        ),
        objects = listOf(
            Sphere(Point(0.6f, 0.35f, -0.6f), 0.35f, MaterialUniform(COLOR_BLUE)),
            Sphere(Point(-0.8f, 0.2f, -0.7f), 0.2f, MaterialUniform(COLOR_GREEN)),
            Sphere(Point(0f, 0.3f, -1f), 0.3f, MaterialUniform(COLOR_RED)),
            Plane(Point.origin(), Point.yUnit(), MaterialWithChecks())
        )
    )
}

fun createScene_ToneLowR(): Scene {
    return Scene(
        wardOrRein = 2,
        rdiv =253-R_CORRECTOR,
        camera = Camera(
            position = Point(-1f, 1f, 1f),
            lookAt = Point(0f, 0.3f, -1f),
            planeNormal = Point.yUnit(),
            width = 1280,
            height = 720
        ),
        lights = listOf(
            LightPoint(Point(-5f, 5f, -1.5f)),
            LightPoint(Point(5f, 5f, 5f), intensityDiffuse = Point(0.15f))
        ),
        objects = listOf(
            Sphere(Point(0.6f, 0.35f, -0.6f), 0.35f, MaterialUniform(COLOR_BLUE)),
            Sphere(Point(-0.8f, 0.2f, -0.7f), 0.2f, MaterialUniform(COLOR_GREEN)),
            Sphere(Point(0f, 0.3f, -1f), 0.3f, MaterialUniform(COLOR_RED)),
            Plane(Point.origin(), Point.yUnit(), MaterialWithChecks())
        )
    )
}

fun createScene_2(): Scene {
    return Scene(
        camera = Camera(
            position = Point(-2f, 2f, 3f),
            lookAt = Point(-0.8f, 1f, 0f),
            planeNormal = Point.yUnit(),
            width = 1920,
            height = 1080
        ),
        lights = listOf(
            LightPoint(Point(2f, 2f, 4f), intensityDiffuse = Point(1.0f), intensityAmbient = Point(1.0f), intensitySpecular = Point(1.0f))
        ),
        objects = listOf(
            Sphere(Point(-1f, 1.0f, 0f), 0.6f, MaterialUniform(Point(0f, 0.0f, 0f),0.8f)),
            Sphere(Point(-2f, 1.3f, 0f), 0.3f, MaterialUniform(Point(0.5f, 1f, 0f),0.0f)),
            //Cone(Point(0.5f, 1.2f, 1.2f), 0.2f,1f ,MaterialUniform(Point(0.5f, 0.0f, 0f),0.2f)),
            Plane(Point.origin(), Point.yUnit(), MaterialWithChecks())
        )
    )
}

fun createScene_Ch5(): Scene {
    return Scene(
        camera = Camera(
            position = Point(-2f, 2f, 3f),
            lookAt = Point(-0.8f, 1f, 0f),
            planeNormal = Point.yUnit(),
            width = 1920,
            height = 1080
        ),
        lights = listOf(
            LightPoint(Point(2f, 2f, 3f), intensityDiffuse = Point(1.0f)) ,
            LightPoint(Point(-1.2f, 2f, 3f), intensityDiffuse = Point(0.5f))
        ),
        objects = listOf(
            Sphere(Point(-1f, 1.0f, 0f), 0.6f, MaterialUniform(Point(0f, 0.0f, 0f),0f)),
            Sphere(Point(-2f, 1.3f, 0f), 0.3f, MaterialUniform(Point(1.7f, 1f, 3f),0.6f)),
            Cone(Point(0.5f, 1.2f, 1.2f), 0.2f,1f ,MaterialUniform(Point(1.5f, 0.0f, 0f),0.2f)),
            PlaneLimited(Point.origin(), Point.yUnit(), MaterialWithChecksNR())
        )
    )
}
