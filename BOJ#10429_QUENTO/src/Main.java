import java.io.*;
import java.util.StringTokenizer;

/**
 * 선데이코딩 베타라운드1 - D. 수학 퍼즐 게임
 * BOJ#10429 QUENTO
 * https://www.acmicpc.net/problem/10429
 */

public class Main {

    static final int POSSIBLE = 1;
    static final int IMPOSSIBLE = 0;

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    static final int[] sRow = {0, 0, 1, 2, 2};
    static final int[] sCol = {0, 2, 1, 0, 2};

    static char[][] map = new char[3][3];
    static boolean[][] visited = new boolean[3][3];

    static int N; // 만들어야 하는 숫자
    static int M; // 사용해야 하는 숫자의 개수

    static StringBuilder sb = new StringBuilder("");

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 3; i++) {

            String input = br.readLine();
            for (int j = 0; j < 3; j++) {

                map[i][j] = input.charAt(j);
            }
        }

        // solve
        for (int i = 0; i < 5; i++) {

            int startRow = sRow[i];
            int startCol = sCol[i];

            char[] expression = new char[2 * M - 1];

            if (dfs(0, startRow, startCol, expression)) {

                System.out.println(POSSIBLE);
                System.out.println(sb);
                return;
            }

            initVisitedArr();
        }

        System.out.println(IMPOSSIBLE);

    }

    static boolean dfs(int depth, int row, int col, char[] expression) {

        if (depth == M * 2 - 2) {

            expression[M * 2 - 2] = map[row][col];

            int sum = expression[0] - '0';

            for (int i = 2; i < M * 2 - 1; i += 2) {

                if (expression[i - 1] == '+') sum += expression[i] - '0';
                else sum -= expression[i] - '0';
            }

            if (sum == N) {

                sb.insert(0, row + " " + col + "\n");

                return true;
            } else {

                return false;
            }
        }

        for (int i = 0; i < 4; i++) {

            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if (nextRow < 0 || nextRow >= 3 || nextCol < 0 || nextCol >= 3) continue;
            if (visited[nextRow][nextCol]) continue;

            visited[row][col] = true;

            expression[depth] = map[row][col];

            if (dfs(depth + 1, nextRow, nextCol, expression)) {

                sb.insert(0, row + " " + col + "\n");

                return true;
            } else {

                visited[row][col] = false;
            }
        }

        return false;
    }

    static void initVisitedArr() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                visited[i][j] = false;
            }
        }

        sb = new StringBuilder("");
    }
}
