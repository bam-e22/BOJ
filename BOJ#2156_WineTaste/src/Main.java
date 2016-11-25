import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
 * BOJ#2156 포도주 시식
 * https://www.acmicpc.net/problem/2156
 */

public class Main {

	static int[] dp = new int[10001];

	public static void main(String[] args) throws IOException {

		int n;
		int[] p;

		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		p = new int[n + 1];

		for (int i = 1; i <= n; i++) {

			p[i] = Integer.parseInt(br.readLine());
		}

		// dp[n]
		System.out.println(go(n, p));

	}

	static int go(int n, int[] p) {

		if (n < 1) {

			return 0;
		} else if (n == 1) {

			return p[1];
		} else if (n == 2) {

			return p[1] + p[2];
		}

		if (dp[n] > 0) {

			return dp[n];
		}

		dp[n] = Math.max(Math.max(go(n - 1, p), go(n - 2, p) + p[n]), go(n - 3, p) + p[n] + p[n - 1]);

		return dp[n];
	}

}
