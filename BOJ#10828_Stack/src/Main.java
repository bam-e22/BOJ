import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#10828 스택
 * https://www.acmicpc.net/problem/10828
 */
public class Main {

	public static void main(String[] args) throws IOException {

		int N;
		String order;
		int x;

		Stack stack = new Stack();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {

			st = new StringTokenizer(br.readLine());

			// push
			if (st.countTokens() == 2) {

				order = st.nextToken();
				x = Integer.parseInt(st.nextToken());

				if (order.equalsIgnoreCase("push")) {

					stack.push(x);
				}
			} else {

				order = st.nextToken();

				if (order.equalsIgnoreCase("pop")) {

					System.out.println(stack.pop());
				}
				if (order.equalsIgnoreCase("size")) {

					System.out.println(stack.size());
				}
				if (order.equalsIgnoreCase("empty")) {

					System.out.println(stack.empty());
				}
				if (order.equalsIgnoreCase("top")) {

					System.out.println(stack.top());
				}

			}

		}

	}

}

class Stack {

	int[] arr = new int[10001];;
	int top = -1;

	public void push(int n) {

		top++;
		arr[top] = n;
	}

	public int pop() {

		int value;

		if (top > -1) {

			value = arr[top];
			top--;
		} else {

			value = -1;
		}

		return value;
	}

	public int size() {

		return top + 1;
	}

	public int empty() {

		if (size() == 0) {

			return 1;
		} else {

			return 0;
		}
	}

	public int top() {

		if (empty() == 0) {

			return arr[top];
		} else {

			return -1;
		}
	}

}