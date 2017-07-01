import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#14500 테트로미노
 * https://www.acmicpc.net/problem/14500
 */

public class Main {

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};
    static final int LIMIT = 4;
    static final int INF = 100000000;

    static int N, M;
    static int[][] map = new int[501][501];
    static boolean[][] visited = new boolean[501][501];

    static int maxValue = 0;

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // solve
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                // 1. dfs로 탐색 가능한 테트로미노
                dfs(0, i, j, 0);

                // 2. dfs로 탐색 불가능한 테트로미노
                solve(i, j);
            }
        }

        System.out.println(maxValue);
    }

    static void dfs(int depth, int row, int col, int sum) {

        if (depth == LIMIT - 1) {

            sum += map[row][col];

            maxValue = maxValue < sum ? sum : maxValue;
            return;
        }

        visited[row][col] = true;

        for (int i = 0; i < 4; i++) {

            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
            if (visited[nextRow][nextCol]) continue;

            dfs(depth + 1, nextRow, nextCol, sum + map[row][col]);
        }

        visited[row][col] = false;
    }

    static void solve(int row, int col) {

        int[] adjValue = {-1, -1, -1, -1};

        int nInvalidity = 0;
        int minValue = INF;
        int sum = 0;
        for (int i = 0; i < 4; i++) {

            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {

                nInvalidity++;
                continue;
            }

            adjValue[i] = map[nextRow][nextCol];

            minValue = minValue > adjValue[i] ? adjValue[i] : minValue;
            sum += adjValue[i];
        }

        int ret = 0;
        if (nInvalidity >= 2) {

            return;
        } else if (nInvalidity == 1) {

            ret = sum + map[row][col];
        } else if (nInvalidity == 0) {

            ret = sum + map[row][col] - minValue;
        }

        maxValue = maxValue < ret ? ret : maxValue;
    }
}