import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ#1600 말이 되고픈 원숭이
 * https://www.acmicpc.net/problem/1600
 */

public class Main {

    static final int BLANK = 0;
    static final int OBSTACLE = 1;

    static final int MONKEY = 0;
    static final int HORSE = 1;

    static final int[] dRowMonkey = {0, -1, 0, 1};
    static final int[] dColMonkey = {-1, 0, 1, 0};

    static final int[] dRowHorse = {-1, -2, -2, -1, 1, 2, 2, 1};
    static final int[] dColHorse = {-2, -1, 1, 2, 2, 1, -1, -2};

    static int K;
    static int W, H;
    static int[][] map = new int[201][201];
    static boolean[][][] discovered = new boolean[201][201][31];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        for (int i = 0; i < H; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int step = -1;
        Queue<Point> queue = new LinkedList<Point>();

        queue.add(new Point(0, 0, K));
        discovered[0][0][MONKEY] = true;
        discovered[0][0][HORSE] = true;

        while (!queue.isEmpty()) {

            step++;

            int size = queue.size();
            for (int i = 0; i < size; i++) {

                Point u = queue.poll();

                if (u.row == H - 1 && u.col == W - 1) {

                    System.out.println(step);
                    return;
                }
                // MONEY
                for (int j = 0; j < 4; j++) {

                    int nextRow = u.row + dRowMonkey[j];
                    int nextCol = u.col + dColMonkey[j];

                    if (nextRow < 0 || nextRow >= H || nextCol < 0 || nextCol >= W) continue;
                    if (discovered[nextRow][nextCol][u.k]) continue;
                    if (map[nextRow][nextCol] == OBSTACLE) continue;

                    queue.add(new Point(nextRow, nextCol, u.k));
                    discovered[nextRow][nextCol][u.k] = true;
                }

                // HORSE
                if (u.k == 0) continue;
                for (int j = 0; j < 8; j++) {

                    int nextRow = u.row + dRowHorse[j];
                    int nextCol = u.col + dColHorse[j];

                    if (nextRow < 0 || nextRow >= H || nextCol < 0 || nextCol >= W) continue;
                    if (discovered[nextRow][nextCol][u.k - 1]) continue;
                    if (map[nextRow][nextCol] == OBSTACLE) continue;

                    queue.add(new Point(nextRow, nextCol, u.k - 1));
                    discovered[nextRow][nextCol][u.k - 1] = true;
                }
            }
        } // ~ while loop

        System.out.println(-1);
    }
}

class Point {

    int row, col;
    int k;

    Point(int row, int col, int k) {

        this.row = row;
        this.col = col;
        this.k = k;
    }
}
