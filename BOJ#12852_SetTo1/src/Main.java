import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ#12852 1로 만들기 2
 * https://www.acmicpc.net/problem/12852
 */

public class Main {

    static int[] num = new int[1000001];
    static int[] dp = new int[1000001];

    public static void main(String[] args) throws IOException {

        int N; // N <= 1_000_000

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        System.out.println(solve(N));

        printPath(N);
    }

    static void printPath(int N) {

        while (N >= 1) {

            System.out.print(N + " ");
            N = num[N];
        }
    }

    static int solve(int N) {

        if (N == 1) {

            return 0;
        }

        if (dp[N] > 0) {

            return dp[N];
        }

        dp[N] = solve(N - 1) + 1;
        num[N] = N - 1;

        if (N % 3 == 0) {

            int temp = solve(N / 3) + 1;

            if (temp < dp[N]) {

                dp[N] = temp;
                num[N] = N / 3;
            }
        }

        if (N % 2 == 0) {

            int temp = solve(N / 2) + 1;

            if (temp < dp[N]) {

                dp[N] = temp;
                num[N] = N / 2;
            }
        }

        return dp[N];
    }
}
