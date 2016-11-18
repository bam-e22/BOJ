import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#10824 네 수
 * https://www.acmicpc.net/problem/10824
 */
public class Main {

	public static void main(String[] args) throws IOException {

		String AB, CD;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		AB = st.nextToken() + st.nextToken();
		CD = st.nextToken() + st.nextToken();
		
		System.out.println(Long.parseLong(AB) + Long.parseLong(CD));

	}

}
