import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BOJ#1197 최소 스패닝 트리
 * https://www.acmicpc.net/problem/1197
 */

public class Main {

	static final int INF = 100000000;

	public static void main(String[] args) throws IOException {

		int V; // V 그룹 정점의 개수
		int E; // 간선의 개수
		int weightSumOfMST = 0; // MST 가중치의 합
		int edgeNumOfMST = 0; // MST 정점의 개수

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		// 간선 정보 입력
		// Kruskal 알고리즘
		Queue<Edge> edgePriorityQueue = new PriorityQueue<Edge>();

		for (int i = 0; i < E; i++) {

			st = new StringTokenizer(br.readLine());

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			// 우선순위큐-간선의 Weight 기준으로 정렬
			edgePriorityQueue.add(new Edge(A, B, C));
		}

		UnionFind unionFind = new UnionFind(V);

		while (edgeNumOfMST < V && !edgePriorityQueue.isEmpty()) {

			Edge edge = edgePriorityQueue.poll();

			// Union-Find로 사이클을 이루는지 확인
			// 사이클을 이룬다면
			if (unionFind.find(edge.v1) == unionFind.find(edge.v2)) {

				continue;
			}
			// 사이클을 이루지 않는다면
			else {

				unionFind.union(edge.v1, edge.v2);
				weightSumOfMST += edge.weight;
				edgeNumOfMST++;
			}
		}

		// 결과 출력
		bw.write(String.valueOf(weightSumOfMST));

		bw.flush();

		bw.close();
		br.close();

	}

}

class UnionFind {

	int[] root;

	public UnionFind(int V) {

		root = new int[V + 1];

		initialize();
	}

	public int find(int a) {

		if (root[a] < 0) {

			return a;
		}

		return root[a] = find(root[a]);
	}

	public void union(int a, int b) {

		int root1 = find(a);
		int root2 = find(b);

		// 이미 같은 그룹이라면
		if (root1 == root2) {

			return;
		}

		// 다른 그룹이라면

		// root1의 그룹이 더 작다면 (root1 < root2)
		if (root[root1] > root[root2]) {

			root1 ^= root2;
			root2 ^= root1;
			root1 ^= root2;
		}

		// root1과 root2를 결합하고, root2의 부모를 roo1로 설정
		root[root1] += root[root2];
		root[root2] = root1;

	}

	private void initialize() {

		for (int i = 0; i < root.length; i++) {

			root[i] = -1;
		}
	}

	// a를 포함하는 그룹의 정점의 개수를 확인하는 함수
	public int size(int a) {

		return -root[find(a)];
	}
}

class Edge implements Comparable<Edge> {

	int v1;
	int v2;
	int weight;

	Edge(int v1, int v2, int weight) {

		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {

		return (this.weight > o.weight ? 1 : (this.weight == o.weight ? 0 : -1));
	}

}