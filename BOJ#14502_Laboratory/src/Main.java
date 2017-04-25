import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#14502 연구소
 * https://www.acmicpc.net/problem/14502
 */

public class Main {

    static final int BLANK = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;
    static final int nADDWALL = 3;
    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    static int N, M;
    static int[][] map = new int[9][9];
    static int nWall = 0;
    static int safetyMaxArea = -1;

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
                if (map[i][j] == WALL) nWall++;
            }
        }

        // solve
        int[] wallPos = new int[nADDWALL];
        combination(wallPos, 0, 0, N * M, nADDWALL);

        System.out.println(safetyMaxArea);
    }

    static void combination(int[] arr, int depth, int target, int n, int k) {

        if (depth == k) {

            commitMap(arr);
            findSafetyArea();
            rollbackMap(arr);
            return;
        }

        if (target == n) {

            return;
        }

        // 벽을 세울 수 있는 위치에만
        if (map[target / M][target % M] == BLANK) {

            arr[depth] = target;
            combination(arr, depth + 1, target + 1, n, k);
        }
        combination(arr, depth, target + 1, n, k);
    }

    static void findSafetyArea() {

        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {

            Arrays.fill(visited[i], false);
        }

        int nVirusArea = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (map[i][j] == VIRUS && !visited[i][j]) {

                    nVirusArea += dfs(i, j, visited);
                }
            }
        }

        safetyMaxArea = Math.max(safetyMaxArea, N * M - (nVirusArea + nWall + nADDWALL));
    }

    static int dfs(int row, int col, boolean[][] visited) {

        visited[row][col] = true;

        int ret = 1;
        for (int i = 0; i < 4; i++) {

            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;

            if (map[nextRow][nextCol] != WALL && !visited[nextRow][nextCol]) {

                ret += dfs(nextRow, nextCol, visited);
            }
        }

        return ret;
    }

    static void commitMap(int[] arr) {

        for (int i = 0; i < nADDWALL; i++) {

            int row = arr[i] / M;
            int col = arr[i] % M;

            map[row][col] = WALL;
        }
    }

    static void rollbackMap(int[] arr) {

        for (int i = 0; i < nADDWALL; i++) {

            int row = arr[i] / M;
            int col = arr[i] % M;

            map[row][col] = BLANK;
        }
    }
}
