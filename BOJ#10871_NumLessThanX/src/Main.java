import java.io.*;
import java.util.StringTokenizer;

/**
 * BOJ#10871 X 보다 작은 수
 * https://www.acmicpc.net/problem/10871
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int N;
        int X;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {

            int num = Integer.parseInt(st.nextToken());

            if (num < X) {

                bw.write(num + " ");
            }
        }

        bw.flush();

        br.close();
        bw.close();
    }
}
