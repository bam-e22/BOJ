var dp = Array(41) {
    Node()
}

fun main(args: Array<String>) {
    var T = readLine()!!.toInt()

    while (T-- > 0) {
        val N = readLine()!!.toInt()
        fibonacci(N)
        println("${dp[N].count0} ${dp[N].count1}")
    }
}

fun fibonacci(n: Int): Node {
    if (dp[n].num != -1) {
        return dp[n]
    }
    when (n) {
        0 -> {
            dp[n].num = 0
            dp[n].count0++
        }
        1 -> {
            dp[n].num = 1
            dp[n].count1++
        }
        else -> {
            dp[n] = fibonacci(n - 1) + fibonacci(n - 2)
        }
    }

    return dp[n]
}

class Node(var num: Int = -1, var count0: Int = 0, var count1: Int = 0) {
    operator fun plus(value: Node): Node {
        return Node(num + value.num, count0 + value.count0, count1 + value.count1)
    }
}