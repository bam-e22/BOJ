import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#1912 연속합
 * https://www.acmicpc.net/problem/1912
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int n;
		int[] A;
		int[] dp;
		int max = -1000000000;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		A = new int[n];
		dp = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {

			int num = Integer.parseInt(st.nextToken());

			A[i] = num;
			dp[i] = num;
		}

		for (int i = 1; i < n; i++) {

			dp[i] = dp[i] + dp[i - 1] > dp[i] ? dp[i] + dp[i - 1] : dp[i];
			max = max < dp[i] ? dp[i] : max;
		}

		System.out.println(Integer.max(max, A[0]));
	}

}
