import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BOJ#1938 통나무 옮기기
 * https://www.acmicpc.net/problem/1938
 */

public class Main {

    static final int BLANK = 0;
    static final int TREE = 1;
    static final int TARGET = 2;

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    static final int NONE = 0;
    static final int VERTICAL = 0;
    static final int HORIZON = 1;

    static int N;
    //static int[][] map = new int[52][52];
    static int[][] map = new int[102][102];
    //static boolean[][][] discovered = new boolean[52][52][2];
    static boolean[][][] discovered = new boolean[102][102][2];

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // 통나무의 중심과 방향을 판단한다
        Point log = null;
        int bCnt = 0;

        Point target = null;
        int targetCnt = 0;

        for (int i = 0; i < N; i++) {

            String s = br.readLine();
            for (int j = 0; j < N; j++) {

                char c = s.charAt(j);

                switch (c) {

                    case '0':
                        map[i][j] = BLANK;
                        break;

                    case '1':
                        map[i][j] = TREE;
                        break;

                    case 'B':
                        map[i][j] = BLANK;
                        bCnt++;
                        if (bCnt == 1) {

                            log = new Point(i, j, NONE);
                        } else if (bCnt == 2) {

                            if (log.row == i) {

                                log = new Point(i, j, HORIZON);
                            } else {

                                log = new Point(i, j, VERTICAL);
                            }
                        }
                        break;

                    case 'E':
                        map[i][j] = TARGET;

                        targetCnt++;
                        if (targetCnt == 1) {

                            target = new Point(i, j, NONE);
                        } else if (targetCnt == 2) {

                            if (target.row == i) {

                                target = new Point(i, j, HORIZON);
                            } else {

                                target = new Point(i, j, VERTICAL);
                            }
                        }
                        break;
                }
            }
        }

        // solve
        Queue<Point> queue = new LinkedList<Point>();
        int step = -1;
        boolean ret = false;

        queue.add(log);
        discovered[log.row][log.col][log.dir] = true;

        while (!queue.isEmpty() && !ret) {

            step++;

            int size = queue.size();
            for (int i = 0; i < size; i++) {

                Point u = queue.poll();
                // 종료 조건
                if (map[u.row][u.col] == TARGET) {

                    if (u.row == target.row && u.col == target.col && u.dir == target.dir) {

                        System.out.println(step);
                        ret = true;
                        break;
                    }
                }

                for (int j = 0; j < 4; j++) {

                    int nextRow = u.row + dRow[j];
                    int nextCol = u.col + dCol[j];

                    // 상하좌우 - 수평일 경우
                    if (u.dir == HORIZON) {

                        if (nextRow < 0 || nextRow >= N || nextCol - 1 < 0 || nextCol + 1 >= N) continue;
                        if (discovered[nextRow][nextCol][u.dir]) continue;
                        if (map[nextRow][nextCol] == TREE || map[nextRow][nextCol - 1] == TREE || map[nextRow][nextCol + 1] == TREE)
                            continue;

                        queue.add(new Point(nextRow, nextCol, u.dir));
                        discovered[nextRow][nextCol][u.dir] = true;
                    }

                    // 상하좌우 - 수직일 경우
                    if (u.dir == VERTICAL) {

                        if (nextRow - 1 < 0 || nextRow + 1 >= N || nextCol < 0 || nextCol >= N) continue;
                        if (discovered[nextRow][nextCol][u.dir]) continue;
                        if (map[nextRow][nextCol] == TREE || map[nextRow - 1][nextCol] == TREE || map[nextRow + 1][nextCol] == TREE)
                            continue;

                        queue.add(new Point(nextRow, nextCol, u.dir));
                        discovered[nextRow][nextCol][u.dir] = true;
                    }
                }

                // 회전하는 경우
                if (u.row - 1 < 0 || u.row + 1 >= N || u.col - 1 < 0 || u.col + 1 >= N) continue;
                if (discovered[u.row][u.col][u.dir ^ 1]) continue;

                boolean rotateFree = true;
                for (int j = u.row - 1; j <= u.row + 1 && rotateFree; j++) {

                    for (int k = u.col - 1; k <= u.col + 1; k++) {

                        if (map[j][k] == TREE) {

                            rotateFree = false;
                            break;
                        }
                    }
                }

                if (rotateFree) {

                    u.dir ^= 1;
                    queue.add(u);
                    discovered[u.row][u.col][u.dir] = true;
                }
            }
        } // ~ while loop

        if (!ret) System.out.println(0);
    } // ~ main
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