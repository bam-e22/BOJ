import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ#1194 달이 차오른다, 가자.
 * https://www.acmicpc.net/problem/1194
 */

public class Main {

    static final int BLANK = 0;
    static final int WALL = -1;
    static final int EXIT = -2;

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};
    static final int LEFT = 0;
    static final int UP = 1;
    static final int RIGHT = 2;
    static final int DOWN = 3;

    static final int a = 1;
    static final int A = 10;
    static final int f = 6;
    static final int F = 60;

    static int N, M;
    static int[][] map = new int[51][51];
    static boolean[][][] discovered = new boolean[51][51][(1 << 7)];

    public static void main(String[] args) throws IOException {

        int startRow = -1, startCol = -1;

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {

            String s = br.readLine();
            for (int j = 0; j < M; j++) {

                char c = s.charAt(j);

                switch (c) {

                    case '.':
                        map[i][j] = BLANK;
                        break;

                    case '#':
                        map[i][j] = WALL;
                        break;

                    case '1':
                        map[i][j] = EXIT;
                        break;

                    case '0':
                        map[i][j] = BLANK;
                        startRow = i;
                        startCol = j;
                        break;

                    case 'a':
                    case 'b':
                    case 'c':
                    case 'd':
                    case 'e':
                    case 'f':
                        map[i][j] = (c - 'a' + 1);
                        break;

                    case 'A':
                    case 'B':
                    case 'C':
                    case 'D':
                    case 'E':
                    case 'F':
                        map[i][j] = (c - 'A' + 1) * 10;
                        break;
                }
            }
        }

        // solve
        Queue<Point> queue = new LinkedList<Point>();
        int step = -1;
        boolean ret = false;

        queue.add(new Point(startRow, startCol, 0));

        while (!queue.isEmpty() && !ret) {

            step++;

            int size = queue.size();
            for (int i = 0; i < size; i++) {

                Point u = queue.poll();

                if (map[u.row][u.col] >= a && map[u.row][u.col] <= f) {

                    u.key |= 1 << map[u.row][u.col];
                }

                if (map[u.row][u.col] >= A && map[u.row][u.col] <= F) {

                    if ((u.key & (1 << (map[u.row][u.col] / 10))) == 0) {

                        discovered[u.row][u.col][u.key] = true;
                        continue;
                    }

                }

                if (map[u.row][u.col] == EXIT) {

                    System.out.println(step);
                    ret = true;
                    break;
                }

                for (int j = 0; j < 4; j++) {

                    int nextRow = u.row + dRow[j];
                    int nextCol = u.col + dCol[j];

                    if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
                    if (discovered[nextRow][nextCol][u.key]) continue;
                    if (map[nextRow][nextCol] == WALL) continue;

                    queue.add(new Point(nextRow, nextCol, u.key));
                    discovered[nextRow][nextCol][u.key] = true;
                }
            }
        }

        if (!ret) System.out.println(-1);
    }
}

class Point {

    int row, col;
    int key;

    Point(int row, int col, int key) {

        this.row = row;
        this.col = col;
        this.key = key;
    }
}