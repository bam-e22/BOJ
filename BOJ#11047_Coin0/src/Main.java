import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ#11047 동전 0
 * https://www.acmicpc.net/problem/11047
 */
public class Main {

	public static void main(String[] args) throws IOException {

		int N;
		int K;
		int[] A;
		int count = 0;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N];
		for (int i = 0; i < N; i++) {

			A[i] = Integer.parseInt(br.readLine());
		}

		for (int i = N - 1; i >= 0; i--) {

			if (A[i] <= K) {
				count += (K / A[i]);
				K %= A[i];
			}

		}
		System.out.println(count);

	}

}
