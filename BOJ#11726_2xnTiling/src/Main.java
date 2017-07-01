import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#11726 2xN 타일링
 * https://www.acmicpc.net/problem/11726
 */

public class Main {

	static final int DIVISOR = 10007;
	static int[] dp = new int[1001]; // dp[N] : 2xN 직사각형을 1x2, 2x1 타일로 채우는 방법의 수

	// dp[N] = dp[N-1] + dp[N-2];
	public static void main(String[] args) throws IOException {

		int n;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		System.out.println(go(n));
	}

	static int go(int n) {

		if (n < 2) {

			return 1;
		}

		if (dp[n] > 0) {

			return dp[n];
		}

		dp[n] = (go(n - 1) + go(n - 2)) % DIVISOR;

		return dp[n];
	}

}
