import java.util.*

fun main(args: Array<String>) {

    // input
    val input = Scanner(System.`in`)

    val N = Integer.parseInt(input.nextLine())
    val strArr: Array<String> = Array(N, { "" })

    for (i in 0..N - 1) {

        strArr.set(i, input.nextLine())
    }

    // solve
    var result: CharArray = strArr[0].toCharArray()
    val length = strArr[0].length

    for (i in 1..N - 1) {

        for (j in 0..length - 1) {

            if (result[j] != strArr[i].get(j)) {

                result[j] = '?'
            }
        }
    }

    println(result)
}