import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#5557 1학년
 * https://www.acmicpc.net/problem/5557
 */

public class Main {

    static final int MAX = 20;
    static final int MIN = 0;

    static int N;
    static int[] A = new int[101];

    static long[][] dp = new long[101][21];

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {

            A[i] = Integer.parseInt(st.nextToken());
        }

        // solve
        dp[0][A[0]] = 1;
        System.out.println(solve(N - 2, A[N - 1]));
    }

    static long solve(int idx, int sum) {

        if (sum < MIN || sum > MAX) return 0;
        if (idx == 0) {

            return (sum == A[idx]) ? 1 : 0;
        }
        if (dp[idx][sum] > 0) return dp[idx][sum];

        dp[idx][sum] = 0;
        dp[idx][sum] += solve(idx - 1, sum + A[idx]);
        dp[idx][sum] += solve(idx - 1, sum - A[idx]);

        return dp[idx][sum];
    }
}
