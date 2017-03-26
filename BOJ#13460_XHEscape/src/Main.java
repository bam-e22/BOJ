import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#13460 째로탈출
 * https://www.acmicpc.net/problem/13460
 */

public class Main {

    // map info
    static final int WALL = -1;
    static final int BLANK = 0;
    static final int HOLE = -10;
    static final int LIMIT = 10;

    static int N, M;
    static int[][] map = new int[11][11];
    static int[][][][][] dp = new int[11][11][11][11][11];

    // direction
    static final int LEFT = 0;
    static final int UP = 1;
    static final int RIGHT = 2;
    static final int DOWN = 3;

    // result
    static final int NONE = 0;
    static final int SUCCESS = 1;
    static final int FAIL = 2;

    static int ret = NONE;
    static final int INF = 100;

    public static void main(String[] args) throws IOException {

        int rRow = -1, rCol = -1; // 빨간구슬 위치
        int bRow = -1, bCol = -1; // 파란구슬 위치

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

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
                        map[i][j] = BLANK;
                        rRow = i;
                        rCol = j;
                        break;

                    case 'B':
                        map[i][j] = BLANK;
                        bRow = i;
                        bCol = j;
                        break;

                    case 'O':
                        map[i][j] = HOLE;
                        break;
                }
            }
        }

        // solve
        dfs(rRow, rCol, bRow, bCol, 0);

        System.out.println(dp[rRow][rCol][bRow][bCol][0] > LIMIT ? -1 : dp[rRow][rCol][bRow][bCol][0]);

    } // main

    static int dfs(int rRow, int rCol, int bRow, int bCol, int step) {

        int temp = INF;

        // memoization
        if (dp[rRow][rCol][bRow][bCol][step] > 0) {

            return dp[rRow][rCol][bRow][bCol][step];
        }

        // 종료 조건
        if (step >= LIMIT) {

            return dp[rRow][rCol][bRow][bCol][step] = INF;
        }

        for (int direction = 0; direction < 4; direction++) {

            PointInfo info = action(rRow, rCol, bRow, bCol, direction);

            if (ret > 0) {

                if (ret == SUCCESS) {

                    ret = NONE;
                    dp[info.rRow][info.rCol][info.bRow][info.bCol][step + 1] = step + 1;

                    return dp[rRow][rCol][bRow][bCol][step] = step + 1;
                } else {

                    ret = NONE;

                    dp[info.rRow][info.rCol][info.bRow][info.bCol][step + 1] = INF;
                    continue;
                }
            }

            temp = Math.min(temp, dfs(info.rRow, info.rCol, info.bRow, info.bCol, step + 1));
        }

        return dp[rRow][rCol][bRow][bCol][step] = temp;
    }

    static PointInfo action(int rRow, int rCol, int bRow, int bCol, int dir) {

        int nBlank;
        PointInfo initialState = new PointInfo(rRow, rCol, bRow, bCol);

        switch (dir) {

            case LEFT:

                // redBall
                while (map[rRow][rCol - 1] != WALL) {

                    rCol--;

                    if (map[rRow][rCol] == HOLE) {

                        ret = ret > NONE ? ret : SUCCESS;
                        break;
                    }
                }

                // blueBall
                while (map[bRow][bCol - 1] != WALL) {

                    bCol--;

                    if (map[bRow][bCol] == HOLE) {

                        ret = FAIL;
                        break;
                    }
                }

                if (ret == NONE && rRow == bRow && rCol == bCol) {

                    if (initialState.rCol < initialState.bCol) {

                        bCol++;
                    } else {

                        rCol++;
                    }
                }
                break;

            case UP:

                // redBall
                while (map[rRow - 1][rCol] != WALL) {

                    rRow--;

                    if (map[rRow][rCol] == HOLE) {

                        ret = ret > NONE ? ret : SUCCESS;
                        break;
                    }
                }

                // blueBall
                while (map[bRow - 1][bCol] != WALL) {

                    bRow--;

                    if (map[bRow][bCol] == HOLE) {

                        ret = FAIL;
                        break;
                    }
                }

                if (ret == NONE && rRow == bRow && rCol == bCol) {

                    if (initialState.rRow < initialState.bRow) {

                        bRow++;
                    } else {

                        rRow++;
                    }
                }
                break;

            case RIGHT:

                // redBall
                while (map[rRow][rCol + 1] != WALL) {

                    rCol++;

                    if (map[rRow][rCol] == HOLE) {

                        ret = ret > NONE ? ret : SUCCESS;
                        break;
                    }
                }

                // blueBall
                while (map[bRow][bCol + 1] != WALL) {

                    bCol++;

                    if (map[bRow][bCol] == HOLE) {

                        ret = FAIL;
                        break;
                    }
                }

                if (ret == NONE && rRow == bRow && rCol == bCol) {

                    if (initialState.rCol < initialState.bCol) {

                        rCol--;
                    } else {

                        bCol--;
                    }
                }
                break;

            case DOWN:

                // redBall
                while (map[rRow + 1][rCol] != WALL) {

                    rRow++;

                    if (map[rRow][rCol] == HOLE) {

                        ret = ret > NONE ? ret : SUCCESS;
                        break;
                    }
                }

                // blueBall
                while (map[bRow + 1][bCol] != WALL) {

                    bRow++;

                    if (map[bRow][bCol] == HOLE) {

                        ret = FAIL;
                        break;
                    }
                }

                if (ret == NONE && rRow == bRow && rCol == bCol) {

                    if (initialState.rRow < initialState.bRow) {

                        rRow--;
                    } else {

                        bRow--;
                    }
                }
                break;

        }

        return new PointInfo(rRow, rCol, bRow, bCol);
    }
}

class PointInfo {

    int rRow, rCol, bRow, bCol;

    PointInfo(int rRow, int rCol, int bRow, int bCol) {

        this.rRow = rRow;
        this.rCol = rCol;
        this.bRow = bRow;
        this.bCol = bCol;
    }
}