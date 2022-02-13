package objects

import materials.Material
import math.Point
import java.lang.Math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt


class Cone(
    private val center: Point,
    private val radius: Float,
    private val height: Float,
    material: Material
): Object(center,material){

    override fun getNormalVectorSurface(p: Point): Point {
        //return (point - center).normalized()
        val r: Float = sqrt((p.x - center.x) * (p.x - center.x) + (p.z - center.z) * (p.z - center.z))
        val n = Point(p.x - center.x, r * (radius / height), p.z - center.z)
        return n.normalized()

    }

    override fun intersect(origin: Point, direction: Point): Float? {

            val A  = origin.x - center.x
            val B  = origin.z - center.z
            val D = height - origin.y + center.y

            val tan = (radius/height) * (radius/height)

            val a = (direction.x * direction.x) + (direction.z * direction.z) - (tan*(direction.y*direction.y))
            val b = (2*A*direction.x) + (2*B*direction.z) + (2*tan*D*direction.y)
            val c = (A*A) + (B*B) - (tan*(D*D))


            val delta = b.pow(2) - 4 * a * c
            if (delta > 0) {
                val t1 = (-b + sqrt(delta)) / 2f*a
                val t2 = (-b - sqrt(delta)) / 2f*a

                val t = when{
                    t1>t2->t2
                    else->t1
                }
                if (t > 0) {
                    val r = origin.y + t*direction.y
                    if ((r > center.y) and (r < center.y + height)) return t;

                }
            }
            return null

    }
}