import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#1003_Fibonacci
 * https://www.acmicpc.net/problem/1003
 */
public class Main {

	static int count0 = 0;
	static int count1 = 0;
	static int[][] dp = new int[41][3];

	public static void main(String[] args) throws IOException {

		int t = 0;
		int n = 0;

		for (int i = 0; i < dp.length; i++) {

			for (int j = 0; j < dp[0].length; j++) {

				dp[i][j] = -1;
			}
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {

			n = Integer.parseInt(br.readLine());

			count0 = 0;
			count1 = 0;

			if (dp[n][1] == -1 && dp[n][2] == -1) {
				fibonacci(n);
				dp[n][1] = count0;
				dp[n][2] = count1;
			}

			System.out.println(dp[n][1] + " " + dp[n][2]);
		}

	} // main

	static int fibonacci(int n) {

		if (dp[n][0] != -1) {

			count0 += dp[n][1];
			count1 += dp[n][2];
			return dp[n][0];
		} else {

			if (n == 0) {

				count0++;
				dp[n][0] = 0;
				return dp[n][0];
			} else if (n == 1) {

				count1++;
				dp[n][0] = 1;
				return dp[n][0];
			} else {

				dp[n][0] = fibonacci(n - 1) + fibonacci(n - 2);
				return dp[n][0];
			}
		}

	}
}
