import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#14391 종이 조각
 * https://www.acmicpc.net/problem/14391
 */

public class Main {

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {

            String s = br.readLine();
            for (int j = 0; j < M; j++) {

                map[i][j] = s.charAt(j) - '0';
            }
        }

        // Solve
        int maxSum = 0;

        // 모든 경우를 다 해보기 : 2^NM
        for (int state = 0; state < (1 << N * M); state++) {

            int sum = 0;

            // 가로 계산
            for (int row = 0; row < N; row++) {

                int rowSum = 0;

                for (int col = 0; col < M; col++) {

                    int colIdx = row * M + col;

                    if ((state & (1 << colIdx)) == 0) {

                        rowSum = rowSum * 10 + map[row][col];
                    } else {

                        sum += rowSum;
                        rowSum = 0;
                    }
                }

                sum += rowSum;
            }

            // 세로 계산
            for (int col = 0; col < M; col++) {

                int colSum = 0;

                for (int row = 0; row < N; row++) {

                    int rowIdx = col + row * M;

                    if ((state & (1 << rowIdx)) != 0) {

                        colSum = colSum * 10 + map[row][col];
                    } else {

                        sum += colSum;
                        colSum = 0;
                    }
                }

                sum += colSum;
            }

            maxSum = maxSum < sum ? sum : maxSum;
        }

        System.out.println(maxSum);
    } // ~main
}
