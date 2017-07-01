import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// @formatter:off
/**
 * BOJ#14499 주사위 굴리기
 * https://www.acmicpc.net/problem/14499
 *
 * 초기상태
 *   2
 * 4 1 3
 *   5
 *   6
 *
 * 초기상태 -> WEST
 *   2
 * 1 3 6
 *   5
 *   4
 *
 * 초기상태 -> EAST
 *   2
 * 6 4 1
 *   5
 *   3
 *
 * 초기상태 -> NORTH
 *   1
 * 4 5 3
 *   6
 *   2
 *
 * 초기상태 -> SOUTH
 *   6
 * 4 2 3
 *   1
 *   5
 */
// @formatter:on

public class Main {

    static int N, M; // 맵 크기
    static int row, col; // 주사위의 처음 좌표
    static int K; // 명령의 개수
    static int[][] map = new int[21][21];

    static final int EAST = 1;
    static final int WEST = 2;
    static final int NORTH = 3;
    static final int SOUTH = 4;

    //static int[] diceHorizon = {4, 1, 3};
    //static int[] diceVertical = {2, 1, 5, 6};
    static int[] diceHorizon = {0, 0, 0};
    static int[] diceVertical = {0, 0, 0, 0};

    static final boolean DEBUG = true;

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // solve
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {

            int cmd = Integer.parseInt(st.nextToken());

            if (!rollingDice(cmd)) continue;

            // 이동한 맵 바닥면 숫자가 0이면, 주사위 바닥면 -> 맵 바닥면
            if (map[row][col] == 0) {

                map[row][col] = getDiceBottom();
            }
            // 이동한 맵 바닥면 숫자가 0이 아니면, 맵 바닥면 -> 주사위 바닥면, 맵 바닥면 = 0
            else {

                setDiceBottom(map[row][col]);
                map[row][col] = 0;
            }

            printDiceTop();
        }

    } // ~main

    // 만약 바깥으로 이동시키려고 하는 경우에는 해당 명령을 무시해야 하며, 출력도 하면 안된다.
    static boolean rollingDice(int dir) {

        boolean ret = true;
        switch (dir) {

            case EAST:

                if (col + 1 >= M) return false;
                col = col + 1;
                rotateRight();
                break;

            case WEST:
                if (col - 1 < 0) return false;
                col = col - 1;
                rotateLeft();
                break;

            case NORTH:
                if (row - 1 < 0) return false;
                row = row - 1;
                rotateUp();
                break;

            case SOUTH:
                if (row + 1 >= N) return false;
                row = row + 1;
                rotateDown();
                break;
        }

        return ret;
    }

    static void rotateRight() {

        int temp = diceHorizon[2];

        for (int i = 2; i > 0; i--) {

            diceHorizon[i] = diceHorizon[i - 1];
        }

        diceHorizon[0] = diceVertical[3];
        diceVertical[3] = temp;
        diceVertical[1] = diceHorizon[1];
    }

    static void rotateLeft() {

        int temp = diceHorizon[0];

        for (int i = 0; i < 2; i++) {

            diceHorizon[i] = diceHorizon[i + 1];
        }

        diceHorizon[2] = diceVertical[3];
        diceVertical[3] = temp;
        diceVertical[1] = diceHorizon[1];
    }

    static void rotateDown() {

        int temp = diceVertical[3];

        for (int i = 3; i > 0; i--) {

            diceVertical[i] = diceVertical[i - 1];
        }

        diceVertical[0] = temp;
        diceHorizon[1] = diceVertical[1];
    }

    static void rotateUp() {

        int temp = diceVertical[0];

        for (int i = 0; i < 3; i++) {

            diceVertical[i] = diceVertical[i + 1];
        }

        diceVertical[3] = temp;
        diceHorizon[1] = diceVertical[1];
    }

    static void printDiceTop() {

        System.out.println(getDiceTop());
    }

    static int getDiceTop() {

        return diceVertical[1];
    }

    static int getDiceBottom() {

        return diceVertical[3];
    }

    static void setDiceBottom(int value) {

        diceVertical[3] = value;
    }
}
