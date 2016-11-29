import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#13422 도둑
 * https://www.acmicpc.net/problem/13422
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int T;
		int N, M, K;
		int count;
		int sum;
		int[] house;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {

			sum = 0;
			count = 0;

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());

			house = new int[N];

			for (int i = 0; i < N; i++) {

				house[i] = Integer.parseInt(st.nextToken());

				if (i < M) {

					sum += house[i];
				}
			}

			if (N == M) {

				count = sum < K ? 1 : 0;

			} else {

				for (int i = 0; i < N; i++) {

					if (sum < K) {

						count++;
					}

					sum = sum + house[(i + M) % N] - house[i];
				}
			}
			System.out.println(count);

		}
	}
}
