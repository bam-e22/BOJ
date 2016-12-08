import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#1699 제곱수의 합
 * https://www.acmicpc.net/problem/1699
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int N;
		int[] dp;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		dp = new int[N + 1];

		dp[0] = 0;
		for (int i = 1; i <= N; i++) {

			dp[i] = i;
			for (int j = 1; j * j <= i; j++) {

				int temp = dp[i - j * j] + 1;

				dp[i] = dp[i] > temp ? temp : dp[i];
			}

		}

		System.out.println(dp[N]);
	}

}
