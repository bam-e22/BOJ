import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#1212 8진수 2진수
 * https://www.acmicpc.net/problem/1212
 */

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder inputStr = new StringBuilder(br.readLine());
		StringBuilder outputStr = new StringBuilder();

		int length = inputStr.length();

		for (int i = 0; i < length; i++) {

			StringBuilder BIN = new StringBuilder();

			int OCT = (int) inputStr.charAt(i) - 48;
			while (OCT > 0) {

				BIN.insert(0, String.valueOf(OCT % 2));
				OCT = OCT / 2;

			}

			if (i != 0) {

				if (BIN.length() % 3 == 1) {

					BIN.insert(0, "00");
				} else if (BIN.length() % 3 == 2) {

					BIN.insert(0, "0");
				}
				
				if (BIN.length() == 0) {
					
					BIN.insert(0, "000");
				}
			}

			outputStr.append(BIN);
		}
		
		System.out.println(outputStr.length() == 0 ? 0 : outputStr);
	}

}
