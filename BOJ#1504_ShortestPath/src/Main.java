import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ#1504 특정한 최단경로
 * https://www.acmicpc.net/problem/1504
 */

public class Main {

    static final int INF = 100000000;

    static int N, E;
    static int[][] W = new int[801][801];
    static int[][] dist = new int[3][801];

    static {

        for (int i = 0; i < 3; i++) {

            Arrays.fill(dist[i], INF);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i < E; i++) {

            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            W[a][b] = c;
            W[b][a] = c;
        }

        st = new StringTokenizer(br.readLine());
        int node1 = Integer.parseInt(st.nextToken());
        int node2 = Integer.parseInt(st.nextToken());

        dijkstra(1, 0);
        dijkstra(node1, 1);
        dijkstra(node2, 2);

        int minCost = Math.min(dist[0][node1] + dist[1][node2] + dist[2][N], dist[0][node2] + dist[2][node1] + dist[1][N]);

        System.out.println(minCost >= INF ? -1 : minCost);
    }

    static void dijkstra(int src, int idx) {

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(src, 0));
        dist[idx][src] = 0;

        while (!pq.isEmpty()) {

            Node u = pq.poll();

            // 중복 제거
            if (dist[idx][u.node] < u.cost) continue;

            for (int i = 1; i <= N; i++) {

                if (W[u.node][i] != 0) {

                    if (dist[idx][i] > dist[idx][u.node] + W[u.node][i]) {

                        dist[idx][i] = dist[idx][u.node] + W[u.node][i];
                        pq.add(new Node(i, dist[idx][i]));
                    }
                }
            }
        }
    }
}

class Node implements Comparable<Node> {

    int node;
    int cost;

    Node(int node, int cost) {

        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {

        return this.cost < o.cost ? -1 : 1;
    }
}