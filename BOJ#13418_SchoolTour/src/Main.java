import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BOJ#13418 학교 탐방하기
 * https://www.acmicpc.net/problem/13418
 */
public class Main {

	static final int INF = 10000000;
	static int maxCost = 0;
	static int minCost = 0;

	public static void main(String[] args) throws IOException {

		int N; // 정점의 개수
		int M; // 엣지의 개수
		int numEdgeOfMST; // MST 그룹의 엣지의 개수

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// Kruskal Algorithm

		Comparator<Edge> reverse = Collections.reverseOrder();

		Queue<Edge> ascendQueue = new PriorityQueue<Edge>();
		Queue<Edge> decsendQueue = new PriorityQueue<Edge>(reverse);

		for (int i = 0; i < M + 1; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken()); // 0: 오르막길, 1: 내리막길

			ascendQueue.offer(new Edge(a, b, c));
			decsendQueue.offer(new Edge(a, b, c));
		}

		// 1. 최대 비용
		UnionFind unionFind = new UnionFind(N);

		numEdgeOfMST = 0;

		while ((numEdgeOfMST < (N + 1)) && !ascendQueue.isEmpty()) {

			Edge edge = ascendQueue.poll();

			// 사이클을 이룬다면
			if (unionFind.find(edge.v1) == unionFind.find(edge.v2)) {

				continue;
			}
			// 사이클을 이루지 않는다면
			else {

				numEdgeOfMST++;
				maxCost += edge.weight;
				unionFind.union(edge.v1, edge.v2);
			}
		}

		maxCost = (N - maxCost) * (N - maxCost);

		// 2. 최소 비용
		unionFind = new UnionFind(N);

		numEdgeOfMST = 0;

		while ((numEdgeOfMST < (N + 1)) && !decsendQueue.isEmpty()) {

			Edge edge = decsendQueue.poll();
			
			// 사이클을 이룬다면
			if (unionFind.find(edge.v1) == unionFind.find(edge.v2)) {

				continue;
			}
			// 사이클을 이루지 않는다면
			else {

				numEdgeOfMST++;
				minCost += edge.weight;
				unionFind.union(edge.v1, edge.v2);
			}
		}

		minCost = (N - minCost) * (N - minCost);

		// 3. 결과 출력
		System.out.println(maxCost - minCost);

	}

}

class UnionFind {

	int[] root;

	public UnionFind(int N) {

		root = new int[N + 1];

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

		if (root1 == root2) {

			return;
		}

		if (root[root1] > root[root2]) {

			root1 ^= root2;
			root2 ^= root1;
			root1 ^= root2;
		}

		root[root1] += root[root2];
		root[root2] = root1;

	}

	public int isSize(int a) {

		return -root[find(a)];
	}

	private void initialize() {

		for (int i = 0; i < root.length; i++) {

			root[i] = -1;
		}
	}

}

class Edge implements Comparable<Edge> {

	int v1, v2;
	int weight;

	public Edge(int v1, int v2, int weight) {

		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {

		return (this.weight > o.weight ? 1 : (this.weight == o.weight ? 0 : -1));
	}

}