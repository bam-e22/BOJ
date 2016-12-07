import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#11054 가장 긴 바이토닉 부분 수열
 * https://www.acmicpc.net/problem/11054
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int N;
		int[] A;
		int iLength = 1;
		int dLength = 1;
		int[] iDP;
		int[] dDP;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		A = new int[N];
		iDP = new int[N];
		dDP = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {

			iDP[i] = 1;
			dDP[i] = 1;
			A[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < N; i++) {

			for (int j = 0; j < i; j++) {

				if (A[j] < A[i]) {

					iDP[i] = iDP[j] >= iDP[i] ? iDP[j] + 1 : iDP[i];
					iLength = iLength < iDP[i] ? iDP[i] : iLength;
				}
			}
		}

		for (int i = N - 2; i >= 0; i--) {

			for (int j = N - 1; j > i; j--) {

				if (A[j] < A[i]) {

					dDP[i] = dDP[j] >= dDP[i] ? dDP[j] + 1 : dDP[i];
					dLength = dLength < dDP[i] ? dDP[i] : dLength;
				}
			}
		}

		int sum = 0;
		for (int i = 0; i < N; i++) {

			sum = sum < iDP[i] + dDP[i] ? iDP[i] + dDP[i] : sum;
		}

		System.out.println(sum - 1);
	}

}
