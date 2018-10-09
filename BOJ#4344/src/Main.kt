fun main(args: Array<String>) {
    var t = readLine()!!.toInt()

    while (t-- > 0) {
        val input = readLine()!!.split(' ')

        val n = input.first().toInt()
        val scores = input.asSequence().drop(1).map { it.toInt() }
        val average = scores.sum() / n

        val answer = scores.count {
            it > average
        }.toDouble().let {
            it * 100 / n
        }

        println("%.3f".format(answer) + "%")
    }
}

