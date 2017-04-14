import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#2638 치즈
 * https://www.acmicpc.net/problem/2638
 */

public class Main {

    static int N, M;
    static int[][] map = new int[101][101];
    static boolean[][] visited = new boolean[101][101];

    static final int NONE = 0;
    static final int OUTER_AIR = 2;
    static final int CHEESE = 1;

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    static final boolean DEBUG = true;

    public static void main(String[] args) throws IOException {

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

        int step = -1;
        boolean ret = false;
        while (!ret) {

            step++;

            init();
            dfs(0, 0);

            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {

                    if (map[i][j] == CHEESE && checkCheese(i, j)) {

                        cnt++;
                    }
                }
            }
            // 종료
            if (cnt == 0) {

                ret = true;
                System.out.println(step);
            }
        }
    }

    static void dfs(int row, int col) {

        map[row][col] = OUTER_AIR;
        visited[row][col] = true;

        for (int i = 0; i < 4; i++) {

            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
            if (visited[nextRow][nextCol]) continue;
            if (map[nextRow][nextCol] == CHEESE) continue;

            dfs(nextRow, nextCol);
        }
    }

    static void init() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                visited[i][j] = false;
            }
        }
    }

    static boolean checkCheese(int row, int col) {

        int cnt = 0;
        for (int i = 0; i < 4; i++) {

            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
            if (map[nextRow][nextCol] == OUTER_AIR) cnt++;
        }

        if (cnt >= 2) {

            map[row][col] = NONE;
            return true;
        }
        return false;
    }

    static void printMap() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                System.out.printf("%4d", map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}

class Point {

    int row, col;

    Point(int row, int col) {

        this.row = row;
        this.col = col;
    }
}