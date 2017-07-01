import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ#7569 토마토
 * https://www.acmicpc.net/problem/7569
 */

public class Main {

    static final int RIPE = 1;
    static final int UNRIPE = 0;
    static final int BLANK = -1;
    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    static int N, M, H;
    static int[][][] map = new int[101][101][101];
    static boolean[][][] discovered = new boolean[101][101][101];

    public static void main(String[] args) throws IOException {

        Queue<Point> queue = new LinkedList<Point>();

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        boolean isAllRipe = true;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {

                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {

                    int tomato = Integer.parseInt(st.nextToken());
                    map[i][j][k] = tomato;

                    if (tomato == RIPE) {

                        queue.add(new Point(i, j, k));
                        discovered[i][j][k] = true;
                    } else if (tomato == UNRIPE) {

                        isAllRipe = false;
                    }
                }
            }
        }

        if (isAllRipe) {

            System.out.println(0);
            return;
        }

        // solve
        int step = -1;

        while (!queue.isEmpty()) {

            step++;

            int size = queue.size();
            for (int i = 0; i < size; i++) {

                Point u = queue.poll();

                // 같은층 상자
                for (int j = 0; j < 4; j++) {

                    int nextRow = u.row + dRow[j];
                    int nextCol = u.col + dCol[j];

                    if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
                    if (discovered[u.h][nextRow][nextCol]) continue;
                    if (map[u.h][nextRow][nextCol] == BLANK) continue;

                    queue.add(new Point(u.h, nextRow, nextCol));
                    map[u.h][nextRow][nextCol] = RIPE;
                    discovered[u.h][nextRow][nextCol] = true;
                }

                // 위층 상자
                if (u.h + 1 < H) {

                    if (!discovered[u.h + 1][u.row][u.col] && map[u.h + 1][u.row][u.col] != BLANK) {

                        queue.add(new Point(u.h + 1, u.row, u.col));
                        map[u.h + 1][u.row][u.col] = RIPE;
                        discovered[u.h + 1][u.row][u.col] = true;
                    }
                }

                // 아래층 상자
                if (u.h - 1 >= 0) {

                    if (!discovered[u.h - 1][u.row][u.col] && map[u.h - 1][u.row][u.col] != BLANK) {

                        queue.add(new Point(u.h - 1, u.row, u.col));
                        map[u.h - 1][u.row][u.col] = RIPE;
                        discovered[u.h - 1][u.row][u.col] = true;
                    }
                }
            }
        }

        System.out.println(checkTomato() ? step : -1);
    } // ~main

    static boolean checkTomato() {

        boolean ret = true;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {

                    if (map[i][j][k] == UNRIPE) return ret = false;
                }
            }
        }

        return ret;
    }
}

class Point {

    int h, row, col;

    Point(int h, int row, int col) {

        this.h = h;
        this.row = row;
        this.col = col;
    }
}
