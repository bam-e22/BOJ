import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var t = br.readLine().toInt()
    var st: StringTokenizer
    while (t-- > 0) {
        st = StringTokenizer(br.readLine())
        bw.append("${st.nextToken().toInt() + st.nextToken().toInt()}\n")
    }
    bw.flush()
    br.close()
    bw.close()
}