import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map = new int[101][101];

	public static void main(String[] args) throws IOException {

		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {

				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// Solve
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				bw.write(solve(i, j) + " ");
			}
			bw.write("\n");
		}

		bw.flush();

		bw.close();
		br.close();
	} // ~ main

	static int solve(int src, int dest) {

		boolean[] visited = new boolean[N];

		Queue<Integer> queue = new LinkedList<>();

		queue.add(src);

		while (!queue.isEmpty()) {

			int u = queue.poll();
			visited[u] = true;

			if (map[u][dest] == 1) return 1;

			for (int i = 0; i < N; i++) {

				if (map[u][i] == 1 && !visited[i]) {

					queue.add(i);
				}
			}
		}

		return 0;
	}
}