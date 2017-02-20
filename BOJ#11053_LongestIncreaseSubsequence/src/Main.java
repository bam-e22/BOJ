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

public class Main {

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

		// 초기값
		tailTable[0] = A[0];
		lisLength = 1;

		for (int i = 1; i < size; i++) {

			// 후보값이 LIS의 처음 값보다 작은지?
			if (A[i] < tailTable[0]) {

				tailTable[0] = A[i];
			}
			// 후보값이 LIS의 마지막 값 보다 큰지?
			else if (A[i] > tailTable[lisLength - 1]) {

				tailTable[lisLength] = A[i];
				lisLength++;
			} else {

				// CeilIndex를 찾고 replace한다.

				int idx1 = Arrays.binarySearch(tailTable, 0, lisLength, A[i]);

				idx1 = idx1 < 0 ? -idx1 - 1 : idx1;

				tailTable[idx1] = A[i];

			}
		}

		return lisLength;
	}
}