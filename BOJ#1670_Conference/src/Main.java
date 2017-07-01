import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ#1670 정상회담2
 * https://www.acmicpc.net/problem/1670
 */

public class Main {

    static final int MOD = 987654321;
    static long[] dp = new long[10005];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= N; i += 2) {

            for (int j = 1; j < i; j += 2) {

                dp[i] += dp[j-1] * dp[i - (j + 1)];
                dp[i] %= MOD;
            }
        }

        System.out.println(dp[N]);
    }
}
