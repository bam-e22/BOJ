import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#13417 카드 문자열
 * https://www.acmicpc.net/problem/13417
 */
public class Main {

	public static void main(String[] args) throws IOException {

		int T;
		int N;
		String[] inputStr;
		String outputStr;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {

			N = Integer.parseInt(br.readLine());

			inputStr = new String[N];

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {

				inputStr[i] = st.nextToken();
			}

			outputStr = inputStr[0];
			for (int i = 1; i < N; i++) {

				if (inputStr[i].charAt(0) <= outputStr.charAt(0)) {

					outputStr = inputStr[i] + outputStr;
				} else {

					outputStr += inputStr[i];
				}
			}

			System.out.println(outputStr);
		}

	}

}
