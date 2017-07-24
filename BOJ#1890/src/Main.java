import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int[] dRow = {0, 1};
    static final int[] dCol = {1, 0};

    static int[][] map = new int[101][101];
    static long[][] dp = new long[101][101];

    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }

    // return dp[row][col]
    static long dfs(int row, int col) {

        // 기저조건
        if (row == N - 1 && col == N - 1) return 1;

        // memoization
        if (dp[row][col] > -1) return dp[row][col];

        // 재귀 탐색
        dp[row][col] = 0L;

        for (int i = 0; i < 2; i++) {

            int nextRow = row + map[row][col] * dRow[i];
            int nextCol = col + map[row][col] * dCol[i];

            dp[row][col] += dfs(nextRow, nextCol);
        }

        return dp[row][col];
    }
}
