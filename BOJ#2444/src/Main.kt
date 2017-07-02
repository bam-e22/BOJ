import java.util.*

fun main(args: Array<String>): Unit {

    val N: Int = Scanner(System.`in`).nextInt();

    for (i in 1..N) {

        for (j in N - 1 - i downTo 0) {

            print(" ")
        }

        for (j in 1..(2 * i - 1)) {

            print("*")
        }
        println()
    }

    for (i in N - 1 downTo 1) {

        for (j in N - i downTo 1) {

            print(" ")
        }
        for (j in 2 * i - 1 downTo 1) {

            print("*")
        }
        println()
    }
}