import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/11403
 * BOJ#11403_FindPath
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int N;
		int[][] adjList;
		int[][] path;
		boolean[] visited;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		adjList = new int[N][N];
		path = new int[N][N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {

			for (int j = 0; j < N; j++) {

				adjList[i][j] = 0;
				path[i][j] = 0;
			}
			visited[i] = false;
		}

		for (int i = 0; i < N; i++) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {

				adjList[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {

			bfs(adjList, visited, i, N);

			for (int j = 0; j < N; j++) {

				if (visited[j]) {

					path[i][j] = 1;
				}
			}
		}
		print(path);

	}

	static void bfs(int[][] adjList, boolean[] visited, int v, int N) {

		for (int i = 0; i < N; i++) {

			visited[i] = false;
		}

		Queue<Integer> q = new LinkedList<Integer>();

		q.offer(v);

		while (!q.isEmpty()) {

			v = q.poll();

			for (int i = 0; i < N; i++) {

				if (adjList[v][i] == 1 && !visited[i]) {

					q.offer(i);
					visited[i] = true;
				}
			}
		}
	}

	static void print(int[][] path) {

		for (int i = 0; i < path.length; i++) {

			for (int j = 0; j < path.length; j++) {

				System.out.print(path[i][j] + " ");
			}

			System.out.println();
		}
	}

}
