import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#3109 PLINOVOD
 * https://www.acmicpc.net/problem/3109
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        GasPipeLine gasPipeLine = new GasPipeLine(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), br);

        gasPipeLine.findSolution();
        System.out.println(gasPipeLine.getNumOfPipeLine());
    }
}

class GasPipeLine {

    private int[][] map;
    private int row;
    private int col;
    private int numOfPipeLine;

    public GasPipeLine(int R, int C, BufferedReader br) throws IOException {

        this.row = R;
        this.col = C;

        numOfPipeLine = 0;

        getInformation(br);
    }

    public void getInformation(BufferedReader br) throws IOException {

        map = new int[row][col];

        for (int i = 0; i < row; i++) {

            String rowInfo = br.readLine();

            for (int j = 0; j < col; j++) {

                if (rowInfo.charAt(j) == 'x') {

                    map[i][j] = -1;
                } else {

                    map[i][j] = 0;
                }
            }
        }
    }

    public void findSolution() {

        for (int i = 0; i < row; i++) {

            numOfPipeLine += backtracking(i, 0);
        }
    }

    private int backtracking(int targetRow, int targetCol) {

        if (isSafePlace(targetRow, targetCol)) {

            if (targetCol >= col - 1) {

                return 1;
            }

            if (backtracking(targetRow - 1, targetCol + 1) == 1) {

                return 1;
            }
            if (backtracking(targetRow, targetCol + 1) == 1) {

                return 1;
            }
            if (backtracking(targetRow + 1, targetCol + 1) == 1) {

                return 1;
            }
        }

        return 0;
    }

    private boolean isSafePlace(int targetRow, int targetCol) {

        if (targetRow < 0 || targetRow >= row) {

            return false;
        }

        if (map[targetRow][targetCol] != 0) {

            return false;
        }

        map[targetRow][targetCol] = 1;
        return true;
    }

    public int getNumOfPipeLine() {

        return numOfPipeLine;
    }
}