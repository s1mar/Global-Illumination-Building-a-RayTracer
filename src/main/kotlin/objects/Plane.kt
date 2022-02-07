package objects

import math.Point
import materials.Material
import kotlin.math.absoluteValue

class Plane(
    private val point: Point,
    private val normal: Point,
    material: Material
): Object(point, material) {
    override fun getNormalVectorSurface(point: Point): Point {
        return normal
    }

    override fun intersect(origin: Point, direction: Point): Float? {
        if (normal.dot(direction).absoluteValue < 1e-6) {
            return null
        }

        val t = normal.dot(point - origin) / normal.dot(direction)
        if (t > 1e-4) {
            return t
        }

        return null
    }
}
