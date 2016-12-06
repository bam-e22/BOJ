import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#11722 가장 긴 감소하는 부분 수열
 * https://www.acmicpc.net/problem/11722
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int N;
		int[] A;
		int[] dp;
		int length = 1;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		A = new int[N];
		dp = new int[N];

		for (int i = 0; i < N; i++) {

			dp[i] = 1;
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {

			A[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < N; i++) {

			for (int j = 0; j < i; j++) {

				if (A[j] > A[i]) {

					dp[i] = dp[j] >= dp[i] ? dp[j] + 1 : dp[i];
					length = length < dp[i] ? dp[i] : length;
				}
			}
		}
		
		System.out.println(length);

	}

}
