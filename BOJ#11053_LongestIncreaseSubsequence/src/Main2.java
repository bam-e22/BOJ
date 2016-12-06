import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#11053 가장 긴 증가하는 부분 수열
 * https://www.acmicpc.net/problem/11053
 * O(n^2)
 */

public class Main2 {

	public static void main(String[] args) throws IOException {

		int N; // array length
		int[] A;
		int[] dp; // dp[i] : A[i]를 포함하는 부분 수열의 크기
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

		// O(N^2)
		for (int i = 1; i < N; i++) {

			for (int j = 0; j < i; j++) {

				if (A[j] < A[i]) {

					if (dp[i] <= dp[j]) {

						dp[i] = dp[j] + 1;
						length = dp[i] > length ? dp[i] : length;
					}
				}
			}
		}

		System.out.println(length);

	}

}
