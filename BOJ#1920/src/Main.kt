import java.util.*

fun main(args: Array<String>) {

    var result = StringBuilder()
    val input = Scanner(System.`in`);

    val N = input.nextInt()

    var set = java.util.HashSet<Int>();

    for (i in 0..N - 1) {

        set.add(input.nextInt())
    }

    val M = input.nextInt()

    for (i in 0..M - 1) {

        val num = input.nextInt()

        if (set.contains(num)) result.append("1\n") else result.append("0\n")
    }

    println(result)
}