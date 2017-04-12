import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ#1726 로봇
 * https://www.acmicpc.net/problem/1726
 */

public class Main {

    static final int START = 0;
    static final int END = 1;

    static final int WEST = 0;
    static final int NORTH = 1;
    static final int EAST = 2;
    static final int SOUTH = 3;

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {

        int nRow, nCol;
        Point[] point = new Point[2];
        int[][] map;
        boolean[][][] discovered;

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        nRow = Integer.parseInt(st.nextToken());
        nCol = Integer.parseInt(st.nextToken());

        map = new int[nRow + 1][nCol + 1];
        discovered = new boolean[nRow + 1][nCol + 1][4];

        for (int i = 0; i < nRow + 1; i++) {
            for (int j = 0; j < nCol + 1; j++) {

                Arrays.fill(discovered[i][j], false);
            }
        }

        for (int i = 1; i < nRow + 1; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < nCol + 1; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 2; i++) {

            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            // 동쪽이 1, 서쪽이 2, 남쪽이 3, 북쪽이 4
            // 동쪽이 2, 서쪽이 0, 남쪽이 3, 북쪽이 2
            point[i] = new Point(s, e, d == 1 ? EAST : d == 2 ? WEST : d == 4 ? NORTH : SOUTH);
        }

        // solve
        Queue<Point> queue = new LinkedList<Point>();
        int step = -1;

        queue.add(point[START]);
        discovered[point[START].row][point[START].col][point[START].dir] = true;

        while (!queue.isEmpty()) {

            step++;

            int size = queue.size();
            for (int i = 0; i < size; i++) {

                Point u = queue.poll();

                if (u.row == point[END].row && u.col == point[END].col) {

                    if (u.dir == point[END].dir) {

                        System.out.println(step);
                        return;
                    }
                }

                // 명령 1. Go k
                // k는 1, 2 또는 3 일 수 있다.현재 향하고 있는 방향으로 k칸 만큼 움직인다.
                for (int j = 1; j <= 3; j++) {

                    int nextRow = u.row + j * dRow[u.dir];
                    int nextCol = u.col + j * dCol[u.dir];

                    if (nextRow <= 0 || nextRow > nRow || nextCol <= 0 || nextCol > nCol) continue;
                    if (discovered[nextRow][nextCol][u.dir]) continue;
                    if (map[nextRow][nextCol] == 1) break;

                    queue.add(new Point(nextRow, nextCol, u.dir));
                    discovered[nextRow][nextCol][u.dir] = true;
                }

                // 명령 2. Turn dir
                // dir은 left 또는 right 이며, 각각 왼쪽 또는 오른쪽으로 90° 회전한다.
                int nextDir = u.dir + 1 > 3 ? 0 : u.dir + 1;

                if (discovered[u.row][u.col][nextDir]) continue;

                queue.add(new Point(u.row, u.col, nextDir));
                discovered[u.row][u.col][nextDir] = true;

                nextDir = u.dir - 1 < 0 ? 3 : u.dir - 1;
                if (discovered[u.row][u.col][nextDir]) continue;

                queue.add(new Point(u.row, u.col, nextDir));
                discovered[u.row][u.col][nextDir] = true;

            }
        }
    }
}

class Point {

    int row, col;
    int dir;

    Point(int row, int col, int dir) {

        this.row = row;
        this.col = col;
        this.dir = dir;
    }
}