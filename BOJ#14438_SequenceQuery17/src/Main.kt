import java.util.*

fun main(args: Array<String>) {

    // input
    val input = Scanner(System.`in`)

    val N = input.nextInt()

    val arr = IntArray(N, { 0 })

    for (i in 0..N - 1) {

        arr[i] = input.nextInt()
    }

    // solve
    val segmentTree = SegmentTree(arr, N)

    var nQuery = input.nextInt()

    while (nQuery-- > 0) {

        val queryType = input.nextInt()

        if (queryType == 1) {

            val i = input.nextInt() - 1
            val v = input.nextInt()

            segmentTree.update(i, v, 1, 0, N - 1)

        } else {

            val i = input.nextInt() - 1
            val j = input.nextInt() - 1

            println(segmentTree.query(i, j, 1, 0, N - 1))

        }
    }
}

class SegmentTree(arr: IntArray, n: Int) {

    val segmentArr: IntArray
    val MAX_VALUE = Integer.MAX_VALUE

    init {

        val x = Math.ceil((Math.log(n.toDouble()) / Math.log(2.toDouble())))

        val segmentSize = (Math.pow(2.toDouble(), x) * 2).toInt()

        segmentArr = IntArray(segmentSize, { 0 })

        initialize(arr, 0, n - 1, 1)

    }

    fun initialize(arr: IntArray, left: Int, right: Int, nodeIdx: Int): Int {

        if (left == right) {

            segmentArr[nodeIdx] = arr[left]
            return segmentArr[nodeIdx]
        }

        val mid = (left + right) / 2

        segmentArr[nodeIdx] = Math.min(initialize(arr, left, mid, nodeIdx * 2), initialize(arr, mid + 1, right, nodeIdx * 2 + 1))

        return segmentArr[nodeIdx]
    }

    fun query(left: Int, right: Int, nodeIdx: Int, nodeLeft: Int, nodeRight: Int): Int {

        if (left > nodeRight || right < nodeLeft) return MAX_VALUE

        if (left <= nodeLeft && right >= nodeRight) return segmentArr[nodeIdx]

        val nodeMid = (nodeLeft + nodeRight) / 2

        return Math.min(query(left, right, nodeIdx * 2, nodeLeft, nodeMid), query(left, right, nodeIdx * 2 + 1, nodeMid + 1, nodeRight))
    }

    fun update(index: Int, newValue: Int, nodeIdx: Int, nodeLeft: Int, nodeRight: Int): Int {

        if (index < nodeLeft || index > nodeRight) return segmentArr[nodeIdx]

        if (nodeLeft == nodeRight) {

            segmentArr[nodeIdx] = newValue

            return segmentArr[nodeIdx]
        }

        val nodeMid = (nodeLeft + nodeRight) / 2

        segmentArr[nodeIdx] = Math.min(update(index, newValue, nodeIdx * 2, nodeLeft, nodeMid), update(index, newValue, nodeIdx * 2 + 1, nodeMid + 1, nodeRight))

        return segmentArr[nodeIdx]
    }

}