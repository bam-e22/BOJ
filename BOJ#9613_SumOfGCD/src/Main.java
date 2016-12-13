import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * BOJ#9613 GCD 합
 *  https://www.acmicpc.net/problem/9613
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int T;
		int N;
		int[] numArr;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());

			numArr = new int[N];
			for (int n = 0; n < N; n++) {

				numArr[n] = Integer.parseInt(st.nextToken());

			}

			int sum = 0;
			for (int i = 0; i < N; i++) {

				for (int j = i + 1; j < N; j++) {

					sum += getGCD(numArr[i], numArr[j]);
				}
			}

			bw.write(sum + "\n");
			bw.flush();
		}

		bw.close();
		br.close();
	}

	static int getGCD(int A, int B) {

		if (A < B) {

			A ^= B;
			B ^= A;
			A ^= B;
		}

		while (B > 0) {

			int temp = A % B;
			A = B;
			B = temp;
		}

		return A;
	}

}
