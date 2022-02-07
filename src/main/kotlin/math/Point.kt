package math

import kotlin.math.*

class Point(var x: Float = 0f, var y: Float = 0f, var z: Float = 0f) {
    constructor(p: Point) : this() {
        set(p)
    }

    constructor(n: Float) : this() {
        set(n, n, n)
    }

    fun set(p: Point) {
        x = p.x
        y = p.y
        z = p.z
    }

    fun set(x: Float = this.x, y: Float = this.y, z: Float = this.z) {
        this.x = x
        this.y = y
        this.z = z
    }

    operator fun plus(p: Point): Point {
        return Point(x + p.x, y + p.y, z + p.z)
    }

    operator fun unaryMinus(): Point {
        return Point(-x, -y, -z)
    }

    operator fun minus(p: Point): Point {
        return this + (-p)
    }

    operator fun times(p: Point): Point {
        return Point(x * p.x, y * p.y, z * p.z)
    }

    operator fun times(f: Float): Point {
        return Point(x * f, y * f, z * f)
    }

    fun dot(p: Point): Float {
        return x * p.x + y * p.y + z * p.z
    }

    fun cross(p: Point): Point {
        return Point(
            y * p.z - z * p.y,
            z * p.x - x * p.z,
            x * p.y - y * p.x
        )
    }

    fun reflection(p: Point): Point {
        return this - 2 * this.dot(p) * p
    }

    fun length(): Float {
        return sqrt(x * x + y * y + z * z)
    }

    fun normalized(): Point {
         return this * (1f / length())
    }

    fun rotate(angle: Float, axis: Point): Point {
        val r = angle * PI / 180
        val cosR = cos(r)
        val sinR = sin(r)
        return Point(
            ((cosR + axis.x.pow(2) * (1 - cosR)) * x + (axis.x * axis.y * (1 - cosR) - axis.z * sinR) * y + (axis.x * axis.z * (1 - cosR) + axis.y * sinR) * z).toFloat(),
            ((axis.y * axis.x * (1 - cosR) + axis.z * sinR) * x + (cosR + axis.y.pow(2) * (1 - cosR)) * y + (axis.y * axis.z * (1 - cosR) - axis.x * sinR) * z).toFloat(),
            ((axis.z * axis.x * (1 - cosR) - axis.y * sinR) * x + (axis.z * axis.y * (1 - cosR) + axis.x * sinR) * y + (cosR + axis.z.pow(2) * (1 - cosR)) * z).toFloat()
        )
    }

    fun rotate(angle: Float, axis: Point, origin: Point = origin()): Point {
        return (this - origin).rotate(angle, axis) + origin
    }

    override fun toString(): String {
        return "($x, $y, $z)"
    }

    companion object {
        fun origin(): Point = Point()
        fun ones(): Point = Point(1f, 1f, 1f)
        fun xUnit(): Point = Point(1f, 0f, 0f)
        fun yUnit(): Point = Point(0f, 1f, 0f)
        fun zUnit(): Point = Point(0f, 0f, 1f)
    }
}
