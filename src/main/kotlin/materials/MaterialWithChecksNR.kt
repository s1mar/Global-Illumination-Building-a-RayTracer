package materials

import math.Point
import math.times
import scene.lights.Light

class MaterialWithChecksNR(
    private val uniformMaterial1: MaterialUniform = MaterialUniform(Point()),
    private val uniformMaterial2: MaterialUniform = MaterialUniform(Point(0.6f)),
    private val spaceNormal: Point = Point(0f, 1f, 0f),
    private val squareSize: Float = 0.2f
): Material {
    private val u = Point()
    private val v = Point()

    init {
        spaceNormal.set(spaceNormal.normalized())
        u.set(Point(0f, spaceNormal.z, -spaceNormal.y).normalized())
        v.set(u.cross(spaceNormal).normalized())
    }

    private fun getMaterialOnSurface(point: Point): MaterialUniform {
        val projected = point - (point - this.spaceNormal).dot(this.spaceNormal) * this.spaceNormal
        val distance = (point - projected).length()
        projected.set(projected + distance * u)

        val tmp = (projected - this.spaceNormal)
        val a = tmp.dot(u)
        val b = tmp.dot(v)

        val i = (a / squareSize).run { if (this < 0) this - 1 else this }.toInt()
        val j = (b / squareSize).run { if (this < 0) this - 1 else this }.toInt()

        if ((i + j % 2) % 2 == 0) {
            return uniformMaterial1
        }
        return uniformMaterial2
    }

    override fun getColor(point: Point, normal: Point, toLight: Point, toCamera: Point, light: Light): Point {
        return getMaterialOnSurface(point).getColor(point, normal, toLight, toCamera, light)
    }

    override fun getReflectivity(point: Point): Float {
        return 0f //Non reflective floor
    }
}
