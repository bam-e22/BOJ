import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * BOJ#10973 이전 순열
 * https://www.acmicpc.net/problem/10973
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

        while (idx > 0 && A[idx - 1] < A[idx]) idx--;

        if (idx == 0) {

            System.out.println(-1);
        } else {

            int endIdx = N - 1;

            while (endIdx >= idx && A[idx - 1] < A[endIdx]) endIdx--;

            int iteration = N - 1 - endIdx;

            Integer[] boxedA = Arrays.stream(A).boxed().toArray(Integer[]::new);

            Arrays.sort(boxedA, idx - 1, A.length, Collections.reverseOrder());

            rightRotate(boxedA, idx - 1, idx + iteration);
            printArr(boxedA, N);
        }
    }

    static void rightRotate(Integer[] A, int start, int end) {

        int last = A[end];

        for (int i = end; i > start; i--) {

            A[i] = A[i - 1];
        }

        A[start] = last;
    }

    static void printArr(Integer[] A, int N) {

        for (int i = 0; i < N; i++) {

            System.out.print(A[i] + " ");
        }
    }
}
