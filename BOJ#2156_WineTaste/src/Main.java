import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ#2156 포도주 시식
 * https://www.acmicpc.net/problem/2156
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int n;
        int[] wine; // 포도주의 양
        int[] dp; // dp[n] : n잔까지 마셨을 때, 최대로 마실 수 있는 포도주의 양

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        wine = new int[n + 1];
        dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {

            wine[i] = Integer.parseInt(br.readLine());
        }

        // dp[n]
        System.out.println(solve(wine, dp, n));

    }

    static int solve(int[] wine, int[] dp, int n) {

        if (n < 1) {

            return 0;
        } else if (n == 1) {

            return wine[1];
        } else if (n == 2) {

            return wine[1] + wine[2];
        }

        // memoization
        if (dp[n] > 0) {

            return dp[n];
        }

        // dynamic programming
        dp[n] = Math.max(Math.max(solve(wine, dp, n - 1), solve(wine, dp, n - 2) + wine[n]), solve(wine, dp, n - 3) + wine[n] + wine[n - 1]);

        return dp[n];
    }
}
