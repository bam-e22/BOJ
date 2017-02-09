import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#1520 내리막길
 * https://www.acmicpc.net/problem/1520
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int M; // 세로의 크기
        int N; // 가로의 크기
        int[][] map;
        int[][] dp;

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {

            Arrays.fill(dp[i], 0);

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve(map, dp, M, N, M - 1, N - 1));


    } // main

    // dp[i][j] : (0, 0) 부터 (i, j) 좌표까지의 내리막 경로의 수
    static int solve(int[][] map, int[][] dp, int M, int N, int i, int j) {

        if (i == 0 && j == 0) {

            return 1;
        }

        if (dp[i][j] > 0) {

            return dp[i][j];
        }

        // 상
        if (i - 1 >= 0 && map[i - 1][j] > map[i][j]) {

            dp[i][j] += solve(map, dp, M, N, i - 1, j);
        }

        // 하
        if (i + 1 < M && map[i + 1][j] > map[i][j]) {

            dp[i][j] += solve(map, dp, M, N, i + 1, j);
        }

        // 좌
        if (j - 1 >= 0 && map[i][j - 1] > map[i][j]) {

            dp[i][j] += solve(map, dp, M, N, i, j - 1);
        }

        // 우
        if (j + 1 < N && map[i][j + 1] > map[i][j]) {

            dp[i][j] += solve(map, dp, M, N, i, j + 1);
        }

        return dp[i][j];
    }

}
