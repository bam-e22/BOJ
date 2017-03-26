import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#13460 째로탈출
 * https://www.acmicpc.net/problem/13460
 */

public class Main2 {

    // map info
    static int N, M; // 격자의 크기
    static final int WALL = -1;
    static final int BLANK = 0;
    static final int RED = 1;
    static final int BLUE = 2;
    static final int HOLE = -10;
    static final int LIMIT = 10;
    static int[][][][] dp = new int[11][11][11][11];

    // direction
    static final int LEFT = 0;
    static final int UP = 1;
    static final int RIGHT = 2;
    static final int DOWN = 3;

    // result
    static final int NONE = 0;
    static final int SUCCESS = 1;
    static final int FAIL = 2;
    static final int INF = 11;
    static int ret = NONE;

    static int ans = INF;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {

            String rowInfo = br.readLine();

            for (int j = 0; j < M; j++) {

                char inputChar = rowInfo.charAt(j);

                switch (inputChar) {

                    case '#':
                        map[i][j] = WALL;
                        break;

                    case '.':
                        map[i][j] = BLANK;
                        break;

                    case 'R':
                        map[i][j] = RED;
                        break;

                    case 'B':
                        map[i][j] = BLUE;
                        break;

                    case 'O':
                        map[i][j] = HOLE;
                        break;
                }
            }
        }

        dfs(1, map);

        System.out.println(ans >= INF ? -1 : ans);

/*

        // Test Module
        printMap(map);
        while (ret != SUCCESS) {
            map = action(Integer.parseInt(br.readLine()), map);
            printMap(map);
            System.out.println("Result = " + ret);
        }
*/

    } // main

    static void dfs(int step, int[][] map) {

        if (step > LIMIT) {

            return;
        }

        for (int direction = 0; direction < 4; direction++) {

            int[][] mapCopy = new int[N][M];
            for (int j = 0; j < N; j++) {

                mapCopy[j] = Arrays.copyOf(map[j], M);
            }

            mapCopy = action(direction, mapCopy);

/*
            printMap(mapCopy);
            System.out.println("#STEP = " + step + ", action=" + direction);
            System.out.println("#ret = " + ret);
*/

            if (ret > 0) {

                //System.out.println("step=" + step);

                if (ret == SUCCESS) {

                    ans = step < ans ? step : ans;
                }

                ret = NONE;
                continue;
            }

            dfs(step + 1, mapCopy);
        }
    }

    static int[][] action(int dir, int[][] map) {

        int nBlank;

        switch (dir) {

            case LEFT:

                for (int i = 1; i < N; i++) {

                    nBlank = 0;
                    for (int j = 1; j < M - 1; j++) {

                        // count blank & action
                        if (map[i][j] == BLANK) nBlank++;
                        else if (map[i][j] == HOLE) nBlank = 1;
                        else nBlank = 0;

                        // action
                        if (map[i][j + 1] > 0) {

                            if (map[i][j + 1 - nBlank] == HOLE) {

                                if (map[i][j + 1] == RED) {

                                    ret = ret > NONE ? ret : SUCCESS;
                                } else {

                                    ret = FAIL;
                                }

                                map[i][j + 1] = BLANK;
                            } else {
                                if (nBlank != 0) {

                                    map[i][j + 1 - nBlank] = map[i][j + 1];
                                    map[i][j + 1] = BLANK;

                                    nBlank--;
                                }
                            }
                        }
                    }
                }
                break;

            case UP:

                for (int j = 1; j < M; j++) {
                    nBlank = 0;
                    for (int i = 1; i < N - 1; i++) {

                        // count blank & action
                        if (map[i][j] == BLANK) nBlank++;
                        else if (map[i][j] == HOLE) nBlank = 1;
                        else nBlank = 0;

                        // action
                        if (map[i + 1][j] > 0) {

                            if (map[i + 1 - nBlank][j] == HOLE) {

                                if (map[i + 1][j] == RED) {

                                    ret = ret > NONE ? ret : SUCCESS;
                                } else {

                                    ret = FAIL;
                                }

                                map[i + 1][j] = BLANK;
                            } else {
                                if (nBlank != 0) {

                                    map[i + 1 - nBlank][j] = map[i + 1][j];
                                    map[i + 1][j] = BLANK;

                                    nBlank--;
                                }
                            }
                        }
                    }
                }
                break;

            case RIGHT:

                for (int i = 1; i < N; i++) {

                    nBlank = 0;
                    for (int j = M - 1; j > 1; j--) {

                        // count blank & action
                        if (map[i][j] == BLANK) nBlank++;
                        else if (map[i][j] == HOLE) nBlank = 1;
                        else nBlank = 0;

                        // action
                        if (map[i][j - 1] > 0) {

                            if (map[i][j - 1 + nBlank] == HOLE) {

                                if (map[i][j - 1] == RED) {

                                    ret = ret > NONE ? ret : SUCCESS;
                                } else {

                                    ret = FAIL;
                                }

                                map[i][j - 1] = BLANK;
                            } else {
                                if (nBlank != 0) {

                                    map[i][j - 1 + nBlank] = map[i][j - 1];
                                    map[i][j - 1] = BLANK;

                                    nBlank--;
                                }
                            }
                        }
                    }
                }
                break;

            case DOWN:

                for (int j = 1; j < M; j++) {

                    nBlank = 0;
                    for (int i = N - 1; i > 1; i--) {

                        // count blank & action
                        if (map[i][j] == BLANK) nBlank++;
                        else if (map[i][j] == HOLE) nBlank = 1;
                        else nBlank = 0;

                        // action
                        if (map[i - 1][j] > 0) {

                            if (map[i - 1 + nBlank][j] == HOLE) {

                                if (map[i - 1][j] == RED) {

                                    ret = ret > NONE ? ret : SUCCESS;
                                } else {

                                    ret = FAIL;
                                }

                                map[i - 1][j] = BLANK;
                            } else {
                                if (nBlank != 0) {

                                    map[i - 1 + nBlank][j] = map[i - 1][j];
                                    map[i - 1][j] = BLANK;

                                    nBlank--;
                                }
                            }
                        }
                    }
                }
                break;

        }

        return map;
    }

    static void printMap(int[][] map) {

        System.out.println();
        for (int i = 0; i < N; i++) {

            for (int j = 0; j < M; j++) {

                System.out.printf("%4d", map[i][j]);
            }

            System.out.println();
        }

        System.out.println();
    }
}
