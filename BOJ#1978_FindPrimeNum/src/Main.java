import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ#1978 소수 찾
 * https://www.acmicpc.net/problem/1978
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int N;
		int[] dp = new int[1001];
		int[] checkNumArr;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		checkNumArr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {

			checkNumArr[i] = Integer.parseInt(st.nextToken());
		}

		// find Prime Number
		for (int i = 0; i < dp.length; i++) {

			dp[i] = 1;
		}

		dp[0] = 0;
		dp[1] = 0;

		for (int i = 2; i < dp.length; i++) {

			for (int j = 2; i * j < dp.length; j++) {

				dp[i * j] = 0;
			}
		}

		int count = 0;
		for (int i = 0; i < N; i++) {

			if (dp[checkNumArr[i]] == 1) {

				count++;
			}
		}

		System.out.println(count);

	}

}
