import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#13419 탕수육
 * https://www.acmicpc.net/problem/13419
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int T;
		StringBuilder inputStr;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {

			StringBuilder a = new StringBuilder();
			StringBuilder b = new StringBuilder();

			inputStr = new StringBuilder(br.readLine());

			
			if(inputStr.length() %2 != 0) {
				
				inputStr.append(inputStr);
			}

			for (int i = 0; i < inputStr.length(); i++) {

				if (i % 2 == 0) {

					a.append(inputStr.substring(i, i + 1));
				} else {

					b.append(inputStr.substring(i, i + 1));
				}
			}

			System.out.println(a);
			System.out.println(b);
		}
	}

}
