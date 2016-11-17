import java.io.IOException;
import java.util.Scanner;

/*
 * BOJ#10820_StringAnalysis
 * https://www.acmicpc.net/problem/10820
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int nSmall, nBig, nNum, nSpace;
		char[] S;

		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		while (sc.hasNext()) {

			nSmall = 0;
			nBig = 0;
			nNum = 0;
			nSpace = 0;

			S = sc.nextLine().toCharArray();

			for (int i = 0; i < S.length; i++) {

				if (S[i] >= 'a' && S[i] <= 'z') {

					nSmall++;
				}

				if (S[i] >= 'A' && S[i] <= 'Z') {

					nBig++;
				}

				if (S[i] >= '0' && S[i] <= '9') {

					nNum++;
				}

				if (S[i] == ' ') {

					nSpace++;
				}

			}
			sb.append(nSmall + " " + nBig + " " + nNum + " " + nSpace + "\n");
		}

		System.out.println(sb);
		sc.close();
	}

}
