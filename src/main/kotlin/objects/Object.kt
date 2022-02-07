package objects

import math.Point
import materials.Material
import scene.Scene

open class Object(val position: Point, val material: Material) {
    open fun intersect(origin: Point, direction: Point): Float? {
        throw NotImplementedError()
    }

    open fun getNormalVectorSurface(point: Point): Point {
        throw NotImplementedError()
    }

    fun getLightColor(origin: Point, direction: Point, distance: Float, scene: Scene): Point {
        val intersection = origin + direction * distance
        val toCameraUnit = -direction
        val normalToSurface = getNormalVectorSurface(intersection)
        var color = Point()

        scene.lights.forEach { light ->
            light.pointsVisibleTo(intersection).forEach { lightPoint ->
                val toLightUnit = (lightPoint - intersection).normalized()
                if (!scene.objects.any { it.intersect(intersection, toLightUnit) != null }) {
                    color += material.getColor(intersection, normalToSurface, toLightUnit, toCameraUnit, light)
                }
            }
        }

        return color.apply {
            x = x.coerceIn(0f, 1f)
            y = y.coerceIn(0f, 1f)
            z = z.coerceIn(0f, 1f)
        }
    }
}
