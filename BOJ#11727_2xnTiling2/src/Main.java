import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#11727 2xn 타일링 2
 * https://www.acmicpc.net/problem/11727
 */

public class Main {

	static final int DIVISOR = 10007;
	static int[] dp = new int[1001];

	public static void main(String[] args) throws IOException {

		int n;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		System.out.println(go(n));

	}

	static int go(int n) {

		if (n == 1) {

			return 1;
		}
		if (n == 2) {

			return 3;
		}

		if (dp[n] > 0) {

			return dp[n];
		}

		dp[n] = (go(n - 2) * 2 + go(n - 1)) % DIVISOR;

		return dp[n];
	}

}
