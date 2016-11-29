import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ#13423 Three Dots
 * https://www.acmicpc.net/problem/13423
 */

public class Main {

	static int count = 0;

	public static void main(String[] args) throws IOException {

		int T;
		int N;
		int[] X;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {

			count = 0;

			N = Integer.parseInt(br.readLine());

			X = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < X.length; i++) {

				X[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(X);

			int[] combinationArr = new int[3];

			combination(combinationArr, 0, N, 3, 0, X);

			System.out.println(count);

		}

	}

	static void combination(int[] arr, int index, int n, int k, int target, int[] X) {

		// nCk = n-1Ck + n-1Ck-1

/*		if (k == 0) {

			if (X[arr[1]] - X[arr[0]] == X[arr[2]] - X[arr[1]]) {

				count++;
			}

		} else */if (k == 1) {

			for (int i = arr[1]; i < n; i++) {

				if (X[i] - X[arr[1]] == X[arr[1]] - X[arr[0]]) {

					count++;
					return;
				} else if (X[i] - X[arr[1]] > X[arr[1]] - X[arr[0]]) {
					
					return;
				}
			}

		} else if (target == n) {

			return;
		} else {

			arr[index] = target;
			combination(arr, index + 1, n, k - 1, target + 1, X);
			combination(arr, index, n, k, target + 1, X);
		}

	}

}
