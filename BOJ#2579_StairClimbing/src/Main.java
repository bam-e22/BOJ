import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#2579 계단 오르기
 * https://www.acmicpc.net/problem/2579
 */
public class Main {

	public static void main(String[] args) throws IOException {

		int n;
		int[] A;
		int[] dp;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		A = new int[n];
		dp = new int[n];

		for (int i = 0; i < n; i++) {

			A[i] = Integer.parseInt(br.readLine());
			dp[i] = 0;
		}

		System.out.println(go(n - 1, A, dp));

	}

	static int go(int i, int[] A, int[] dp) {

		if (i < 0) {

			return 0;
		}
		if (i == 0) {

			return A[i];
		}

		if (dp[i] > 0) {

			return dp[i];
		}

		dp[i] = Integer.max(A[i] + A[i - 1] + go(i - 3, A, dp), A[i] + go(i - 2, A, dp));

		return dp[i];
	}

}
