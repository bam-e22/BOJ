import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#13416 주식 투자
 * https://www.acmicpc.net/problem/13416
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int T;
		int N;
		int[][] stockData;
		int sum;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {

			sum = 0;

			N = Integer.parseInt(br.readLine());

			stockData = new int[N][3];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				for (int j = 0; j < 3; j++) {

					stockData[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {

				int max = Math.max(stockData[i][0], Math.max(stockData[i][1], stockData[i][2]));

				if (max > 0) {

					sum += max;
				}

			}

			System.out.println(sum);
		}

	}

}
