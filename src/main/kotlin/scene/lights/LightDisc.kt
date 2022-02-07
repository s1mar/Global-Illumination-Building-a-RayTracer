package scene.lights

import math.Point

class LightDisc(
    val center: Point,
    radius: Float,
    towards: Point,
    intensityDiffuse: Point = Point(2f),
    intensityAmbient: Point = Point(0.4f),
    intensitySpecular: Point = Point.ones()
): Light(center, intensityDiffuse, intensityAmbient, intensitySpecular) {
    private val positions = mutableListOf(center)

    init {
        val normal = (towards - center).normalized()
        val orthogonal = Point(0f, -normal.z, normal.y).normalized()
        for (i in 10 until 100 step 10) {
            for (j in 0 until 360 step 20) {
                positions.add(center + orthogonal.rotate(j.toFloat(), normal) * (i / 100f) * radius)
            }
        }
        intensityDiffuse.set(intensityDiffuse * (1f / positions.size))
        intensityAmbient.set(intensityAmbient * (1f / positions.size))
        intensitySpecular.set(intensitySpecular * (1f / positions.size))
    }

    override fun pointsVisibleTo(point: Point): List<Point> {
        return positions
    }
}
