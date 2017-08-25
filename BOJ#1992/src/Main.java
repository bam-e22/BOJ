import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static char[][] map = new char[64][64];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            String inputLine = br.readLine();
            for (int j = 0; j < N; j++) {

                map[i][j] = inputLine.charAt(j);
            }
        }

        System.out.println(solve(N, map, 0, 0));
    }

    static String solve(int N, char[][] map, int row, int col) {

        // Base Case
        if (N == 1) {

            return "" + map[row][col];
        } else {

            if (isAvailableCompression(N, map, row, col)) {

                return "" + map[row][col];
            }
            // divide & merge
            else {

                String ret = "(";

                for (int i = 0; i < 4; i++) {

                    int newRow = row;
                    int newCol = col;

                    switch (i) {

                        case 0:
                            break;

                        case 1:

                            newCol += N / 2;
                            break;

                        case 2:

                            newRow += N / 2;
                            break;

                        case 3:

                            newRow += N / 2;
                            newCol += N / 2;
                            break;
                    }
                    ret += solve(N / 2, map, newRow, newCol);
                }

                return ret + ")";
            }
        }
    }

    static boolean isAvailableCompression(int N, char[][] map, int row, int col) {

        char data = map[row][col];

        for (int i = row; i < row + N; i++) {
            for (int j = col; j < col + N; j++) {

                if (data != map[i][j]) {

                    return false;
                }
            }
        }

        return true;
    }
}
