import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] D = new int[501];
    static int[][] dp = new int[501][501];

    static final int MAX_VALUE = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            D[i] = Integer.parseInt(st.nextToken());
            D[i + 1] = Integer.parseInt(st.nextToken());

            Arrays.fill(dp[i], MAX_VALUE);
        }

        // Solve

        System.out.println(solve(0, N - 1));

    }

    static int solve(int start, int end) {

        if (start == end) return 0;

        if (dp[start][end] != MAX_VALUE) return dp[start][end];

        for (int i = start; i < end; i++) {

            dp[start][end] = Math.min(dp[start][end], solve(start, i) + solve(i + 1, end) + D[start] * D[i + 1] * D[end + 1]);
        }

        return dp[start][end];
    }
}