import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ#10217 KCM Travel
 * https://www.acmicpc.net/problem/10217
 */

public class Main {

    static final int START = 1;
    static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {

        int T;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            // input
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            ArrayList<ArrayList<Edge>> adjList = new ArrayList<>();
            int[] dist = new int[N + 1];
            for (int i = 0; i < N + 1; i++) {

                adjList.add(new ArrayList<>());
                dist[i] = INF;
            }

            for (int i = 0; i < K; i++) {

                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                adjList.get(u).add(new Edge(v, x, d));
            }

            // solve
            PriorityQueue<Node> pq = new PriorityQueue<>();

            pq.add(new Node(START, 0, 0));
            dist[START] = 0;

            while (!pq.isEmpty()) {

                Node u = pq.poll();

                for (Edge edge : adjList.get(u.node)) {

                    if (dist[edge.toNode] > u.dist + edge.dist
                            && u.cost + edge.cost <= M) {

                        dist[edge.toNode] = u.dist + edge.dist;

                        pq.add(new Node(edge.toNode, u.cost + edge.cost, dist[edge.toNode]));
                    }
                }
            }

            System.out.println(dist[N] >= INF ? "Poor KCM" : dist[N]);

        } // ~test case loop
    }
}


class Edge {

    int toNode;
    int cost;
    int dist;

    Edge(int toNode, int cost, int dist) {

        this.toNode = toNode;
        this.cost = cost;
        this.dist = dist;
    }
}

class Node implements Comparable<Node> {

    int node;
    int cost;
    int dist;

    Node(int node, int cost, int dist) {

        this.node = node;
        this.cost = cost;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node o) {

        return this.dist < o.dist ? -1 : 1;
    }
}