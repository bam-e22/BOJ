import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/1007
 * BOJ#1007_Vector Matching
 */
public class Main {

	static double min = Double.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		int T = 0;
		int N = 0; // 점의 개수
		Point[] p;
		int[] pIterator;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {

			N = Integer.parseInt(br.readLine());

			p = new Point[N];
			// 배열 p의 Index 배열
			pIterator = new int[N / 2];

			min = Double.MAX_VALUE;

			for (int i = 0; i < N; i++) {

				StringTokenizer st = new StringTokenizer(br.readLine());

				p[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			// N개의 Point 중 N/2개의 Point를 뽑는다.
			combination(pIterator, 0, N, N / 2, 0, p);
			System.out.printf("%.6f\n",min);

		}

	}

	private static void combination(int[] arr, int index, int n, int k, int target, Point[] p) {

		if (k == 0) {

			min = calculateMin(arr, index, p);
		} else if (n == target) {

			return;
		} else {

			arr[index] = target;
			combination(arr, index + 1, n, k - 1, target + 1, p);
			combination(arr, index, n, k, target + 1, p);
		}

	}

	private static double calculateMin(int[] arr, int length, Point[] p) {

		double sumX = 0;
		double sumY = 0;
		double vectorLength = 0;

		for (int i = 0; i < p.length; i++) {

			sumX += p[i].x;
			sumY += p[i].y;
		}

		for (int i = 0; i < length; i++) {

			sumX -= (p[arr[i]].x) * 2;
			sumY -= (p[arr[i]].y) * 2;
		}

		vectorLength = Math.sqrt((sumX * sumX + sumY * sumY));
		if (vectorLength < min) {

			min = vectorLength;
		}

		return min;

	}
}

class Point {
	int x, y;

	public Point(int x, int y) {

		this.x = x;
		this.y = y;
	}

}