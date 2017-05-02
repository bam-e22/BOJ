import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#2740 행렬 곱셈
 * https://www.acmicpc.net/problem/2740
 */

public class Main {

    public static void main(String[] args) throws IOException {

        // input

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nA = Integer.parseInt(st.nextToken());
        int mA = Integer.parseInt(st.nextToken());

        int[][] A = new int[nA][mA];
        for (int i = 0; i < nA; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < mA; j++) {

                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        int nB = Integer.parseInt(st.nextToken());
        int mB = Integer.parseInt(st.nextToken());

        int[][] B = new int[nB][mB];
        for (int i = 0; i < nB; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < mB; j++) {

                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] C = new int[nA][mB];

        // solve #1
        for (int i = 0; i < nA; i++) {
            for (int j = 0; j < mB; j++) {

                C[i][j] = 0;
                for (int k = 0; k < nB; k++) {

                    C[i][j] += (A[i][k] * B[k][j]);
                }
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }

    }

}
