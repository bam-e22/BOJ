import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static final int[] dRow = {-1, 1};
    static final int[] dCol = {0, 0};

    static final int[] dCol2 = {-1, 1};

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        char[][] map = new char[N][N];
        char[][] ansMap = new char[N][N];

        Queue<Integer> blackQueue = new LinkedList<>();

        String input = br.readLine();
        for (int i = 0; i < N; i++) {

            char c = input.charAt(i);

            map[0][i] = c;
            ansMap[0][i] = c;
        }

        // Solve
        for (int row = 0; row < N - 1; row++) {

            // 초기 검은색 타일의 위치를 확인한다
            for (int col = 0; col < N; col++) {

                if (ansMap[row][col] == '#') blackQueue.add(col);
            }

            // 현재 row에서 파악한 검은 타일과 인접한 좌우의 타일을 뒤집는다

            for (Integer col : blackQueue) {

                map[row][col] = map[row][col] == '#' ? '.' : '#';

                for (int dir = 0; dir < dCol2.length; dir++) {

                    int nextCol = col + dCol2[dir];

                    if (nextCol < 0 || nextCol >= N) continue;

                    map[row][nextCol] = map[row][nextCol] == '#' ? '.' : '#';
                }
            }

            // 다음 row의 최초 상태는 미리 알 수 있다
            for (int col = 0; col < N; col++) {

                ansMap[row + 1][col] = map[row][col];
                map[row + 1][col] = map[row][col];
            }

            // 현재 row에서 파악한 검은 타일과 인접한 상하의 타일을 뒤집는다
            while (!blackQueue.isEmpty()) {

                int col = blackQueue.poll();

                map[row][col] = map[row][col] == '#' ? '.' : '#';

                for (int dir = 0; dir < dRow.length; dir++) {

                    int nextRow = row + dRow[dir];
                    int nextCol = col + dCol[dir];

                    if (nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) continue;

                    map[nextRow][nextCol] = map[nextRow][nextCol] == '#' ? '.' : '#';
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                bw.write(ansMap[i][j]);
            }
            bw.write("\n");
        }

        bw.flush();

        bw.close();
        br.close();
    } // ~main
}
