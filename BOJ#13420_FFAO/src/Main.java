import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#13420 사칙연산
 * https://www.acmicpc.net/problem/13420
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int T;
		long a, b;
		long inputAnswer;
		long answer;
		String op;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {

			answer = 0;

			st = new StringTokenizer(br.readLine());

			a = Long.parseLong(st.nextToken());
			op = st.nextToken();
			b = Long.parseLong(st.nextToken());
			st.nextToken();
			inputAnswer = Long.parseLong(st.nextToken());

			switch (op) {
			case "+":

				answer = a + b;
				break;
			case "-":

				answer = a - b;
				break;
			case "/":

				answer = a / b;
				break;
			case "*":

				answer = a * b;
				break;

			default:

				break;
			}
			System.out.println(answer == inputAnswer ? "correct" : "wrong answer");

		}

	}

}
