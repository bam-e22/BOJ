fun main(args: Array<String>) {
    var t = readLine()!!.toInt()

    while (t-- > 0) {
        val input = readLine()!!.split(' ')
        println(getPrize1(input[0].toInt()) + getPrize2(input[1].toInt()))
    }
}

fun getPrize1(rank: Int): Int = when (rank) {
    1 -> 500
    in 2..3 -> 300
    in 4..6 -> 200
    in 7..10 -> 50
    in 11..15 -> 30
    in 16..21 -> 10
    else -> 0
} * 10000

fun getPrize2(rank: Int): Int = when (rank) {
    1 -> 512
    in 2..3 -> 256
    in 4..7 -> 128
    in 8..15 -> 64
    in 16..31 -> 32
    else -> 0
} * 10000