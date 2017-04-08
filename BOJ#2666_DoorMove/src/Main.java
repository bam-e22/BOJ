import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#2666 벽장문의 이동
 * https://www.acmicpc.net/problem/2666
 */

public class Main {

    static int n; // 벽장의 개수
    static int m; // 사용할 벽장의 개수
    static int[] opened = new int[2];
    static int[] order;

    // dp[pos1][pos2][depth] : 열린문이 pos1, pos2에 위치하고 사용한 벽장문의 개수가 idx일때, 벽장문을 모두 사용하는데 필요한 최소 이동 수
    static int[][][] dp = new int[21][21][21];

    static final int NOT_VISITED = -1;

    static {

        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {

                Arrays.fill(dp[i][j], NOT_VISITED);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        opened[0] = Integer.parseInt(st.nextToken());
        opened[1] = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine());
        order = new int[m];
        for (int i = 0; i < m; i++) {

            order[i] = Integer.parseInt(br.readLine());
        }

        // solve
        System.out.println(solve(opened[0], opened[1], 0));
    }

    // return : dp[firstPos][secondPos][idx]
    static int solve(int firstPos, int secondPos, int idx) {

        // 기저 조건
        if (idx == m) {

            return 0;
        }

        // memoization
        if (dp[firstPos][secondPos][idx] != NOT_VISITED) return dp[firstPos][secondPos][idx];

        // 탐색
        dp[firstPos][secondPos][idx] = Math.min(solve(order[idx], secondPos, idx + 1) + Math.abs((order[idx] - firstPos)),
                solve(firstPos, order[idx], idx + 1) + Math.abs((order[idx] - secondPos)));

        return dp[firstPos][secondPos][idx];
    }
}
