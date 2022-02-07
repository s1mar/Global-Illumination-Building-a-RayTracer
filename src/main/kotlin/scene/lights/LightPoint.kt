package scene.lights

import math.Point

class LightPoint(
    position: Point,
    intensityDiffuse: Point = Point(1.5f),
    intensityAmbient: Point = Point(0.5f),
    intensitySpecular: Point = Point.ones()
): Light(position, intensityDiffuse, intensityAmbient, intensitySpecular) {
    override fun pointsVisibleTo(point: Point): List<Point> {
        return listOf(position)
    }
}
