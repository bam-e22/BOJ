import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

/*
 * BOJ#1158  조세퍼스 문제
 * https://www.acmicpc.net/problem/1158
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int N, M;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		List<Integer> list = new LinkedList<Integer>();
		ListIterator<Integer> it = list.listIterator();

		for (int i = 0; i < N; i++) {

			it.add(i + 1);
		}

		System.out.print("<");
		it = list.listIterator();
		while (!list.isEmpty()) {

			for (int i = 0; i < M; i++) {

				if (!it.hasNext()) {

					it = list.listIterator();
				}

				if (i == M - 1) {

					if (list.size() == 1) {

						System.out.print(it.next() + ">");
					} else {

						System.out.print(it.next() + ", ");
					}
				} else {

					it.next();
				}

			}

			it.remove();
		}

	}

}
