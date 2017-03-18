import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

/**
 * BOJ#2295 세 수의 합
 * https://www.acmicpc.net/problem/2295
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int N; // 자연수의 개수 5 <= N <= 1000
        int d; // 세 수의 합
        int[] U; // 숫자의 집합
        HashSet<Integer> set = new HashSet<Integer>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        U = new int[N];

        for (int i = 0; i < N; i++) {

            U[i] = Integer.parseInt(br.readLine());
            set.add(U[i]);
        }

        Arrays.sort(U);

        for (int i = N - 1; i >= 0; i--) {

            for (int j = 0; j < N; j++) {

                for (int k = 0; k < N; k++) {

                    if (U[i] - U[j] - U[k] < 0) {

                        break;
                    } else {

                        if (set.contains(U[i] - U[j] - U[k])) {

                            System.out.println(U[i]);
                            return;
                        }
                    }
                }
            }
        }
    }
}
