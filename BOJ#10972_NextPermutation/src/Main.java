import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#10972 다음 순열
 * https://www.acmicpc.net/problem/10972
 */

public class Main {

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {

            A[i] = Integer.parseInt(st.nextToken());
        }

        // Solve
        int idx = N - 1;

        while (idx > 0 && A[idx - 1] > A[idx]) idx--;

        if (idx == 0) {

            System.out.println(-1);
        } else {

            int endIdx = N - 1;
            while (endIdx >= idx && A[idx - 1] > A[endIdx]) endIdx--;

            int iteration = N - 1 - endIdx;

            Arrays.sort(A, idx - 1, A.length);

            rightRotate(A, idx - 1, idx + iteration);
            printArr(A, N);
        }
    }

    static void printArr(int[] A, int N) {

        for (int i = 0; i < N; i++) {

            System.out.print(A[i] + " ");
        }
    }

    static void rightRotate(int[] A, int start, int end) {

        int last = A[end];

        for (int i = end; i > start; i--) {

            A[i] = A[i - 1];
        }

        A[start] = last;
    }
}