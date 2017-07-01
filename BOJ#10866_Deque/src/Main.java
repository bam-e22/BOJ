import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * BOJ#10866 덱
 * https://www.acmicpc.net/problem/10866
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int N;
		String cmd;
		int x;

		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		PrivateDeque dq = new PrivateDeque();

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {

			st = new StringTokenizer(br.readLine());

			cmd = st.nextToken();

			switch (cmd) {
			case "push_front":

				x = Integer.parseInt(st.nextToken());
				dq.pushFront(x);
				break;

			case "push_back":

				x = Integer.parseInt(st.nextToken());
				dq.pushBack(x);
				break;

			case "pop_front":

				System.out.println(dq.popFront());
				break;

			case "pop_back":

				System.out.println(dq.popBack());
				break;

			case "size":

				System.out.println(dq.size());
				break;

			case "empty":

				System.out.println(dq.empty());
				break;

			case "front":

				System.out.println(dq.front());
				break;

			case "back":

				System.out.println(dq.back());
				break;

			default:
				break;
			}

		}

	}

}

class PrivateDeque {

	LinkedList<Integer> deque;

	public PrivateDeque() {

		deque = new LinkedList<Integer>();
	}

	public int back() {

		if (deque.isEmpty()) {

			return -1;
		} else {

			return deque.peekLast();
		}
	}

	public int front() {

		if (deque.isEmpty()) {

			return -1;
		} else {

			return deque.peekFirst();
		}

	}

	public int empty() {

		if (deque.isEmpty()) {

			return 1;
		} else {

			return 0;
		}
	}

	public int size() {

		return deque.size();
	}

	public int popBack() {

		if (deque.isEmpty()) {

			return -1;
		} else {

			return deque.pollLast();
		}
	}

	public int popFront() {

		if (deque.isEmpty()) {

			return -1;
		} else {

			return deque.pollFirst();
		}
	}

	public void pushBack(int x) {

		deque.addLast(x);
	}

	public void pushFront(int x) {

		deque.addFirst(x);
	}

}