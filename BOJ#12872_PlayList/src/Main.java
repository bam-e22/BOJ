import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#12872 플레이리스트
 * https://www.acmicpc.net/problem/12872
 */

public class Main {

    static int N, M, P;
    static int[] dp = new int[101];

    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        dp[1] = 1;

        int ans = 1;
        int j = 0;
        for (int i = 0; i < P; i++) {

            ans *= solve(N - j);
            ans %= MOD;

            j = j >= M ? j : j + 1;
        }

        System.out.println(ans);
    }

    static int solve(int N) {

        if (dp[N] > 0) return dp[N];

        return dp[N] = solve(N - 1) * N;
    }
}
