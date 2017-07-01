import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#11057 오르막 수
 * https://www.acmicpc.net/problem/11057
 */

public class Main {

	static final int DIVISOR = 10007;
	static int[][] dp = new int[1001][10];

	// dp[N][L] = 길이가 N이면서 끝자리가 L인 오르막 수의 개수
	// dp[N][L] += dp[N-1][0~L-1]
	public static void main(String[] args) throws IOException {

		int N;
		long ans = 0L;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		// 길이가 1인 오르막 수의 개수는 10개이다. 0으로 시작 가능.
		for (int i = 0; i <= 9; i++) {

			dp[1][i] = 1;
		}

		// 길이가 2 이상일 떄
		for (int i = 2; i <= N; i++) {

			for (int j = 0; j <= 9; j++) {

				// 앞의 자리 수(k)가 같거나 작아야 하므로 k <= j
				for (int k = 0; k <= j; k++) {

					dp[i][j] += dp[i - 1][k];
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
