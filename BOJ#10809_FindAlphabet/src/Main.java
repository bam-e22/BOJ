import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#10809 알파벳 찾기
 * https://www.acmicpc.net/problem/10809
 */

public class Main {

	public static void main(String[] args) throws IOException {

		char[] S;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		S = br.readLine().toCharArray();

		for (int i = 'a'; i <= 'z'; i++) {

			for (int j = 0; j < S.length; j++) {

				if (S[j] == i) {

					System.out.print(j + " ");
					break;
				}

				if (j == S.length - 1) {

					System.out.print(-1 + " ");
				}

			}

		}

	}

}
