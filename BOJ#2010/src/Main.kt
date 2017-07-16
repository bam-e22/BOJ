import java.util.*

fun main(args: Array<String>) {

    val input = Scanner(System.`in`);

    val N = input.nextInt()

    var ans = 1

    for (i in 0..N - 1) {

        ans += (input.nextInt() - 1)
    }

    println(ans)
}

