import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#2240 자두나무
 * https://www.acmicpc.net/problem/2240
 */

public class Main {

    static int[][][] dp = new int[3][1002][32];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= T; i++) {

            int plum = Integer.parseInt(br.readLine());

            for (int j = 1; j <= W + 1; j++) {

                if (plum == 1) {

                    dp[1][i][j] = Math.max(dp[1][i - 1][j] + 1, dp[2][i - 1][j - 1] + 1);
                    dp[2][i][j] = Math.max(dp[1][i - 1][j - 1], dp[2][i - 1][j]);

                } else {

                    dp[1][i][j] = Math.max(dp[1][i - 1][j], dp[2][i - 1][j - 1]);
                    dp[2][i][j] = Math.max(dp[1][i - 1][j - 1] + 1, dp[2][i - 1][j] + 1);
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= W + 1; i++) {

            ans = Math.max(ans, Math.max(dp[1][T][i], dp[2][T][i]));
        }

        System.out.println(ans);
    }
}
