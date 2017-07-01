import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#1937 욕심쟁이 판다
 * https://www.acmicpc.net/problem/1937
 */

public class Main {

    static int n;
    static int[][] dp = new int[501][501];
    static int[][] map = new int[501][501];

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxValue = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                maxValue = Math.max(maxValue, solve(i,j));
            }
        }

        System.out.println(maxValue);
    }

    static int solve(int row, int col) {

        if (dp[row][col] > 0) return dp[row][col];

        dp[row][col] = 1;
        for (int i = 0; i < 4; i++) {

            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
            if (map[nextRow][nextCol] <= map[row][col]) continue;

            dp[row][col] = Math.max(dp[row][col], solve(nextRow, nextCol) + 1);
        }

        return dp[row][col];
    }
}
