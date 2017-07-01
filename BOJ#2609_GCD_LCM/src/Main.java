import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#2609_GCD_LCM (최대공약수와 최소공배수)
 * https://www.acmicpc.net/problem/2609
 */
public class Main {

	public static void main(String[] args) throws IOException {

		int a, b;
		int multiple;
		int mod = 0;
		int GCD = 0, LCM = 0;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		multiple = a * b;

		// swap
		if (a < b) {
			a = a ^ b;
			b = a ^ b;
			a = a ^ b;
		}

		// GCD, Greatest Common Divisor
		// Euclidean algorithm
		while (b > 0) {

			mod = a % b;
			a = b;
			b = mod;
		}
		GCD = a;

		// LCM, Least Common multiple
		LCM = multiple / GCD;

		System.out.println(GCD);
		System.out.println(LCM);

	}
}
