import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#1929_소수 구하기
 * https://www.acmicpc.net/problem/1929
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int M, N;
		int[] primeNumArr;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		primeNumArr = new int[N + 1];

		for (int i = 0; i < N + 1; i++) {

			primeNumArr[i] = 1;
		}
		primeNumArr[1] = 0;

		for (int i = 2; i < N + 1; i++) {

			for (int j = 2; i * j <= N; j++) {

				primeNumArr[i * j] = 0;
			}
		}

		for (int i = M; i < N + 1; i++) {

			if (primeNumArr[i] == 1) {

				System.out.println(i);
			}
		}
	}

}
