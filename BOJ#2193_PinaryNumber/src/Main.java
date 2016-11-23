import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#2193 이친수
 * https://www.acmicpc.net/problem/2193
 */

public class Main {

	static long[] dp = new long[91];

	// dp[N] : N자리 이친수의 개수
	// dp[N] = dp[N-1] + dp[N-2];
	public static void main(String[] args) throws IOException {

		int N;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		System.out.println(go(N));

	}

	static long go(int N) {

		if (N < 1) {

			return 0;
		}

		if (N == 1 || N == 2) {
			return 1;
		}

		if (dp[N] > 0) {

			return dp[N];
		}

		dp[N] = go(N - 1) + go(N - 2);

		return dp[N];
	}

}
