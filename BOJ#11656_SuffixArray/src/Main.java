import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * BOJ#11656 접미사 배열
 * https://www.acmicpc.net/problem/11656
 */
public class Main {

	public static void main(String[] args) throws IOException {

		String S;

		ArrayList<String> suffixArr = new ArrayList<String>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		S = br.readLine();

		for (int i = 0; i < S.length(); i++) {

			suffixArr.add(S.substring(i, S.length()));
		}

		/*
		 * Comparator<String> reverseOrder = Collections.reverseOrder();
		 * Collections.sort(suffixArr, reverseOrder);
		 */

		Collections.sort(suffixArr);

		for (String suffix : suffixArr) {

			System.out.println(suffix);
		}
	}

}
