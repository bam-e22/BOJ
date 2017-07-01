import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ#3055 탈출
 * https://www.acmicpc.net/problem/3055
 */

public class Main {

    static final int BLANK = 0;
    static final int WATER = -1;
    static final int STONE = -2;
    static final int CAVE = 1;

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    static int[][][] map = new int[51][51][2000];
    static boolean[][] waterDiscovered = new boolean[51][51];
    static boolean[][] hogDiscovered = new boolean[51][51];

    public static void main(String[] args) throws IOException {

        int R, C;
        ArrayList<Point> waterPos = new ArrayList<Point>();
        Point hogPos = null;

        // 1. input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for (int i = 0; i < R; i++) {

            String lineInfo = br.readLine();
            for (int j = 0; j < C; j++) {

                char c = lineInfo.charAt(j);

                switch (c) {

                    case '.': // 빈칸
                        map[i][j][0] = BLANK;
                        break;

                    case '*': // 물
                        map[i][j][0] = WATER;
                        waterPos.add(new Point(i, j));
                        break;

                    case 'X': // 돌
                        map[i][j][0] = STONE;
                        break;

                    case 'D': // 동굴
                        map[i][j][0] = CAVE;
                        break;

                    case 'S': // 고슴도치 위치
                        map[i][j][0] = BLANK;
                        hogPos = new Point(i, j);
                        break;
                }
            }
        }

        // 2. water
        Queue<Point> waterQueue = new LinkedList<Point>();

        for (Point x : waterPos) {

            waterQueue.add(x);
            waterDiscovered[x.row][x.col] = true;
        }

        int step = 0;
        while (!waterQueue.isEmpty()) {

            step++;

            if (step > 0) {

                for (int x = 0; x < R; x++) {

                    for (int y = 0; y < C; y++) {

                        map[x][y][step] = map[x][y][step - 1];
                    }
                }
            }

            int size = waterQueue.size();
            for (int i = 0; i < size; i++) {

                Point u = waterQueue.poll();

                // 상, 하, 좌, 우
                for (int j = 0; j < 4; j++) {

                    int nextRow = u.row + dRow[j];
                    int nextCol = u.col + dCol[j];

                    // boundary check
                    if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C) continue;

                    // visited check
                    if (waterDiscovered[nextRow][nextCol]) continue;

                    // cave, stone check
                    if (map[nextRow][nextCol][0] == STONE || map[nextRow][nextCol][0] == CAVE) continue;

                    map[nextRow][nextCol][step] = WATER;
                    waterDiscovered[nextRow][nextCol] = true;

                    waterQueue.add(new Point(nextRow, nextCol));
                }
            }
        }

        // 3. hedgehog
        Queue<Point> hogQueue = new LinkedList<Point>();

        hogQueue.add(hogPos);
        hogDiscovered[hogPos.row][hogPos.col] = true;

        step = 0;
        boolean exit = false;
        while (!hogQueue.isEmpty() && !exit) {

            step++;

            int size = hogQueue.size();
            for (int i = 0; i < size && !exit; i++) {

                Point u = hogQueue.poll();

                // 상, 하, 좌, 우
                for (int j = 0; j < 4; j++) {

                    int nextRow = u.row + dRow[j];
                    int nextCol = u.col + dCol[j];

                    // boundary check
                    if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C) continue;

                    // visited check
                    if (hogDiscovered[nextRow][nextCol]) continue;

                    // stone check
                    if (map[nextRow][nextCol][0] == STONE) continue;

                    // water check
                    if (map[nextRow][nextCol][step] == WATER) continue;

                    // cave check
                    if (map[nextRow][nextCol][0] == CAVE) {

                        // 4. output
                        exit = true;
                        System.out.println(step);
                        break;
                    }

                    hogDiscovered[nextRow][nextCol] = true;

                    hogQueue.add(new Point(nextRow, nextCol));
                }

            }
        }

        // 4. output
        if (!exit) {

            System.out.println("KAKTUS");
        }
    }
}

class Point {

    int row;
    int col;

    Point(int row, int col) {

        this.row = row;
        this.col = col;
    }
}