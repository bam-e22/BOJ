import java.io.IOException;


public class Main {


    public static void main(String[] args) throws IOException {

        // permutaion 테스트
        int[] a = {1, 2, 3, 4};

        permutation(a, 3, 4, 0);

    }

    static void permutation(int[] a, int k, int n, int depth) {

        if (depth == k) {

            printPermutation(a, k);
            return;
        }

        for (int i = depth; i < n; i++) {

            swap(a, depth, i);
            permutation(a, k, n, depth + 1);
            swap(a, depth, i);
        }

        return;
    }

    static void printPermutation(int[] a, int k) {

        for (int i = 0; i < k; i++) {

            System.out.print(a[i] + " ");
        }

        System.out.println();
    }

    static void swap(int[] a, int i, int j) {

        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}


