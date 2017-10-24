import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int L;

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // Solve
        int cnt = 0;

        // row
        for (int row = 0; row < N; row++) {

            if (checkPossible(map[row])) cnt++;
        }

        // col
        for (int col = 0; col < N; col++) {

            int[] heightArr = new int[N];
            for (int row = 0; row < N; row++) {

                heightArr[row] = map[row][col];
            }

            if (checkPossible(heightArr)) cnt++;
        }

        System.out.println(cnt);
    }

    static boolean checkPossible(int[] heightArr) {

        int prevChange = 1; // 1: 오르막, -1: 내리막
        int prevHeight = heightArr[0];
        int prevLength = 1;
        int height;

        for (int i = 1; i < N; i++) {

            height = heightArr[i];
            int diff = prevHeight - height;

            // 높이 차이가 1 이상인 경우
            if (Math.abs(diff) > 1) return false;

            // 평지
            if (diff == 0) {

                prevLength++;
            }
            // 오르막
            else if (diff < 0) {

                // 오 -> 오
                if (prevChange == 1) {

                    if (prevLength < L) return false;
                } else {

                    // 내 -> 오
                    if (prevLength < 2 * L) return false;
                }

                prevLength = 1;
                prevHeight = height;

                prevChange = 1;
            }
            // 내리막
            else if (diff > 0) {

                // 내 -> 내
                if (prevChange == -1) {

                    if (prevLength < L) return false;
                }

                prevLength = 1;
                prevHeight = height;

                prevChange = -1;
            }

            // 끝에 도달
            if (i == N - 1) {

                if (prevChange == -1) {

                    if (prevLength < L) return false;
                }
            }
        }

        return true;
    }
}
