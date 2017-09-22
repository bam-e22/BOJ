import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MOD = 10007;
    static int[][] dp = new int[1001][1001];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(solve(N, K));
    }

    static int solve(int N, int K) {

        // 고를 원소가 없는 경우, 모든 원소를 다 고르는 경우
        if (K == 0 || N == K) return 1;

        if (dp[N][K] > 0) return dp[N][K];

        dp[N][K] = solve(N - 1, K - 1) + solve(N - 1, K);

        return dp[N][K] % MOD;
    }
}
