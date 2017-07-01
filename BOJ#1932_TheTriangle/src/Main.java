import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#1932 숫자삼각형
 * https://www.acmicpc.net/problem/1932
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int n;
        int[][] triangle;
        int[][] dp;

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        triangle = new int[n][n + (n - 1)];
        dp = new int[n][n + (n - 1)];

        for (int i = 0; i < n; i++) {

            Arrays.fill(triangle[i], 0);
            Arrays.fill(dp[i], -1);
        }

        int center = n - 1;
        for (int i = 0; i < n; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < (i + 1) * 2; j += 2) {

                triangle[i][center - i + j] = Integer.parseInt(st.nextToken());
            }
        }

        // solve
        int tempMax = 0;
        for (int i = 0; i < 2 * n - 1; i += 2) {

            int cost = solve(triangle, dp, n - 1, i);
            tempMax = tempMax < cost ? cost : tempMax;
        }

        System.out.println(tempMax);
    }

    // dp[i][j] : i층에서 j를 선택했을 때, 0~i층까지의 최대합
    // solve() : dp[i][j]를 구함
    static int solve(int[][] triangle, int[][] dp, int depth, int num) {

        if (num < 0 || num >= triangle[0].length || depth < 0) {

            return 0;
        }

        if (dp[depth][num] != -1) {

            return dp[depth][num];
        }

        return dp[depth][num] = triangle[depth][num] + Math.max(solve(triangle, dp, depth - 1, num - 1), solve(triangle, dp, depth - 1, num + 1));
    }

}
