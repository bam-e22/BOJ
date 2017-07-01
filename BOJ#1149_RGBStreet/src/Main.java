import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#1149 RGB 거리
 * https://www.acmicpc.net/problem/1149
 */

public class Main {

    static final int RED = 0;
    static final int GREEN = 1;
    static final int BLUE = 2;

    public static void main(String[] args) throws IOException {

        int N;
        int[][] RGB;
        int[][] dp; // dp[i][j] : i번째 집을 j색으로 색칠했을 때 최소 비용

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        RGB = new int[N][3];
        dp = new int[N][3];

        for (int i = 0; i < 3; i++) {

            Arrays.fill(dp[i], 0);
        }

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {

                RGB[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // solve
        dp[0][RED] = RGB[0][RED];
        dp[0][GREEN] = RGB[0][GREEN];
        dp[0][BLUE] = RGB[0][BLUE];

        for (int i = 1; i < N; i++) {

            for (int j = 0; j < 3; j++) {

                dp[i][j] = getMin(dp, i - 1, j) + RGB[i][j];
            }
        }

        System.out.println(Math.min(dp[N - 1][RED], Math.min(dp[N - 1][GREEN], dp[N - 1][BLUE])));

    } // main

    static int getMin(int[][] dp, int house, int exclude) {

        return exclude == RED ? Math.min(dp[house][GREEN], dp[house][BLUE]) : exclude == BLUE ? Math.min(dp[house][RED], dp[house][GREEN]) : Math.min(dp[house][RED], dp[house][BLUE]);
    }
}
