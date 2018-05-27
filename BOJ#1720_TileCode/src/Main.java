import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ#1720 타일 코드
 * https://www.acmicpc.net/problem/1720
 */

public class Main {

    static int[] dp = new int[31];


    static {

        dp[1] = 1;
        dp[2] = 3;
    }

    public static void main(String[] args) throws IOException {

        int N; // 타일의 크기

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        System.out.println(solve(N));

    }

    static int solve(int N) {

        if (dp[N] > 0) {

            return dp[N];
        }

        return dp[N] = solve(N - 1) + 2 * solve(N - 2);
    }
}