import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#1916 최소비용 구하기
 * https://www.acmicpc.net/problem/1916
 */

public class Main {

	static final int INF = 1000000000;

	public static void main(String[] args) throws IOException {

		int n;
		int m;
		int[][] w;
		int[] d;
		boolean[] visited;
		int start, end;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		w = new int[n + 1][n + 1];
		d = new int[n + 1];
		visited = new boolean[n + 1];

		for (int i = 0; i < n + 1; i++) {

			d[i] = INF;
			visited[i] = false;
			for (int j = 0; j < n + 1; j++) {
				w[i][j] = INF;

			}
		}
		for (int i = 0; i < m; i++) {

			int a, b;
			int weight;

			StringTokenizer st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());

			//w[a][b] = weight;
			if (w[a][b] > weight) {
				
				w[a][b] = weight;
			}
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		dijkstra(start, n, m, w, d, visited);
		System.out.println(d[end]);

	}

	static void dijkstra(int start, int n, int m, int[][] w, int[] d, boolean[] visited) {

		int u;
		int dist;

		d[start] = 0;

		for (int i = 1; i < n + 1; i++) {

			dist = INF + 1;
			u = -1;

			for (int j = 1; j < n + 1; j++) {

				if (!visited[j] && d[j] < dist) {

					u = j;
					dist = d[j];
				}
			}

			for (int j = 1; j < n + 1; j++) {

				if (d[j] > d[u] + w[u][j]) {

					d[j] = d[u] + w[u][j];
				}
			}

			visited[u] = true;
		}
	}
}
