import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] A = new int[N];
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        System.out.println(getLISLength(A, N));
    }

    static int getLISLength(int[] A, int N) {

        int lisLength;
        int[] tailTable = new int[N];

        tailTable[0] = A[0];
        lisLength = 1;

        for (int target = 1; target < N; target++) {

            if (A[target] < tailTable[0]) {

                tailTable[0] = A[target];
            } else if (A[target] > tailTable[lisLength - 1]) {

                tailTable[lisLength] = A[target];
                lisLength++;
            } else {

                int idx = Arrays.binarySearch(tailTable, 0, lisLength, A[target]);
                idx = idx < 0 ? -idx -1 : idx;

                tailTable[idx] = A[target];
            }
        }

        return lisLength;
    }
}
