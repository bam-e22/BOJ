import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#11005 진법 변환2
 * https://www.acmicpc.net/problem/11005 
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int N;
		int B;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		System.out.println(radixTransform(N, B));
	}

	static String radixTransform(int N, int B) {

		StringBuilder ret = new StringBuilder("");

		while (N > 0) {

			int remainder = N % B;
			N = N / B;

			ret.insert(0, remainder < 10 ? String.valueOf((char) ('0' + remainder)) : String.valueOf((char) ('A' + remainder - 10)));

		}

		return ret.toString();
	}

}
