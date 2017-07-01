import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#10799 쇠막대기
 * https://www.acmicpc.net/problem/10799
 */

public class Main {

	public static void main(String[] args) throws IOException {

		String inputStr;
		char[] replacedStr;
		int numOfLaser = 0;
		int numOfLPS = 0; // 왼쪽 괄호 '('
		int numOfFragment = 0;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		inputStr = br.readLine();
		replacedStr = inputStr.replace("()", "x").toCharArray();

		for (int i = 0; i < replacedStr.length; i++) {

			if (replacedStr[i] == '(') {

				numOfLPS++;
				numOfFragment++;
			}

			else if (replacedStr[i] == 'x') {

				if (numOfLPS != 0) {

					numOfLaser++;

					// 연산
					numOfFragment += numOfLPS * numOfLaser;
					numOfLaser = 0;
				}
			}

			else if (replacedStr[i] == ')') {

				numOfLPS--;
			}
		}

		System.out.println(numOfFragment);
	}

}
