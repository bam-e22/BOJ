import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ#14629 숫자 조각
 * https://www.acmicpc.net/problem/14629
 */

public class Main {

    static final long MAX_VALUE = 9876543210L;
    static int[] piece = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    static String N;
    static long minDiff = Long.MAX_VALUE;
    static String minNumber = "";

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = br.readLine();

        if (Long.parseLong(N) >= MAX_VALUE) {

            System.out.println(MAX_VALUE);
            return;
        }

        permutaion(piece, 0, 10, N.length());

        System.out.println(Long.parseLong(minNumber));

    } // ~main

    static void permutaion(int[] arr, int depth, int n, int k) {

        if (depth == k) {

            StringBuilder number = new StringBuilder("");
            for (int i = 0; i < k; i++) {

                number.append(arr[i]);
            }

            long diff = Math.abs(Long.parseLong(N) - Long.parseLong(number.toString()));
            if (minDiff > diff) {

                minDiff = diff;
                minNumber = number.toString();
            }

            return;
        }

        for (int i = depth; i < n; i++) {

            swap(arr, i, depth);
            permutaion(arr, depth + 1, n, k);
            swap(arr, i, depth);
        }
    }

    static void swap(int[] arr, int i, int j) {

        int temp;

        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
