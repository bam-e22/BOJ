import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int[] dRow = { 0, -1, 0, 1 };
    static final int[] dCol = { -1, 0, 1, 0 };

    static final int[][] patternRow = {
            { 1, 1, 1 }, { 0, 0, 0 },
            { 0, 1, 0 }, { 1, 0, 1 },
            { 0, 0, 1 }, { 1, 1, 0 }, { 0, 0, -1 }, { -1, -1, 0 },
            { 0, 1, 0}
    };
    static final int[][] patternCol = {
            { 0, 0, 0 }, { 1, 1, 1 },
            { 1, 0, 1 }, { 0, -1, 0 },
            { 1, 1, 0 }, { 0, 0, -1 }, {-1, -1, 0 }, { 0, 0, 1 },
            { 1, 0, -1}
    };

    static int N;
    static int[][] map = new int[101][101];
    static int maxSum = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int idx = 0;
        while (true) {

            idx++;
            maxSum = Integer.MIN_VALUE;

            // Input
            N = Integer.parseInt(br.readLine().trim());

            if (N == 0) break;

            for (int i = 0; i < N; i++) {

                String inputLine = br.readLine().trim();
                String[] line = inputLine.split("\\p{Blank}+");
                for (int j = 0; j < N; j++) {

                    map[i][j] = Integer.parseInt(line[j]);
                }
            }

            // Solve
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {

                    // Pattern1
                    solve1(row, col);

                    // Pattern2 (+)
                    solve2(row, col);
                }
            }

            System.out.println(idx + ". " + maxSum);
        } // ~ test case loop
    } // ~ main

    static void solve1(int row, int col) {

        for (int i = 0; i < 9; i++) {

            int sum = map[row][col];
            boolean isMatchPattern = true;

            int nextRow = row;
            int nextCol = col;

            for (int j = 0; j < 3; j++) {

                nextRow += patternRow[i][j];
                nextCol += patternCol[i][j];

                if (nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) {

                    isMatchPattern = false;
                    break;
                }

                sum += map[nextRow][nextCol];
            }

            if (isMatchPattern) {

                maxSum = maxSum < sum ? sum : maxSum;
            }
        }
    }

    static void solve2(int row, int col) {

        int nInvalidity = 0;
        int minValue = Integer.MAX_VALUE;
        int crossSum = map[row][col];

        for (int i = 0; i < 4; i++) {

            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if (nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) {

                nInvalidity++;
                continue;
            }

            crossSum += map[nextRow][nextCol];
            minValue = minValue > map[nextRow][nextCol] ? map[nextRow][nextCol] : minValue;
        }

        switch (nInvalidity) {

            case 0:

                crossSum -= minValue;
            case 1:
                maxSum = maxSum < crossSum ? crossSum : maxSum;
                break;

            case 2:
            case 3:
                break;
        }

    }
}

