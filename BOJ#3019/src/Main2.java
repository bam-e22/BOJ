import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    static int C;
    static int P;
    static int[][] map = new int[106][101];
    static int[] height = new int[101];
    static int cnt = 0;

    static int[][] patternRow1 = { { 1, 1, 1 }, { 0, 0, 0 } };
    static int[][] patternCol1 = { { 0, 0, 0 }, { 1, 1, 1 } };

    static int[][] patternRow2 = { { 1, 0, -1 } };
    static int[][] patternCol2 = { { 0, 1, 0 } };

    static int[][] patternRow3 = { { 0, 1, 0 }, { 1, 0, 1 } };
    static int[][] patternCol3 = { { 1, 0, 1 }, { 0, -1, 0 } };

    static int[][] patternRow4 = { { 0, -1, 0 }, { 1, 0, 1 } };
    static int[][] patternCol4 = { { 1, 0, 1 }, { 0, 1, 0 } };

    static int[][] patternRow5 = { { 0, 1, -1 }, { 1, 0, 1 }, { 1, 0, 1 }, { 1, 0, 0 } };
    static int[][] patternCol5 = { { 1, 0, 1 }, { 0, -1, 1 }, { 0, 1, -1 }, { 0, -1, 2 } };

    static int[][] patternRow6 = { { 0, 0, 1 }, { 1, 1, -2 }, { 1, 0, 0 }, { 1, 1, 0 } };
    static int[][] patternCol6 = { { 1, 1, 0 }, { 0, 0, 1 }, { 0, 1, 1 }, { 0, 0, -1 } };

    static int[][] patternRow7 = { { 1, -1, 0 }, { 1, 1, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };
    static int[][] patternCol7 = { { 0, 1, 1 }, { 0, 0, 1 }, { 0, -1, -1 }, { 1, 0, 0 } };

    static int[][][] patternRow = { patternRow1, patternRow2, patternRow3, patternRow4, patternRow5, patternRow6, patternRow7 };
    static int[][][] patternCol = { patternCol1, patternCol2, patternCol3, patternCol4, patternCol5, patternCol6, patternCol7 };

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());

        for (int col = 0; col < C; col++) {

            height[col] = Integer.parseInt(st.nextToken());
            for (int row = 0; row < height[col]; row++) {

                map[row][col] = 1;
            }
        }

        // Solve
        for (int col = 0; col < C; col++) {

            for (int i = 0; i < patternRow[P].length; i++) {

                map[height[col]][col] = 2;
                dropPiece(height[col], col, 0, patternRow[P][i], patternCol[P][i]);
                map[height[col]][col] = 0;
            }
        }

        System.out.println(cnt);
    }

    static void dropPiece(int row, int col, int depth, int[] dRow, int[] dCol) {

        if (depth == 3) {

            // printMap();
            if (isPossible()) cnt++;
            // System.out.println(">> cnt=" + cnt + "\n");
            return;
        }

        int nextRow = row + dRow[depth];
        int nextCol = col + dCol[depth];

        if (nextRow < 0 || nextCol < 0 || nextRow >= 105 || nextCol >= C) return;
        if (map[nextRow][nextCol] != 0) return;

        map[nextRow][nextCol] = 2;
        dropPiece(nextRow, nextCol, depth + 1, dRow, dCol);
        map[nextRow][nextCol] = 0;
    }

    static boolean isPossible() {

        for (int col = 0; col < C; col++) {

            boolean rowCheck = true;
            for (int row = height[col]; row < 105; row++) {

                if (map[row][col] == 0 && rowCheck) {

                    rowCheck = !rowCheck;
                    continue;
                }
                if (map[row][col] > 0 && !rowCheck) {

                    return rowCheck;
                }
            }
        }

        return true;
    }

    static void printMap() {

        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 105; row++) {

            String line = "";
            for (int col = 0; col < C; col++) {

                line += map[row][col] + " ";
            }
            sb.insert(0, line + "\n");
        }
        System.out.println(sb);
    }
}

