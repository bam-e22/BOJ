import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * BOJ#10430 나머지
 * https://www.acmicpc.net/problem/10430
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int A, B, C;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		bw.write((A + B) % C + "\n");
		bw.write((A % C + B % C) % C + "\n");
		bw.write((A * B) % C + "\n");
		bw.write((A % C * B % C) % C + "\n");

		bw.flush();
		bw.close();
		br.close();

	}

}
