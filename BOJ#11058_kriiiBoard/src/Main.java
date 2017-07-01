import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ#11058 크리보드
 * https://www.acmicpc.net/problem/11058
 */

public class Main {

    static long[] dp = new long[101]; // dp[i] : 버튼을 i번 눌러서 화면에 출력할 수 있는 A의 최대 개수

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());


        System.out.println(solve(N));
    }

    // return dp[N];
    static long solve(int N) {

        if (N <= 0) {

            return 0;
        }

        if (N == 1) {

            return dp[N] = 1;
        }

        if (dp[N] > 0) return dp[N];

        dp[N] = N;
        dp[N] = Math.max(dp[N], solve(N - 1) + 1);

        int j = 2;
        for (int i = N - 3; i > N - 6; i--) {

            dp[N] = Math.max(dp[N], solve(i) * j);
            j++;
        }

        return dp[N];
    }
}
