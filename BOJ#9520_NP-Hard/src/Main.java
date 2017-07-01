import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#9520 NP-Hard
 * https://www.acmicpc.net/problem/9520
 */

public class Main {

    static final int FRONT = 0;
    static final int BACK = 1;
    static final int MIN = 2;

    static int N;
    static int[][] weight = new int[1501][1501];
    static int[][] dp = new int[1501][1501];

    static {

        for (int i = 0; i < 1501; i++) {

            Arrays.fill(dp[i], -1);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 1; i < N + 1; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {

                weight[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve(1, 1));

    } // main

    static int solve(int left, int right) {

        if (dp[left][right] >= 0) return dp[left][right];

        int next = Math.max(left, right) + 1;

        if (next > N) return 0;

        return dp[left][right] = Math.min((weight[next][left] + solve(next, right)), (weight[right][next] + solve(left, next)));
    }
}
