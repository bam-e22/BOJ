import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/1000
 * A+B
 */
public class Main {

	public static void main(String[] args) {

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			System.out.println(Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()));

		} catch (IOException e) {

			e.printStackTrace();

		}
	} // main
}
