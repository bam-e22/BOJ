import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#13413 오셀로 재배치
 * https://www.acmicpc.net/problem/13413
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int T;
		int N;
		int numOfW;
		int numOfB;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {

			numOfW = 0;
			numOfB = 0;

			N = Integer.parseInt(br.readLine());

			char[] input = br.readLine().toCharArray();
			char[] output = br.readLine().toCharArray();

			for (int i = 0; i < N; i++) {

				if (input[i] != output[i]) {

					if (input[i] == 'W') {

						numOfW++;
					}
					if (input[i] == 'B') {

						numOfB++;
					}
				}
			}

			System.out.println(Math.min(numOfW, numOfB) + Math.abs(numOfW - numOfB));

		}

	}

}
