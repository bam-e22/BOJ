import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int MOD = 15746;

    static int[] dp = new int[1_000_000];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= N; i++) {

            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }

        System.out.println(dp[N]);
    }
}
