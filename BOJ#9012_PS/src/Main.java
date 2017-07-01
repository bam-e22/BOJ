import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#9012 괄호
 * https://www.acmicpc.net/problem/9012
 */
public class Main {

	public static void main(String[] args) throws IOException {

		int T;
		String inputStr;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			inputStr = br.readLine();

			if (isVPS(inputStr)) {

				System.out.println("YES");
			} else {

				System.out.println("NO");
			}

		}

	}

	// VPS : Valid Parenthesis String
	static boolean isVPS(String inputStr) {

		int leftPS = 0;
		int rightPS = 0;

		char[] charArr = inputStr.toCharArray();

		for (int i = 0; i < charArr.length; i++) {

			if (charArr[i] == '(') leftPS++;
			if (charArr[i] == ')') rightPS++;

			if (rightPS > leftPS) return false;
		}

		if (rightPS != leftPS) return false;

		return true;

	}

}
