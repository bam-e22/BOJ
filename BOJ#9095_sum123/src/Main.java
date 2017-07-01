import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#9095 1, 2, 3 더하기
 * https://www.acmicpc.net/problem/9095
 */

public class Main {

	static int[] dp = new int[11];

	public static void main(String[] args) throws IOException {

		int T;
		int n;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {

			n = Integer.parseInt(br.readLine());

			System.out.println(go(n));

		}

	}

	static int go(int n) {

		if (n < 1) {

			return 0;
		}
		if (n == 1) {

			return 1;
		}
		if (n == 2) {

			return 2;
		}
		if (n == 3) {

			return 4;
		}

		if (dp[n] > 0) {

			return dp[n];
		}

		dp[n] = go(n - 1) + go(n - 2) + go(n - 3);

		return dp[n];

	}

}
