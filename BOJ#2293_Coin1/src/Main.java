import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#2293 동전1
 * https://www.acmicpc.net/problem/2293
 */

public class Main {

    // dp[i] : n가지 종류의 동전을 써서 i원을 만드는 경우의 수
    static int[] dp = new int[10001];

    public static void main(String[] args) throws IOException {

        int n; // 동전 종류 개수
        int k; // 목표
        int[] coins;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        coins = new int[n];
        for (int i = 0; i < n; i++) {

            coins[i] = Integer.parseInt(br.readLine());
        }

        solve(n, k, coins);

        System.out.println(dp[k]);
    }

    static void solve(int n, int target, int[] coins) {

        dp[0] = 1;

        for (int i = 0; i < n; i++) {

            for (int j = coins[i]; j <= target; j++) {

                dp[j] += dp[j - coins[i]];
            }
        }
    }
}
