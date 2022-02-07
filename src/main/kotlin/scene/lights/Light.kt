package scene.lights

import math.Point

open class Light(
    val position: Point,
    val intensityDiffuse: Point,
    val intensityAmbient: Point,
    val intensitySpecular: Point
) {
    open fun pointsVisibleTo(point: Point): List<Point> {
        throw NotImplementedError()
    }
}