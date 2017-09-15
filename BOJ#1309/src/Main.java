import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static final int MOD = 9901;
    static int[][] dp = new int[100001][3];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // 0 : 양쪽 모두 비어있는 경우, 1 : 왼쪽에 사자가 있는 경우, 2 : 오른쪽에 사자가 있는 경우
        dp[0][0] = 1;
        dp[0][1] = 1;
        dp[0][2] = 1;

        for (int row = 1; row < N; row++) {

            dp[row][0] = (dp[row - 1][0] + dp[row - 1][1] + dp[row - 1][2]) % MOD;
            dp[row][1] = (dp[row - 1][0] + dp[row - 1][2]) % MOD;
            dp[row][2] = (dp[row - 1][0] + dp[row - 1][1]) % MOD;
        }

        int answer = 0;
        for (int i = 0; i < 3; i++) {
            answer += dp[N - 1][i];
            answer %= MOD;
        }

        System.out.println(answer);
    }
}
