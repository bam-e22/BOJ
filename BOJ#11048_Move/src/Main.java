import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#11048 이동하기
 * https://www.acmicpc.net/problem/11048
 */

public class Main {

    static int N, M;
    static int[] dRow = {0, -1, -1};
    static int[] dCol = {-1, -1, 0};
    static int[][] map = new int[1001][1001];
    static int[][] dp = new int[1001][1001];

    static {

        for (int i = 0; i < 1001; i++) {

            Arrays.fill(dp[i], -1);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][1] = map[1][1];
        System.out.println(solve(N, M));

    }

    static int solve(int row, int col) {

        if (row < 1 || col < 1) return 0;

        if (dp[row][col] >= 0) return dp[row][col];

        for (int i = 0; i < 3; i++) {

            int prevRow = row + dRow[i];
            int prevCol = col + dCol[i];

            dp[row][col] = Math.max(dp[row][col], solve(prevRow, prevCol) + map[row][col]);
        }

        return dp[row][col];
    }
}
