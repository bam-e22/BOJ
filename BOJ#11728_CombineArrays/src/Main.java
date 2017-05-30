import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#11728 배열 합치기
 * https://www.acmicpc.net/problem/11728
 */

public class Main {

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] C = new int[N + M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {

            C[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = N; i < N + M; i++) {

            C[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(C);

        for (int x : C) {

            bw.write(x + " ");
        }

        bw.flush();

        bw.close();
        br.close();
    }
}

