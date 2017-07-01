import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#13459 쨰로탈출
 * https://www.acmicpc.net/problem/13459
 *
 * 연습코드이므로 13460 코드를 볼 것
 * https://github.com/stack07142/BOJ/blob/master/BOJ%2313460_XHEscape/src/Main.java
 */

public class Main {

    // map info
    static int N, M;
    static int[][] map = new int[11][11];

    static final int WALL = -1;
    static final int BLANK = 0;
    static final int HOLE = -10;

    // direction
    static final int LEFT = 0;
    static final int UP = 1;
    static final int RIGHT = 2;
    static final int DOWN = 3;

    // game info
    static final int LIMIT = 10;
    static final int NONE = 0;
    static final int SUCCESS = 1;
    static final int FAIL = 2;
    static int ret = NONE;

    static final int INF = 10000;

    static final boolean DEBUG = false;

    public static void main(String[] args) throws IOException {

        int rRow = -1, rCol = -1;
        int bRow = -1, bCol = -1;

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

                    case '#':
                        map[i][j] = WALL;
                        break;

                    case '.':
                        map[i][j] = BLANK;
                        break;

                    case 'O':
                        map[i][j] = HOLE;
                        break;

                    case 'R':
                        map[i][j] = BLANK;
                        rRow = i;
                        rCol = j;
                        break;

                    case 'B':
                        map[i][j] = BLANK;
                        bRow = i;
                        bCol = j;
                        break;

                }
            }
        } // ~ input

        if (DEBUG) {

            String cmd = "";
            while (!cmd.equals("exit")) {

                cmd = br.readLine();

                controlBlock(Integer.parseInt(cmd), rRow, rCol, bRow, bCol);

            }
        }
        // solve - 2^20가지 경로에 대해 검사
        // 1. makePath() : return path;
        // 2. simulate(path)
        // 3. action(dir) : return PointInfo, ret
        // 4. checkValid() : return step;

        int minStep = INF;
        for (int k = 0; k < (1 << LIMIT * 2); k++) {

            int[] path = makePath(k);

            minStep = Math.min(minStep, simulate(path, rRow, rCol, bRow, bCol));
        }

        // 13460
        //System.out.println(minStep != INF ? minStep + 1 : -1);
        // 13459
        System.out.println(minStep < LIMIT ? 1 : 0);

    } // ~main

    // func - controlBolck
    static void controlBlock(int dir, int rRow, int rCol, int bRow, int bCol) {

        action(dir, rRow, rCol, bRow, bCol);
        printMap();

    }

    // func - makePath
    static int[] makePath(int k) {

        int[] path = new int[LIMIT];

        for (int i = 0; i < LIMIT; i++) {

            path[i] = k & 3;
            k >>= 2;
        }

        return path;
    }

    // func - simulate
    static int simulate(int[] path, int rRow, int rCol, int bRow, int bCol) {

        PointInfo pointInfo = new PointInfo(rRow, rCol, bRow, bCol);

        for (int i = 0; i < LIMIT; i++) {

            pointInfo = action(path[i], pointInfo.rRow, pointInfo.rCol, pointInfo.bRow, pointInfo.bCol);

            // ret ?
            if (ret != NONE) {

                // success
                if (ret == SUCCESS) {

                    ret = NONE;
                    return i;

                }
                // fail
                else {

                    ret = NONE;
                    return INF;
                }
            }
        }

        return INF;
    }

    // func - action
    static PointInfo action(int dir, int rRow, int rCol, int bRow, int bCol) {

        PointInfo prevInfo = new PointInfo(rRow, rCol, bRow, bCol);

        if (DEBUG) System.out.println("#action dir = " + dir);

        switch (dir) {

            case LEFT:

                // red ball
                while (map[rRow][rCol - 1] != WALL) {

                    rCol--;
                    if (map[rRow][rCol] == HOLE) {

                        ret = ret > NONE ? ret : SUCCESS;
                        break;
                    }
                }

                // blue ball
                while (map[bRow][bCol - 1] != WALL) {

                    bCol--;
                    if (map[bRow][bCol] == HOLE) {

                        ret = FAIL;
                        break;
                    }
                }

                // after move
                if (ret == NONE && (rRow == bRow) && (rCol == bCol)) {

                    if (prevInfo.rCol < prevInfo.bCol) {

                        bCol++;
                    } else {

                        rCol++;
                    }
                }
                break;

            case UP:

                // red ball
                while (map[rRow - 1][rCol] != WALL) {

                    rRow--;
                    if (map[rRow][rCol] == HOLE) {

                        ret = ret > NONE ? ret : SUCCESS;
                        break;
                    }
                }

                // blue ball
                while (map[bRow - 1][bCol] != WALL) {

                    bRow--;
                    if (map[bRow][bCol] == HOLE) {

                        ret = FAIL;
                        break;
                    }
                }

                // after move
                if (ret == NONE && (rRow == bRow) && (rCol == bCol)) {

                    if (prevInfo.rRow < prevInfo.bRow) {

                        bRow++;
                    } else {

                        rRow++;
                    }
                }
                break;

            case RIGHT:

                // red ball
                while (map[rRow][rCol + 1] != WALL) {

                    rCol++;
                    if (map[rRow][rCol] == HOLE) {

                        ret = ret > NONE ? ret : SUCCESS;
                        break;
                    }
                }

                // blue ball
                while (map[bRow][bCol + 1] != WALL) {

                    bCol++;
                    if (map[bRow][bCol] == HOLE) {

                        ret = FAIL;
                        break;
                    }
                }

                // after move
                if (ret == NONE && (rRow == bRow) && (rCol == bCol)) {

                    if (prevInfo.rCol < prevInfo.bCol) {

                        rCol--;
                    } else {

                        bCol--;
                    }
                }
                break;

            case DOWN:

                // red ball
                while (map[rRow + 1][rCol] != WALL) {

                    rRow++;
                    if (map[rRow][rCol] == HOLE) {

                        ret = ret > NONE ? ret : SUCCESS;
                        break;
                    }
                }

                // blue ball
                while (map[bRow + 1][bCol] != WALL) {

                    bRow++;
                    if (map[bRow][bCol] == HOLE) {

                        ret = FAIL;
                        break;
                    }
                }

                // after move
                if (ret == NONE && (rRow == bRow) && (rCol == bCol)) {

                    if (prevInfo.rRow < prevInfo.bRow) {

                        rRow--;
                    } else {

                        bRow--;
                    }
                }
                break;
        }

        return new PointInfo(rRow, rCol, bRow, bCol);
    }

    // func - printMap
    static void printMap() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                System.out.printf("%4d", map[i][j]);
            }
            System.out.println();
        }
    }
}

class PointInfo {

    int rRow, rCol;
    int bRow, bCol;

    PointInfo(int rRow, int rCol, int bRow, int bCol) {

        this.rRow = rRow;
        this.rCol = rCol;
        this.bRow = bRow;
        this.bCol = bCol;
    }
}
