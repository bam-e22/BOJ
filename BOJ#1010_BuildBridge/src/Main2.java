import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#1010 다리 놓기
 * https://www.acmicpc.net/problem/1010
 */

public class Main2 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // dp[N][M] = dp[N-1][M-1] + dp[N-1][M-2] + .. + dp[N-1][N-1]
            long[][] dp = new long[N + 1][M + 1];

            for (int i = 0; i < N + 1; i++) {

                Arrays.fill(dp[i], 0L);
            }

            for (int i = 0; i < M + 1; i++) {

                dp[1][i] = i;
            }

            if (N == M) {

                System.out.println(1);
                continue;
            }

            if (N == 1) {

                System.out.println(dp[1][M]);
                continue;
            }

            for (int i = 2; i <= N; i++) {
                for (int j = i; j <= M; j++) {
                    for (int k = i - 1; k < j; k++) {

                        dp[i][j] += dp[i - 1][k];
                    }
                }
            }

            System.out.println(dp[N][M]);
        }
    }

}
