import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#11052 붕어빵 판매하기
 * https://www.acmicpc.net/problem/11052
 */

public class Main {

	static int[] dp = new int[1001];

	public static void main(String[] args) throws IOException {

		int N;
		int[] P;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		P = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {

			P[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(go(N, P));

	}

	static int go(int N, int[] P) {

		if (N < 1) {

			return 0;
		}

		if (N == 1) {

			return P[N];
		}

		if (dp[N] > 0) {

			return dp[N];
		}

		for (int i = 1; i <= N; i++) {

			int temp;

			temp = go(N - i, P) + P[i];
			if (temp > dp[N]) {

				dp[N] = temp;
			}

		}

		return dp[N];
	}

}
