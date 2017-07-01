import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ#5427 불
 * https://www.acmicpc.net/problem/5427
 */

public class Main {

    static final int BLANK = 0;
    static final int WALL = -1;
    static final int FIRE = -2;

    static int h, w;
    static int[][] map = new int[1001][1001];
    static boolean[][] discovered = new boolean[1001][1001];
    static boolean[][] fireDiscovered = new boolean[1001][1001];
    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            Queue<Point> queue = new LinkedList<Point>();
            Queue<Point> fireQueue = new LinkedList<Point>();

            // input
            StringTokenizer st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            initMap();

            for (int i = 0; i < h; i++) {

                String s = br.readLine();
                for (int j = 0; j < w; j++) {

                    char c = s.charAt(j);

                    switch (c) {

                        case '.':
                            map[i][j] = BLANK;
                            break;

                        case '#':
                            map[i][j] = WALL;
                            break;

                        case '@':
                            map[i][j] = BLANK;
                            queue.add(new Point(i, j));
                            discovered[i][j] = true;
                            break;

                        case '*':
                            map[i][j] = FIRE;
                            fireQueue.add(new Point(i, j));
                            fireDiscovered[i][j] = true;
                            break;
                    }
                }
            }

            // solve
            int step = -1;
            boolean ret = false;

            while (!queue.isEmpty() && !ret) {

                // step 하나당 불과 상근이가 하나의 레벨만 bfs 탐색하도록 구성
                step++;

                // fire
                if (!fireQueue.isEmpty()) {

                    int size = fireQueue.size();
                    for (int i = 0; i < size; i++) {

                        Point v = fireQueue.poll();

                        for (int j = 0; j < 4; j++) {

                            int nextRowFire = v.row + dRow[j];
                            int nextColFire = v.col + dCol[j];

                            if (nextRowFire < 0 || nextRowFire >= h || nextColFire < 0 || nextColFire >= w) continue;
                            if (fireDiscovered[nextRowFire][nextColFire]) continue;
                            if (map[nextRowFire][nextColFire] == WALL) continue;

                            map[nextRowFire][nextColFire] = FIRE;
                            fireDiscovered[nextRowFire][nextColFire] = true;
                            fireQueue.add(new Point(nextRowFire, nextColFire));
                        }
                    }
                }

                // human
                int size = queue.size();
                for (int i = 0; i < size; i++) {

                    Point u = queue.poll();

                    if (u.row == 0 || u.col == 0 || u.row == h - 1 || u.col == w - 1) {

                        bw.write(step + 1 + "\n");
                        ret = true;
                        break;
                    }

                    for (int j = 0; j < 4; j++) {

                        int nextRow = u.row + dRow[j];
                        int nextCol = u.col + dCol[j];

                        if (nextRow < 0 || nextRow >= h || nextCol < 0 || nextCol >= w) continue;
                        if (discovered[nextRow][nextCol]) continue;
                        if (map[nextRow][nextCol] == WALL || map[nextRow][nextCol] == FIRE) continue;

                        discovered[nextRow][nextCol] = true;
                        queue.add(new Point(nextRow, nextCol));
                    }
                }
            }

            if (!ret) bw.write("IMPOSSIBLE\n");
        } // ~ test case loop

        bw.flush();
        bw.close();
        br.close();
    }

    static void initMap() {

        for (int i = 0; i < h; i++) {

            Arrays.fill(map[i], BLANK);
            Arrays.fill(discovered[i], false);
            Arrays.fill(fireDiscovered[i], false);
        }
    }
}

class Point {

    int row, col;

    Point(int row, int col) {

        this.row = row;
        this.col = col;
    }
}