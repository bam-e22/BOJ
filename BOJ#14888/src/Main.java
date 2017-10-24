import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] A = new int[12];
    static int[] OP = new int[11];

    static int maxValue = -1000000001;
    static int minValue = 1000000000;

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {

            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        // +, -, *, /
        int idx = 0;
        for (int i = 0; i < 4; i++) {

            int num = Integer.parseInt(st.nextToken());

            for (int j = 0; j < num; j++) {

                OP[idx++] = i;
            }
        }

        // Solve

        permutation(N - 1, N - 1, 0);
        System.out.println(maxValue);
        System.out.println(minValue);

    }

    static int calc() {

        int ret = A[0];

        for (int i = 1; i < N; i++) {

            switch (OP[i - 1]) {

                // +
                case 0:
                    ret += A[i];
                    break;

                //-
                case 1:
                    ret -= A[i];
                    break;

                // *
                case 2:
                    ret *= A[i];
                    break;

                // /
                case 3:
                    ret /= A[i];
                    break;
            }
        }

        return ret;
    }

    static void permutation(int n, int k, int depth) {

        if (depth == k) {

            int ret = calc();

            if (ret < minValue) minValue = ret;
            if (ret > maxValue) maxValue = ret;

            return;
        }

        for (int i = depth; i < n; i++) {

            rightRotate(OP, depth, i);
            permutation(n, k, depth + 1);
            leftRotate(OP, depth, i);
        }
    }

    static void rightRotate(int[] arr, int start, int end) {

        int last = arr[end];

        for (int i = end; i > start; i--) {

            arr[i] = arr[i - 1];
        }

        arr[start] = last;
    }

    static void leftRotate(int[] arr, int start, int end) {

        int first = arr[start];

        for (int i = start; i < end; i++) {

            arr[i] = arr[i + 1];
        }

        arr[end] = first;
    }
}
