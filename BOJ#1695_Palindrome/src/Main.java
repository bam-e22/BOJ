import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#1695 팰린드롬 만들기
 * https://www.acmicpc.net/problem/1695
 */

public class Main {

    static int[] S = new int[20000];
    static int[] A = new int[20000];

    public static void main(String[] args) throws IOException {

        int N; // <= 5,000
        int cnt = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        N = 2 * N - 1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i += 2) {

            S[i] = Integer.parseInt(st.nextToken());
        }

        manacher(N);

        printArr(N);

    }

    static void manacher(int N) {

        int R = -1, p = -1;

        // 초기값
        for (int i = 0; i < N; i++) {

            if (R < i) {

                A[i] = 0;
            } else {

                A[i] = Math.min(R - i, A[2 * p - 1]);
            }

            while ((i - A[i] - 1 >= 0 && i + A[i] + 1 < N) &&
                    (S[i - A[i] - 1] == S[i + A[i] + 1])) {

                A[i]++;
            }

            if (A[i] + i > R) {

                R = A[i] + i;
                p = i;
            }
        }
    }

    static void printArr(int N) {

        System.out.println("S : ");
        for (int i = 0; i < N; i++) {

            System.out.print(S[i] + " ");
        }

        System.out.println();
        System.out.println("A : ");
        for (int i = 0; i < N; i++) {

            System.out.print(A[i] + " ");
        }
    }
}
