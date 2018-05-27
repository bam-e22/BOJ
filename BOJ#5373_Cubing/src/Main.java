import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ#5373 큐빙
 * https://www.acmicpc.net/problem/5373
 */

public class Main {

    // U(0): 윗 면, D(1): 아랫 면
    // F(2): 앞 면, B(3): 뒷 면
    // L(4): 왼쪽 면, R(5): 오른쪽 면
    static final int U = 0;
    static final int D = 1;
    static final int F = 2;
    static final int B = 3;
    static final int L = 4;
    static final int R = 5;

    static final int WHITE = 0;
    static final int YELLOW = 1;
    static final int RED = 2;
    static final int ORANGE = 3;
    static final int GREEN = 4;
    static final int BLUE = 5;

    static final char[] color = {'w', 'y', 'r', 'o', 'g', 'b'};

    static final int CW = 1;
    static final int CCW = -1;

    static final int ROW = 0;
    static final int COL = 1;

    static int[][][] cube = new int[6][3][3];

    static {

        for (int face = 0; face < 6; face++) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {

                    cube[face][row][col] = color[face];
                }
            }
        }
    }

    static final boolean DEBUG = true;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        // TC
        for (int t = 0; t < T; t++) {

            int n = Integer.parseInt(br.readLine()); // 큐브를 돌린 횟수

            String s = br.readLine();

            String[] cmd = s.split(" ");

            // solve
            for (int i = 0; i < n; i++) {

                int face = getFaceNumber(cmd[i].charAt(0));
                int rotateDir = getRoateDirection(cmd[i].charAt(1));

                //if (DEBUG) System.out.println("face= " + face + ", rotateDir=" + rotateDir);

                cubing(face, rotateDir);
            }
            printCubeFace(U);
            init();
        } // ~ test case loop
    } // ~ main

    static void cubing(int face, int rotateDir) {

        switch (face) {

            case U:
                if (rotateDir == CW) rotateCube("2435", 0, ROW);
                else rotateCube("5342", 0, ROW);
                break;
            case D:
                if (rotateDir == CW) rotateCube("5342", 2, ROW);
                else rotateCube("2435", 2, ROW);
                break;
            case F:
                if (rotateDir == CW) rotateCube("4051", 2, ROW);
                else rotateCube("1504", 2, ROW);
                break;
            case B:
                if (rotateDir == CW) rotateCube("1504", 0, ROW);
                else rotateCube("4051", 0, ROW);
                break;
            case L:
                if (rotateDir == CW) rotateCube("0213", 0, COL);
                else rotateCube("3120", 0, COL);
                break;
            case R:
                if (rotateDir == CW) rotateCube("3120", 2, COL);
                else rotateCube("0213", 2, COL);
                break;
        }


    }

    static void init() {

        for (int face = 0; face < 6; face++) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {

                    cube[face][row][col] = color[face];
                }
            }
        }
    }

    static void rotateCube(String cmd, int idx, int line) {

        int[] order = new int[4];
        int[] store = new int[3];

        for (int i = 0; i < 4; i++) {

            order[i] = cmd.charAt(i) - '0';
        }

        // store
        for (int i = 0; i < 3; i++) {

            if (line == ROW) store[i] = cube[order[3]][idx][i];
            else if (line == COL) store[i] = cube[order[3]][i][idx];
        }

        // rotate
        for (int i = 3; i > 0; i--) {

            for (int j = 0; j < 3; j++) {

                if (line == ROW) cube[order[i]][idx][j] = cube[order[i - 1]][idx][j];
                else if (line == COL) cube[order[i]][j][idx] = cube[order[i - 1]][j][idx];
            }
        }

        // restroe
        for (int i = 0; i < 3; i++) {

            if (line == ROW) cube[order[0]][idx][i] = store[i];
            else if (line == COL) cube[order[0]][i][idx] = store[i];
        }
    }

    static void printCubeFace(int face) {

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                System.out.printf("%5c", cube[face][row][col]);
            }
            System.out.println();
        }
    }

    static int getRoateDirection(char dir) {

        if (dir == '-') return CCW;
        else return CW;
    }

    static int getFaceNumber(char face) {

        int faceNumber = 0;

        switch (face) {

            case 'U':
                faceNumber = U;
                break;
            case 'D':
                faceNumber = D;
                break;
            case 'F':
                faceNumber = F;
                break;
            case 'B':
                faceNumber = B;
                break;
            case 'L':
                faceNumber = L;
                break;
            case 'R':
                faceNumber = R;
                break;
        }

        return faceNumber;
    }
}
