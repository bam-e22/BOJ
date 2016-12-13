import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * BOJ#1934 최소공배수
 * https://www.acmicpc.net/problem/1934
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int T;
		int A, B;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			bw.write(A * B / getGCD(A, B) + "\n");
			bw.flush();
		}

		bw.close();
		br.close();
	}

	static int getGCD(int A, int B) {

		int mod = 0;

		if (A < B) {

			A ^= B;
			B ^= A;
			A ^= B;
		}

		while (B > 0) {

			mod = A % B;
			A = B;
			B = mod;
		}

		return A;
	}

}
