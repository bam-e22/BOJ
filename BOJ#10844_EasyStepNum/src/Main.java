import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#10844 쉬운 계단 수
 * https://www.acmicpc.net/problem/10844
 */

public class Main {

	// dp[N][L] : 길이가 N이고 마지막 수가 L인 계단 수
	// dp[N][L] = dp[N-1][L-1] + dp[N-1][L+1]
	static int[][] dp = new int[101][10];
	static final int DIVISOR = 1_000_000_000;

	public static void main(String[] args) throws IOException {

		int N;
		long ans = 0;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		// N이 1이면서, 마지막 수가 i인 계단 수의 개수는 9개이다.
		for (int i = 1; i <= 9; i++) {

			dp[1][i] = 1;
		}
		// N이 2 이상인 경우,
		for (int i = 2; i <= N; i++) {

			for (int j = 0; j <= 9; j++) {

				dp[i][j] = 0;

				if (j - 1 >= 0) {

					dp[i][j] += dp[i - 1][j - 1];
				}
				if (j + 1 <= 9) {

					dp[i][j] += dp[i - 1][j + 1];
				}

				dp[i][j] %= DIVISOR;
			}

		}

		for (int i = 0; i <= 9; i++) {

			ans += dp[N][i];
			ans %= DIVISOR;
		}

		System.out.println(ans);
	}

}
