import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {

            arr[i] = Integer.parseInt(br.readLine());
        }

        // Solve - LIS
        int lisLength = getLISLength(arr);
        System.out.println(N - lisLength);
    }

    static int getLISLength(int[] A) {

        int length = A.length;

        int[] tailTable = new int[length];
        int lisLength = 0;

        tailTable[lisLength++] = A[0];

        for (int i = 1; i < length; i++) {

            // 후보값이 tailTable의 첫번째 값보다 작은 경우
            if (A[i] < tailTable[0]) {

                tailTable[0] = A[i];
            }
            // 후보값이 tailTable의 마지막 값보다 작은 경우
            else if (A[i] > tailTable[lisLength - 1]) {

                tailTable[lisLength++] = A[i];
            } else {

                int idx = Arrays.binarySearch(tailTable, 0, lisLength, A[i]);
                idx = idx < 0 ? -idx - 1 : idx;

                tailTable[idx] = A[i];
            }
        }

        return lisLength;
    }
}
