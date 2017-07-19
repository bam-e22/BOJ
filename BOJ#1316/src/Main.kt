import java.util.*

fun main(args: Array<String>) {

    val input = Scanner(System.`in`);

    val N = input.nextInt()
    var wordCount = 0

    for (i in 0..N - 1) {

        val word = input.next()
        var countArr = Array(26, { false })

        for (i in 0..word.length - 1) {

            if (!countArr[word[i] - 'a']) {

                countArr[word[i] - 'a'] = true;
            } else if (word[i] == word[i - 1]) {
            } else {

                wordCount++
                break
            }
        }
    }

    println(N - wordCount)
}