import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#9461 파도반 수열
 * https://www.acmicpc.net/problem/9461
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int T;
		int N;
		long[] P = new long[101];

		for (int i = 0; i < 101; i++) {

			P[i] = 0;
		}

		P[1] = 1;
		P[2] = 1;
		P[3] = 1;
		P[4] = 2;
		P[5] = 2;
		P[6] = 3;
		P[7] = 4;
		P[8] = 5;
		P[9] = 7;
		P[10] = 9;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {

			N = Integer.parseInt(br.readLine());

			System.out.println(padovan(N, P));
		}

	}

	static long padovan(int n, long[] P) {

		if (P[n] > 0) {

			return P[n];
		}

		P[n] = padovan(n - 1, P) + padovan(n - 5, P);

		return P[n];
	}

}
