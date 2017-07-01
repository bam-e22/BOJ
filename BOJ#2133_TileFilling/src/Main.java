import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#2133 타일 채우기
 * https://www.acmicpc.net/problem/2133
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int N;
		int[] dp;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		dp = new int[N+1];

		System.out.println(go(N, dp));

	}

	static int go(int N, int[] dp) {

		if (N % 2 == 1) {

			return 0;
		}

		else {

			if (N == 0) {

				return 1;
			}
			if (N == 2) {

				return 3;
			}

			dp[N] = 3 * go(N - 2, dp);

			for (int i = N - 4; i >= 0; i -= 2) {

				dp[N] += 2 * go(i, dp);
			}
		}

		return dp[N];
	}

}
