import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#2225 합분해
 * https://www.acmicpc.net/problem/2225
 */

public class Main {

	static final int DIVISOR = 1_000_000_000;

	public static void main(String[] args) throws IOException {

		int N;
		int K;
		long[][] dp;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dp = new long[N + 1][K + 1];
		for (int i = 0; i < N + 1; i++) {

			for (int j = 0; j < K + 1; j++) {

				dp[i][j] = 0;
			}
		}

		System.out.println(go(N, K, dp));

	}

	static long go(int N, int K, long[][] dp) {

		if (K == 0) {

			if (N == 0) {

				return 1;
			}

			return 0;
		}

		if (dp[N][K] > 0) {

			return dp[N][K];
		}

		for (int i = 0; i <= N; i++) {

			dp[N][K] += go(N - i, K - 1, dp) % DIVISOR;
			dp[N][K] %= DIVISOR;
		}

		return dp[N][K];

	}

}
