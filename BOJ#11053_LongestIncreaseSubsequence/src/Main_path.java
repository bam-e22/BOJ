import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ#11053 가장 긴 증가하는 부분 수열
 * https://www.acmicpc.net/problem/11053
 * O(N log N)
 */

public class Main_path {

	public static void main(String[] args) throws IOException {

		int N; // array length
		int[] A;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		A = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {

			A[i] = Integer.parseInt(st.nextToken());
		}

		// O(N log N)
		System.out.println(LISLength(A, N));

	}

	static int LISLength(int[] A, int size) {

		int[] tailTable = new int[size];
		int lisLength = 0; // always points empty slot

		int[] parent = new int[size];

		for (int i = 0; i < parent.length; i++) {

			parent[i] = -1;
		}

		// 초기값
		tailTable[0] = A[0];
		lisLength = 1;

		parent[0] = -1;

		for (int i = 1; i < size; i++) {

			// 후보값이 LIS의 처음 값보다 작은지?
			if (A[i] < tailTable[0]) {

				tailTable[0] = A[i];

				parent[i] = -1;
			}
			// 후보값이 LIS의 마지막 값 보다 큰지?
			else if (A[i] > tailTable[lisLength - 1]) {

				tailTable[lisLength] = A[i];
				parent[i] = tailTable[lisLength - 1];

				lisLength++;

			} else {

				// CeilIndex를 찾고 replace한다.
				int ceilIdx = findCeilIndex(tailTable, -1, lisLength - 1, A[i]);
				tailTable[ceilIdx] = A[i];

				parent[i] = tailTable[ceilIdx - 1];

			}
		}

		System.out.println(Arrays.toString(A));
		System.out.println(Arrays.toString(tailTable));
		System.out.println(Arrays.toString(parent));

		int key = tailTable[lisLength - 1];
		int idx = findCeilIndex(A, -1, A.length - 1, key);

		while (idx != -1) {
			System.out.print(A[idx] + " ");

			key = parent[idx];
			idx = findCeilIndex(A, -1, A.length - 1, key);
		}

		return lisLength;
	}

	static int findCeilIndex(int[] A, int l, int r, int key) {

		while (r - l > 1) {
			int m = l + (r - l) / 2;
			if (A[m] >= key)
				r = m;
			else
				l = m;
		}

		return r;
	}

}
