import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        for (int i = 0; i < N; i++) {

            A[i] = i + 1;
        }

        nextPermutation(A, 0, N, N);
    }

    static void nextPermutation(int[] A, int depth, int N, int K) {

        if (depth == K) {

            printArr(A, N);
            return;
        }

        for (int i = depth; i < N; i++) {

            rightRotate(A, depth, i);
            nextPermutation(A, depth + 1, N, K);
            leftRotate(A, depth, i);
        }
    }

    static void rightRotate(int[] A, int start, int end) {

        int last = A[end];

        for (int i = end; i > start; i--) {

            A[i] = A[i - 1];
        }

        A[start] = last;
    }

    static void leftRotate(int[] A, int start, int end) {

        int first = A[start];

        for (int i = start; i < end; i++) {

            A[i] = A[i + 1];
        }

        A[end] = first;
    }

    static void printArr(int[] A, int N) {

        for (int i = 0; i < N; i++) {

            System.out.print(A[i] + " ");
        }

        System.out.println();
    }
}
