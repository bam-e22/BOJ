import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ#6195 Fence Repair
 * https://www.acmicpc.net/problem/6195
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int N;  // number of planks
        int[] L; // length of plank
        int minIdx1, minIdx2;
        long cost = 0;

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        L = new int[N];

        for (int i = 0; i < N; i++) {

            L[i] = Integer.parseInt(br.readLine());
        }

        // 널빤지 N이 1개가 될 때까지
        while (N > 1) {

            minIdx1 = 0; // 가장 작은 널빤지 idx
            minIdx2 = 1; // 그 다음으로 작은 널빤지 idx

            if (L[minIdx1] > L[minIdx2]) {

                // swap
                minIdx1 ^= minIdx2;
                minIdx2 ^= minIdx1;
                minIdx1 ^= minIdx2;
            }

            // 가장 짧은 널빤지와 그 다음으로 작은 널빤지를 구한다
            for (int i = 2; i < N; i++) {

                if (L[i] < L[minIdx1]) {

                    minIdx2 = minIdx1;
                    minIdx1 = i;
                } else if (L[i] < L[minIdx2]) {

                    minIdx2 = i;
                }
            }

            // 구한 2개의 널빤지를 합친다. cost 갱신.
            int subCost = L[minIdx1] + L[minIdx2];
            cost += subCost;

            // 널빤지 2개를 합쳤으므로 N = N-1을 해준다.
            // 합친 널빤지는 L[minIdx1]에 넣어주고, 필요 없어진 minIdx2에는 L[N-1] 값을 넣어준다.
            if (minIdx1 == N - 1) {

                // swap
                minIdx1 ^= minIdx2;
                minIdx2 ^= minIdx1;
                minIdx1 ^= minIdx2;
            }

            L[minIdx1] = subCost;
            L[minIdx2] = L[N - 1];

            N--;
        } // while loop

        System.out.println(cost);
    }
}