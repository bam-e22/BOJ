import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

/*
 * BOJ#1406 에디터
 * https://www.acmicpc.net/problem/1406
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int N;
		String cmd;

		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Editor editor = new Editor(br.readLine().toCharArray());

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {

			st = new StringTokenizer(br.readLine());
			cmd = st.nextToken();

			switch (cmd) {
			case "L":

				editor.L();
				break;
			case "D":

				editor.D();
				break;
			case "B":

				editor.B();
				break;
			case "P":

				char c = st.nextToken().charAt(0);
				editor.P(c);
				break;

			default:
				break;
			}
		}

		editor.printStr();
	}
}

class Editor {

	List<Character> str;
	ListIterator<Character> it;

	int index = 0;

	Editor(char[] arr) {

		str = new LinkedList<Character>();
		it = str.listIterator(str.size());
		pushInitStr(arr);

	}

	public void L() {

		if (it.hasPrevious()) {

			it.previous();
		}
	}

	public void D() {

		if (it.hasNext()) {

			it.next();
		}
	}

	public void B() {

		if (it.hasPrevious()) {

			it.previous();
			it.remove();
		}
	}

	public void P(char c) {

		it.add(c);
	}

	public void printStr() {

		StringBuilder sb = new StringBuilder();
		for (char c : str) {

			sb.append(c);
		}
		System.out.print(sb);
	}
	
/*
	public void printStr() {

		for (char c : str) {

			System.out.print(c);
		}
	}
	*/
	private void pushInitStr(char[] arr) {

		for (int i = 0; i < arr.length; i++) {

			P(arr[i]);
		}
	}

}
