import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#2240 자두나무
 * https://www.acmicpc.net/problem/2240
 */

public class Main2 {

    static int[][][] dp = new int[3][1001][31];
    static int[] plum = new int[1001];

    public static void main(String[] args) throws IOException {

        int T;
        int W;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());


        for (int i = 1; i <= T; i++) {

            plum[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= T; i++) {

            for (int j = 0; j <= W; j++) {

                if (plum[i] == 1) { // 자두가 나무 1에서 떨어지고

                    // 나무 1에서 움직이지 않고 자두 먹는 경우
                    dp[1][i][j] = Math.max(dp[1][i][j], dp[1][i - 1][j] + 1);

                    // 나무 2에서 움직여서 자두 먹는 경우
                    if (j > 0) {

                        dp[1][i][j] = Math.max(dp[1][i][j], dp[2][i - 1][j - 1] + 1);
                    }

                    // 나무 2에서 움직이지 않고 자두 못먹는 경우
                    dp[2][i][j] = Math.max(dp[2][i][j], dp[2][i - 1][j]);

                    // 나무 1에서 움직여서 자두 못먹는 경우
                    if (j > 0) {

                        dp[2][i][j] = Math.max(dp[2][i][j], dp[1][i - 1][j - 1]);
                    }

                } else { // 자두가 나무 2에서 떨어지고

                    // 나무 1에서 움직이지 않고 자두 못먹는 경우
                    dp[1][i][j] = Math.max(dp[1][i][j], dp[1][i - 1][j]);

                    // 나무 2에서 움직여서 자두 못먹는 경우
                    if (j > 0) {

                        dp[1][i][j] = Math.max(dp[1][i][j], dp[2][i - 1][j - 1]);
                    }

                    // 나무 2에서 움직이지 않고 자두 먹는 경우
                    dp[2][i][j] = Math.max(dp[2][i][j], dp[2][i - 1][j] + 1);

                    // 나무 1에서 움직여서 자두 먹는 경우
                    if (j > 0) {

                        dp[2][i][j] = Math.max(dp[2][i][j], dp[1][i - 1][j - 1] + 1);
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i <= W; i++) {

            ans = Math.max(ans, Math.max(dp[1][T][i], dp[0][T][i]));
        }

        System.out.println(ans);

    }
}
