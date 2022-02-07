package materials

import math.Point
import scene.lights.Light

interface Material {
    fun getReflectivity(point: Point): Float
    fun getColor(point: Point, normal: Point, toLight: Point, toCamera: Point, light: Light): Point

}