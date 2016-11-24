import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#9465 스티커
 * https://www.acmicpc.net/problem/9465
 */

public class Main {

	// dp[0][N] : 0열에서 출발하여 0행 N열 위치까지의 cost의 합
	// dp[1][N] : 0열에서 출발하여 1행 N열 위치까지의 cost의 합

	static int[][] dp = new int[2][100001];

	public static void main(String[] args) throws IOException {

		int T;
		int n;
		int[][] cost;

		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {

			n = Integer.parseInt(br.readLine());
			cost = new int[2][n + 1];

			// input
			for (int i = 0; i < 2; i++) {

				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= n; j++) {

					cost[i][j] = Integer.parseInt(st.nextToken());
				}

			}

			// recursion formula
			// dp[0][N] = Max(dp[1][N-1] + dp[1][N-2]) + cost[0][N]
			// dp[1][N] = Max(dp[0][N-1] + dp[0][N-2]) + cost[1][N]

			for (int i = 1; i <= n; i++) {

				dp[0][i] = Math.max(getDPValue(1, i - 1), getDPValue(1, i - 2)) + cost[0][i];
				dp[1][i] = Math.max(getDPValue(0, i - 1), getDPValue(0, i - 2)) + cost[1][i];
			}

			System.out.println(Math.max(getDPValue(0, n), getDPValue(1, n)));

		}

	}

	static int getDPValue(int x, int y) {

		if (x < 0 || y < 1) {

			return 0;
		}

		return dp[x][y];
	}

}
