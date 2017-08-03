import java.util.*

fun main(args: Array<String>) {

    val input = Scanner(System.`in`)

    var max = 0
    var count = 0
    for (i in 0..3) {

        count -= input.nextInt()
        count += input.nextInt()

        if (max < count) max = count;
    }

    println(max)
}