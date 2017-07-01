import java.io.*;
import java.util.StringTokenizer;

/**
 * BOJ#2167 2차원 배열의 합
 * https://www.acmicpc.net/problem/2167
 */

public class Main {

    static int[][] arr = new int[301][301];
    static int[][] dp = new int[301][301];

    public static void main(String[] args) throws IOException {

        int N, M; // 배열의 크기
        int K; // query

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {

                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = arr[i][j] + dp[i][j - 1];
            }
        }

        K = Integer.parseInt(br.readLine());

        for (int k = 0; k < K; k++) {

            int i, j, x, y;

            st = new StringTokenizer(br.readLine());

            i = Integer.parseInt(st.nextToken());
            j = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            int sum = 0;
            for (int row = i; row <= x; row++) {

                sum += dp[row][y] - dp[row][j-1];
            }

            bw.write(sum + "\n");
        } // ~ K loop

        bw.flush();
        bw.close();
        br.close();
    } // ~ main
}
