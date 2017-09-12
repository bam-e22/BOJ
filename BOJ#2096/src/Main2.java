import java.util.Arrays;

/**
 * Created by mymac on 2017. 9. 12..
 */
public class Main2 {

    static int N;
    static int[][] map = new int[100001][3];

    static int[][] dp = new int[100001][3];

    static final int INF = 100000000;

    // Solve Recursive
        /*
        int min = solveMinRecursive(0, 1);
        initDP();
        int max = solveMaxRecursive(0, 1);

        System.out.println(max + " " + min);
        */

    static int solveMinRecursive(int row, int prevCol) {

        if (row == N) return 0;

        if (dp[row][prevCol] != -1) return dp[row][prevCol];

        dp[row][prevCol] = INF;

        for (int col = 0; col < 3; col++) {

            if (Math.abs(col - prevCol) <= 1) {

                dp[row][prevCol] = Math.min(dp[row][prevCol], solveMinRecursive(row + 1, col) + map[row][col]);
            }
        }

        return dp[row][prevCol];
    }

    static int solveMaxRecursive(int row, int prevCol) {

        if (row == N) return 0;

        if (dp[row][prevCol] != -1) return dp[row][prevCol];

        dp[row][prevCol] = 0;

        for (int col = 0; col < 3; col++) {

            if (Math.abs(col - prevCol) <= 1) {

                dp[row][prevCol] = Math.max(dp[row][prevCol], solveMaxRecursive(row + 1, col) + map[row][col]);
            }
        }

        return dp[row][prevCol];
    }

    static void initDP() {

        for (int i = 0; i < 100001; i++) {

            Arrays.fill(dp[i], -1);
        }
    }
}
