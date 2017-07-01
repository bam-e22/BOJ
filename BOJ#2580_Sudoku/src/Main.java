import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * BOJ#2580 스도쿠
 * https://www.acmicpc.net/problem/2580
 */

public class Main {

    static int[][] map = new int[9][9]; // 스도쿠 맵
    static int[] rowInfo = new int[9]; // 각 행의 상태 정보
    static int[] colInfo = new int[9]; // 각 열의 상태 정보
    static int[] squareInfo = new int[9]; // 각 사각형의 상태 정보
    static int blankCnt = 0; // 빈칸 개수

    public static void main(String[] args) throws IOException {

        ArrayList<Point> blankArr = new ArrayList<>(); // 빈칸 정보를 저장하고 있는 ArrayList

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int row = 0; row < 9; row++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int col = 0; col < 9; col++) {

                int num = Integer.parseInt(st.nextToken());
                int squareNum = (row / 3) + (col / 3) * 3;

                map[row][col] = num;

                // 0의 위치를 저장한다
                if (num == 0) {

                    blankArr.add(new Point(row, col, squareNum));
                }
                // 행, 열, square 정보를 기록한다
                else {

                    rowInfo[row] |= 1 << num;
                    colInfo[col] |= 1 << num;
                    squareInfo[squareNum] |= 1 << num;
                }
            }
        }

        // solve
        blankCnt = blankArr.size();
        backtracking(0, blankCnt, blankArr);

    }

    static void backtracking(int idx, int blankCnt, ArrayList<Point> blankArr) {

        if (idx == blankCnt) {

            printSudoku();
            return;
        }

        int row = blankArr.get(idx).row;
        int col = blankArr.get(idx).col;
        int squareNum = blankArr.get(idx).squareNum;

        for (int num = 1; num <= 9; num++) {

            if (!isPossibleValue(num, row, col, squareNum)) {

                continue;
            }

            saveInfo(num, row, col, squareNum);
            backtracking(idx + 1, blankCnt, blankArr);
            restoreInfo(num, row, col, squareNum);
        }
    }

    static boolean isPossibleValue(int num, int row, int col, int squareNum) {

        int info = 1 << num;

        // row check
        if ((rowInfo[row] & info) == info) {

            return false;
        }

        // column check
        if ((colInfo[col] & info) == info) {

            return false;
        }

        // square check
        if ((squareInfo[squareNum] & info) == info) {

            return false;
        }

        return true;
    }

    static void saveInfo(int num, int row, int col, int squareNum) {

        int info = 1 << num;

        map[row][col] = num;

        rowInfo[row] |= info;
        colInfo[col] |= info;
        squareInfo[squareNum] |= info;
    }

    static void restoreInfo(int num, int row, int col, int squareNum) {

        int info = 1 << num;

        map[row][col] = 0;

        rowInfo[row] &= ~info;
        colInfo[col] &= ~info;
        squareInfo[squareNum] &= ~info;
    }

    static void printSudoku() {

        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {

                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}

class Point {

    int row;
    int col;
    int squareNum;

    Point(int row, int col, int squareNum) {

        this.row = row;
        this.col = col;
        this.squareNum = squareNum;
    }
}