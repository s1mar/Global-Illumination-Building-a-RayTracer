import math.Point
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import scene.Scene
import java.io.File
import javax.imageio.ImageIO


val IMG_COUNT = 360

fun main() {
    "output/renders/".also {
        File(it).mkdirs()
        generateMultipleImagesForAnimation(it) { current, total ->
            println("$current/$total")
        }
    }
}

fun generateMultipleImagesForAnimation(folder: String, onProgress: ((Int, Int) -> Unit)? = null) {
    var processed = 0
    val origin = Point(0.6f, 0.35f, -0.6f)
    val axis = Point.yUnit()

    Observable.range(0, IMG_COUNT)
        .flatMap {
            val scene = createScene()
            scene.camera.updatePosition(scene.camera.position.rotate(it.toFloat(), axis, origin))
            Observable.fromCallable {
                Pair(it, Scene.toBufferedImage(scene.render()))
            }.subscribeOn(Schedulers.computation())
        }
        .observeOn(Schedulers.io())
        .blockingSubscribe {
            val file = File("$folder/img_${it.first}.png")
            ImageIO.write(it.second, "png", file)
            onProgress?.invoke(++processed, 360)
        }
}