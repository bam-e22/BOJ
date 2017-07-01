import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#2167 2차원 배열의 합
 * https://www.acmicpc.net/problem/2167
 */

public class Main2 {

    public static void main(String[] args) throws IOException {

        int N, M; // 배열의 크기
        int[][] arr;
        int K; // query

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {

                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        K = Integer.parseInt(br.readLine());

        for (int k = 0; k < K; k++) {

            int i, j, x, y;

            st = new StringTokenizer(br.readLine());

            i = Integer.parseInt(st.nextToken()) - 1;
            j = Integer.parseInt(st.nextToken()) - 1;
            x = Integer.parseInt(st.nextToken()) - 1;
            y = Integer.parseInt(st.nextToken()) - 1;

            int sum = 0;

            for (int row = i; row <= x; row++) {
                for (int col = j; col <= y; col++) {

                    sum += arr[row][col];
                }
            }

            System.out.println(sum);
        } // ~ K loop

    } // ~ main
}
