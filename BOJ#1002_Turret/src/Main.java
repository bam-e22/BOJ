import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#1002_Turret
 * 두 원의 위치 관계
 * d <= abs(r1-r2) <= d <= r1+r2 <= d
 */
public class Main {

	public static void main(String[] args) throws IOException {
		int t = 0;
		int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
		double r1 = 0, r2 = 0;
		int count;
		double d;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {

			count = 0;
			d = 0;

			StringTokenizer st = new StringTokenizer(br.readLine());

			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			r1 = Integer.parseInt(st.nextToken());

			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			r2 = Integer.parseInt(st.nextToken());

			d = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

			if ((x1 == x2) && (y1 == y2)) {

				if (r1 == r2) {

					count = -1;
				} else {

					count = 0;
				}

			} else {

				if ((d > r1 + r2) || (d < Math.abs(r1 - r2))) {

					count = 0;
				} else if ((d == r1 + r2) || (d == Math.abs(r1 - r2))) {

					count = 1;
				} else {

					count = 2;
				}
			}

			System.out.println(count);
		}

	}
}
