import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#1004_LittlePrince
 * https://www.acmicpc.net/problem/1004
 */
class Point {

	int x, y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Circle {

	int x, y, r;

	Circle(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}

	boolean contains(Point point) {

		int d = (x - point.x) * (x - point.x) + (y - point.y) * (y - point.y);

		return d < r * r;
	}

}

public class Main {

	static Point start;
	static Point end;
	static int n;
	static int T;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {

			int count = 0;

			StringTokenizer st = new StringTokenizer(br.readLine());

			start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			n = Integer.parseInt(br.readLine());

			for (int j = 0; j < n; j++) {

				st = new StringTokenizer(br.readLine());

				Circle c = new Circle(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

				if (c.contains(start) ^ c.contains(end)) {
					count++;
				}
			}

			System.out.println(count);

		}
	} // ~main

}
