import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ#1162 도로포장
 * https://www.acmicpc.net/problem/1162
 */

public class Main {

    static final int INF = 100000000;

    public static void main(String[] args) throws IOException {

        int N; // 도시의 수 1 <= N <= 10000
        int M; // 도로의 수 1 <= M <= 50000
        int K; // 포장할 도로의 수 1 <= K <= 20
        ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
        int[][] dist; // dist[i][k] : 도로를 k개 포장 했을 때 1 -> N으로 가는 최소 cost

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new int[N + 1][K + 1];

        for (int i = 0; i < N + 1; i++) {

            Arrays.fill(dist[i], INF);
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj.get(a).add(new Edge(b, w));
            adj.get(b).add(new Edge(a, w));
        }

        dijkstra(1, K, adj, dist);

        int ans = INF;
        for (int i = 0; i <= K; i++) {

            ans = ans > dist[N][i] ? dist[N][i] : ans;
        }

        System.out.println(ans);

    }

    static void dijkstra(int src, int K, ArrayList<ArrayList<Edge>> adj, int[][] dist) {

        PriorityQueue<Element> pq = new PriorityQueue<>();

        dist[src][0] = 0;

        pq.add(new Element(src, 0, dist[src][0]));

        while (!pq.isEmpty()) {

            int here = pq.peek().node;
            int step = pq.peek().step;
            int cost = pq.peek().dist;

            pq.poll();

            if (dist[here][step] < cost) {

                continue;
            }

            for (Edge x : adj.get(here)) {

                int adjNode = x.toNode;
                int weight = x.cost;

                // 도로 포장을 하는 경우
                if (step + 1 <= K && dist[adjNode][step + 1] > dist[here][step]) {

                    dist[adjNode][step + 1] = dist[here][step];
                    pq.add(new Element(adjNode, step + 1, dist[adjNode][step + 1]));
                }

                // 도로 포장을 하지 않는 경우
                if (dist[adjNode][step] > dist[here][step] + weight) {

                    dist[adjNode][step] = dist[here][step] + weight;
                    pq.add(new Element(adjNode, step, dist[adjNode][step]));
                }
            }
        }
    }
}

class Element implements Comparable<Element> {

    int node;
    int step;
    int dist;

    Element(int node, int step, int dist) {

        this.node = node;
        this.step = step;
        this.dist = dist;
    }

    @Override
    public int compareTo(Element o) {

        return this.dist < o.dist ? -1 : 1;
    }
}

class Edge {

    int toNode;
    int cost;

    Edge(int toNode, int cost) {

        this.toNode = toNode;
        this.cost = cost;
    }

    @Override
    public String toString() {

        return "Edge(" + toNode + "," + cost + ")";
    }
}