import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/*
 * BOJ#4134_NextPrime
 * https://www.acmicpc.net/problem/4134
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int T;
		BigInteger n;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {

			n = new BigInteger(br.readLine());

			if (n.isProbablePrime(100)) {

				System.out.println(n);
			} else {

				System.out.println(n.nextProbablePrime());
			}

		}

	}
}
