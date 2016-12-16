import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#11576 Base Conversion
 * https://www.acmicpc.net/problem/11576
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int A, B;
		int[] a;
		StringBuilder outputStr = new StringBuilder();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		a = new int[Integer.parseInt(br.readLine())];

		st = new StringTokenizer(br.readLine());

		for (int i = a.length - 1; i >= 0; i--) {

			a[i] = Integer.parseInt(st.nextToken());
		}

		int decimalNum = 0;
		for (int i = 0; i < a.length; i++) {

			decimalNum += (int) (Math.pow(A, i) * a[i]);
		}

		if (decimalNum == 0) {

			System.out.println(0);
			return;
		}
		
		while (decimalNum > 0) {

			outputStr.insert(0, String.valueOf(decimalNum % B) + " ");

			decimalNum /= B;
		}

		System.out.println(outputStr.toString());

	}

}
