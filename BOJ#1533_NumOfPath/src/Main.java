import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#1533 길의 개수
 * https://www.acmicpc.net/problem/1533
 */

public class Main {

    static final int MOD = 1000003;
    static int[][] A = new int[11][11];

    public static void main(String[] args) throws IOException {

        int N; // 교차점의 개수
        int S; // 시작점의 위치
        int E; // 끝점의 위치 1 <= S, E <= N <= 10
        int T; // 정문이가 늦는 시간 T <= 1,000,000,000

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {

            String rowInfo = br.readLine();
            for (int j = 1; j <= N; j++) {


                A[i][j] = (rowInfo.charAt(j - 1) - '0') % MOD;
            }
        }

        printArr(A, N);
        int[][] B = transformMatrix(A, N);
        printArr(B, 5 * N);


    }

    static int[][] multiplication(int[][] A, int[][] B) {

        int size = A[0].length;
        int[][] C = new int[size][size];

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                for (int k = 0; k < size; k++) {

                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

    static void printArr(int[][] A, int N) {

        for (int i = 1; i <= N; i++) {

            for (int j = 1; j <= N; j++) {

                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int[][] transformMatrix(int[][] A, int N) {

        int[][] B = new int[N * 5 + 1][N * 5 + 1];

        for (int i = 0; i < 5 * N + 1; i++) {

            Arrays.fill(B[i], 0);
        }

        for (int i = 1; i <= N; i++) {

            for (int j = 1; j <= N; j++) {

                for (int k = 0; k < A[i][j]; k++) {

                    B[5 * (i - 1) + 1][j++] = 1;
                }
            }
        }

        return B;
    }
}