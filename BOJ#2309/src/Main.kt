import java.util.*

fun main(args: Array<String>) {

    val input = Scanner(System.`in`)

    var numArr = Array(9, { 0 })

    for (i in 0..8) {

        numArr.set(i, input.nextInt())
    }

    numArr.sort()

    permutaion(numArr, 0)

    for (i in 0..6) {

        println(numArr[i])
    }
}

fun permutaion(numArr: Array<Int>, depth: Int): Boolean {

    if (depth == 7) {

        var sum = 0
        for (i in 0..6) {

            sum += numArr[i]
        }

        return sum == 100
    }

    for (i in depth..8) {

        swap(numArr, depth, i)
        if (permutaion(numArr, depth + 1)) return true
        swap(numArr, depth, i)
    }

    return false
}

fun swap(numArr: Array<Int>, a: Int, b: Int) {

    val temp = numArr[a]

    numArr[a] = numArr[b]
    numArr[b] = temp
}