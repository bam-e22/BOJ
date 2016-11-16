import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ#10845 큐
 * https://www.acmicpc.net/problem/10845
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int N;
		String cmd;

		PrivateQueue q = new PrivateQueue();

		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {

			st = new StringTokenizer(br.readLine());
			cmd = st.nextToken();

			if (st.hasMoreTokens()) {

				if (cmd.equals("push")) {

					q.push(Integer.parseInt(st.nextToken()));
				}
			}

			else {

				switch (cmd) {
				case "pop":

					System.out.println(q.pop());
					break;
				case "size":

					System.out.println(q.size());
					break;
				case "empty":

					System.out.println(q.empty());
					break;
				case "front":

					System.out.println(q.front());
					break;
				case "back":

					System.out.println(q.back());
					break;
				default:
					break;
				}

			}

		}

	}
}

class PrivateQueue {

	List<Integer> list;

	public PrivateQueue() {

		list = new LinkedList<Integer>();

	}

	public void push(int x) {

		list.add(x);
	}

	public int pop() {

		int p;

		if (empty() == 0) {

			p = list.get(0);
			list.remove(0);

			return p;
		} else {

			return -1;
		}
	}

	public int size() {

		return list.size();
	}

	public int empty() {

		if (list.isEmpty()) {

			return 1;
		} else {

			return 0;
		}
	}

	public int front() {

		if (empty() == 0) {

			return list.get(0);
		} else {

			return -1;
		}
	}

	public int back() {

		if (empty() == 0) {

			return list.get(list.size() - 1);
		} else {

			return -1;
		}
	}

}
