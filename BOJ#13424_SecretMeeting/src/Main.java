import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#13424 비밀모임
 * https://www.acmicpc.net/problem/13424
 */

public class Main {

	static final int INF = 100000000;

	public static void main(String[] args) throws IOException {

		int T;
		int N;
		int M;
		int K;
		int[] friends;

		int[][] w;
		int[][] d;
		boolean[] visited;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			w = new int[N + 1][N + 1];
			visited = new boolean[N + 1];

			for (int i = 0; i < N + 1; i++) {

				for (int j = 0; j < N + 1; j++) {

					w[i][j] = INF;
				}
			}

			for (int i = 0; i < M; i++) {

				int a, b, weight;
				st = new StringTokenizer(br.readLine());

				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				weight = Integer.parseInt(st.nextToken());

				if (w[a][b] > weight) {

					w[a][b] = weight;
					w[b][a] = weight;
				}

			}

			K = Integer.parseInt(br.readLine());

			d = new int[K + 1][N + 1];

			friends = new int[K];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++) {

				friends[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < K; i++) {

				dijkstra(friends[i], N, w, visited, d, i);
			}

			int min = INF;
			int minIdx = -1;
			for (int i = 1; i < N + 1; i++) {

				for (int j = 0; j < K; j++) {

					d[K][i] += d[j][i];
				}

				if (d[K][i] < min) {

					min = d[K][i];
					minIdx = i;
				}
			}
			
			System.out.println(minIdx);

		}

	}

	static void dijkstra(int start, int N, int[][] w, boolean[] visited, int[][] d, int index) {

		// Initialize
		for (int i = 0; i < N + 1; i++) {

			d[index][i] = INF;
			visited[i] = false;
		}

		// start node
		d[index][start] = 0;

		for (int i = 1; i < N + 1; i++) {

			int u = -1;
			int dist = INF + 1;

			// get mininum distance node
			for (int j = 1; j < N + 1; j++) {

				if (!visited[j] && d[index][j] < dist) {

					u = j;
					dist = d[index][j];
				}
			}

			// calculate distance of adjacent nodes
			for (int j = 1; j < N + 1; j++) {

				if (d[index][j] > d[index][u] + w[u][j]) {

					d[index][j] = d[index][u] + w[u][j];
				}
			}

			visited[u] = true;

		}

	}

}
