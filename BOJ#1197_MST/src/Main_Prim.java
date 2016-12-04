import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * BOJ#1197 최소 스패닝 트리
 * https://www.acmicpc.net/problem/1197
 */

public class Main_Prim {

	static final int INF = 100000000;

	public static void main(String[] args) throws IOException {

		int V; // V 그룹 정점의 개수
		int E; // 간선의 개수
		int[][] w; // 간선의 정보
		int sumOfWeight = 0; // MST 가중치의 합
		boolean[] visited;
		int numOfMST = 0; // MST 그룹 정점의 개수
		int[] d; // MST 그룹에서 V그룹의 vertex까지의 거리

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		visited = new boolean[V + 1];
		w = new int[V + 1][V + 1];
		d = new int[V + 1];

		for (int i = 0; i < V + 1; i++) {

			d[i] = INF;
			visited[i] = false;
			for (int j = 0; j < V + 1; j++) {

				w[i][j] = INF;
			}
		}

		// 간선 정보 입력
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			if (w[A][B] > C) {

				// 무방향 그래프
				w[A][B] = C;
				w[B][A] = C;
			}
		}

		// Prim 알고리즘

		// 초기값
		numOfMST = 0;
		// MST[0] = 1;
		d[1] = 0;

		while (numOfMST < V) {

			int min = INF;
			int u = -1;

			// v 그룹에서 제일 가까운 node를 뽑는다.
			for (int i = 1; i < V + 1; i++) {

				if (!visited[i] && d[i] < min) {

					min = d[i];
					u = i;
				}
			}

			// d 거리 갱신
			for (int i = 1; i < V + 1; i++) {

				if (!visited[i] && w[u][i] != INF) {

					if (w[u][i] < d[i]) {

						d[i] = w[u][i];
					}
				}
			}

			numOfMST++;
			sumOfWeight += min;
			visited[u] = true;

		}

		// 결과 출력
		bw.write(String.valueOf(sumOfWeight));

		bw.flush();

		bw.close();
		br.close();

	}

}
