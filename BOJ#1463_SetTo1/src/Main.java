import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#1463 1로 만들기
 * https://www.acmicpc.net/problem/1463
 */

public class Main {

	static int[] dp = new int[1000001];

	public static void main(String[] args) throws IOException {

		int N;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		System.out.println(go(N));

	}

	static int go(int n) {

		if (n == 1) {

			return 0;
		}

		if (dp[n] > 0) {

			return dp[n];
		}

		// -1
		dp[n] = go(n - 1) + 1;

		// /3
		if (n % 3 == 0) {

			int temp;

			temp = go(n / 3) + 1;

			if (dp[n] > temp) {

				dp[n] = temp;
			}
		}

		// /2
		if (n % 2 == 0) {

			int temp;

			temp = go(n / 2) + 1;

			if (dp[n] > temp) {

				dp[n] = temp;
			}
		}

		return dp[n];
	}

}
