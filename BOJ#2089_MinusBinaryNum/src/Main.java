import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#2089 -2진수
 * https://www.acmicpc.net/problem/2089
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int N;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder outputStr = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());

		if (N == 0) {

			System.out.println(N);
			return;
		}

		while (N != 0) {

			if (N % 2 == 0) {

				outputStr.insert(0, "0");
			} else {

				outputStr.insert(0, "1");
			}

			N = N < 0 ? -((N - 1) / 2) : -(N / 2);
		}

		System.out.println(outputStr.toString());
	}
}
