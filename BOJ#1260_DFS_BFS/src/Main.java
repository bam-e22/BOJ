import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BOJ#1260 DFS와 BFS
 * https://www.acmicpc.net/problem/1260
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int N;
		int M;
		int V;
		int[][] graph;
		boolean[] visited;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		graph = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		for (int i = 0; i < N + 1; i++) {

			visited[i] = false;
			for (int j = 0; j < N + 1; j++) {

				graph[i][j] = 0;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a][b] = 1;
			graph[b][a] = 1;

		}
		dfs(V, N, graph, visited);

		System.out.println();
		for (int i = 0; i < N + 1; i++) {

			visited[i] = false;
		}

		bfs(V, N, graph, visited);

	}

	static void dfs(int v, int N, int[][] graph, boolean[] visited) {

		System.out.print(v + " ");
		visited[v] = true;
		for (int i = 0; i < N + 1; i++) {

			if (graph[v][i] != 0 && !visited[i]) {

				dfs(i, N, graph, visited);
			}
		}
	}

	static void bfs(int v, int N, int[][] graph, boolean[] visited) {

		Queue<Integer> q = new LinkedList<Integer>();

		q.offer(v);
		visited[v] = true;

		while (!q.isEmpty()) {

			v = q.poll();
			System.out.print(v + " ");

			for (int i = 0; i < N + 1; i++) {

				if (graph[v][i] != 0 && !visited[i]) {

					q.offer(i);
					visited[i] = true;
				}
			}
		}

		System.out.println();
	}
}
