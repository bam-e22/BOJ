import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#1373 2진수 8진수
 */

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder inputStr = new StringBuilder(br.readLine());
		StringBuilder outputStr = new StringBuilder();

		int length = inputStr.length();

		if (length % 3 == 1) {

			inputStr.insert(0, "00");
		} else if (length % 3 == 2) {

			inputStr.insert(0, "0");
		}

		for (int i = 0; i < length; i += 3) {

			int sum = 0;

			sum += ((int) (inputStr.charAt(i) - 48)) * 4;
			sum += ((int) (inputStr.charAt(i + 1) - 48)) * 2;
			sum += ((int) (inputStr.charAt(i + 2) - 48)) * 1;

			outputStr.append(sum);
		}

		System.out.println(outputStr);

	}

}
