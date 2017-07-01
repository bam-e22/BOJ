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

    static int[] dRow = {0, -1, 0, 1};
    static int[] dCol = {-1, 0, 1, 0};

    static int[][] map = new int[500][500];
    static int[][] dp = new int[500][500]; // dp[i][j] : i,j에서 목표 지점으로 가는 내리막 경우의 수

    static final int NONE = -1;

    static int M;
    static int N;

    static {

        for (int i = 0; i < 500; i++) {

            Arrays.fill(dp[i], NONE);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0);

        System.out.println(dp[0][0]);
    }

    static int solve(int row, int col) {

        if (row == M - 1 && col == N - 1) {

            return 1;
        }

        // memoization
        if (dp[row][col] > NONE) {

            return dp[row][col];
        }

        int temp = 0;
        for (int i = 0; i < 4; i++) {

            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if (nextRow < 0 || nextRow >= M || nextCol < 0 || nextCol >= N) {

                continue;
            }

            if (map[nextRow][nextCol] < map[row][col]) {

                temp += solve(nextRow, nextCol);
            }
        }

        return dp[row][col] = temp;
    }
}
