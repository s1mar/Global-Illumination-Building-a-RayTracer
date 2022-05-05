package objects

import materials.Material
import math.Point
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

class Cylinder (
    private val center: Point,
    private val radius: Float,
    private val height: Float,
    material: Material
): Object(center, material) {
    override fun getNormalVectorSurface(p: Point): Point {
       var n = Point(p.x-center.x,0f,p.z-center.z)
       n = n.normalized()
       return n

    }

    override fun intersect(pos: Point, dir: Point): Float? {

        val a = dir.x.pow(2) + dir.z.pow(2)
        val b = 2*(dir.x*(pos.x-center.x) + dir.z*(pos.z-center.z))
        val c = (pos.x - center.x).pow(2) + (pos.z-center.z).pow(2) - radius.pow(2)

        val delta = b.pow(2) - 4*a*c

        if(delta>0){
            val t1 = (-b + sqrt(delta))/(2*a)
            val t2 = (-b + sqrt(delta))/(2*a)
            val t = min(max(0f,t1),max(0f,t2))
            if(t>0){
                return t
            }
        }
        return null
    }
}