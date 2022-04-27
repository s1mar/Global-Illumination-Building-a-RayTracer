package scene

import math.Point
import math.times
import objects.Object
import scene.lights.Light
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Scene(
    val rdiv: Int =250,
    var wdiv: Float = 1.0f,
    var ranger: Int = 255,
    val wardOrRein:Int = 0, // 1 for Ward and 2 for Rein
    val camera: Camera,
    val lights: List<Light>,
    val objects: List<Object>
) {
    fun render(onProgress: ((Int) -> Unit)? = null): List<List<Point>> {
        if(wardOrRein==0){
            wdiv = 1.0f
        }
        else if(wardOrRein==2){
            ranger-=rdiv
        }

        val screen = camera.screen
        val image = List(screen.height) {
            List(screen.width) {
                Point()
            }
        }

        val xVector = (screen.topRight - screen.topLeft) * (1f / screen.width)
        val yVector = (screen.bottomLeft - screen.topLeft) * (1f / screen.height)

        for (y in 0 until screen.height) {
            for (x in 0 until screen.width) {
                val pixel = screen.topLeft + xVector * x.toFloat() + yVector * y.toFloat()
                val direction = (pixel - camera.position).normalized()
                val origin = Point(camera.position)
                image[y][x].set(traceRay(origin, direction))
            }
            onProgress?.invoke((y + 1) * 100 / screen.height)
        }

        return image
    }

    private fun traceRay(origin: Point, direction: Point, maxDepth: Int = 3, lIn:Int = 0): Point {
        val color = Point()
        var reflection = 1f

        for (k in 0..maxDepth) {
            var nearestObject: Object? = null
            var minDistance = Float.MAX_VALUE
            objects.forEach { obj ->
                obj.intersect(origin, direction)?.let {
                    if (it < minDistance) {
                        minDistance = it
                        nearestObject = obj
                    }
                }
            }

            nearestObject?.let {
                val intersection = origin + minDistance * direction
                val normal = it.getNormalVectorSurface(intersection)
                color.set(color + reflection *wdiv* it.getLightColor(origin, direction, minDistance, this))
                origin.set(intersection + 1e-3f * normal)
                direction.set(direction.reflection(normal))
                reflection *= it.material.getReflectivity(intersection)
            } ?: break
        }

        return color.apply {
            x = x.coerceIn(0f, 1f)
            y = y.coerceIn(0f, 1f)
            z = z.coerceIn(0f, 1f)
        }
    }

    fun renderToFile(filepath: String, onProgress: ((Int) -> Unit)? = null) {
        val image = render(onProgress)
        val buffer = toBufferedImage(image)
        val file = File(filepath)
        ImageIO.write(buffer, "png", file)
    }

    fun toBufferedImage(image: List<List<Point>>): BufferedImage {
        val height = image.size
        val width = image.first().size
        return BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR).also {
            image.forEachIndexed { y, list ->
                list.forEachIndexed { x, p ->
                    val red = (p.x * ranger).toInt()
                    val green = (p.y * ranger).toInt()
                    val blue = (p.z * ranger).toInt()
                    val color = (red shl 16) or (green shl 8) or blue
                    it.setRGB(x, y, color)
                }
            }
        }
    }

    companion object{
        fun toBufferedImage(image: List<List<Point>>): BufferedImage {
            val height = image.size
            val width = image.first().size
            return BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR).also {
                image.forEachIndexed { y, list ->
                    list.forEachIndexed { x, p ->
                        val red = (p.x * 255).toInt()
                        val green = (p.y * 255).toInt()
                        val blue = (p.z * 255).toInt()
                        val color = (red shl 16) or (green shl 8) or blue
                        it.setRGB(x, y, color)
                    }
                }
            }
        }
    }

}
