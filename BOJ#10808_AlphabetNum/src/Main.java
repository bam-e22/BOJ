import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#10808 알파벳 개수
 * https://www.acmicpc.net/problem/10808
 */

public class Main {

	public static void main(String[] args) throws IOException {

		char[] S;
		int num;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		S = br.readLine().toCharArray();

		for (int i = 'a'; i <= 'z'; i++) {

			num = 0;

			for (int j = 0; j < S.length; j++) {

				if (S[j] == i) {

					num++;
				}
			}

			System.out.print(num + " ");
		}
	}

}
