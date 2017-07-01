import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#14503 로봇청소기
 * https://www.acmicpc.net/problem/14503
 */

public class Main {

    //static final int NORTH = 0;
    //static final int EAST = 1;
    //static final int SOUTH = 2;
    //static final int WEST = 3;

    static final int BLANK = 0;
    static final int WALL = 1;
    static final int CLEANED = 2;

    static int[] dRow = {-1, 0, 1, 0};
    static int[] dCol = {0, 1, 0, -1};

    static int N, M;
    static int[][] map = new int[51][51];

    public static void main(String[] args) throws IOException {

        int cRow, cCol;
        int cDir;
        int cnt = 0;

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        cRow = Integer.parseInt(st.nextToken());
        cCol = Integer.parseInt(st.nextToken());
        cDir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // solve
        while (true) {

            // 1. 현재 위치를 청소한다
            if (map[cRow][cCol] == BLANK) {

                cnt++;
                map[cRow][cCol] = CLEANED;
            }

            boolean ret = false;
            // 2. 현재 방향에서 왼쪽 방향으로 탐색을 진행한다
            for (int i = 0; i < 4; i++) {

                int nextDir = (cDir - 1 + 4) % 4;
                int nextRow = cRow + dRow[nextDir];
                int nextCol = cCol + dCol[nextDir];

                // 2-1.왼쪽방향에 아직 청소하지 않은 공간이 존재하면, 그 방향으로 회전한다음 한 칸을 전진하고 청소한다
                if (map[nextRow][nextCol] == BLANK) {

                    cDir = nextDir;
                    cRow = nextRow;
                    cCol = nextCol;

                    ret = true;
                    break;
                }
                // 2-2. 그 방향에 청소할 방향이 없다면, 그 방향으로 그냥 회전한다
                else {

                    cDir = nextDir;
                }
            }

            if (!ret) {

                // 2-4. 4방향 청소할 곳이 없고, 후진도 불가능한 경우 종료
                if (map[cRow - dRow[cDir]][cCol - dCol[cDir]] == WALL) {

                    System.out.println(cnt);
                    break;
                }
                // 2-3. 4방향 청소할 곳이 없고 후진이 가능한 경우에 그냥 후진
                else {

                    cRow = cRow - dRow[cDir];
                    cCol = cCol - dCol[cDir];
                }
            }

        } // ~ while loop
    }
}
