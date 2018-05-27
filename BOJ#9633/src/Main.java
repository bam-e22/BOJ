import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 100000000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            // Input
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());

            PriorityQueue<Integer>[][] edges = new PriorityQueue[N + 1][N + 1];

            for (int i = 0; i < M; i++) {

                st = new StringTokenizer(br.readLine());

                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                int Z = Integer.parseInt(st.nextToken());

                if (edges[X][Y] == null) {

                    edges[X][Y] = new PriorityQueue<>();
                }

                edges[X][Y].add(Z);
            }

            // Solve
            for (int i = 0; i < Q; i++) {

                int[] dist = new int[N + 1];
                Arrays.fill(dist, INF);

                st = new StringTokenizer(br.readLine());

                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());

                dijkstra(edges, dist, N, A, B, C);

                System.out.println(dist[B] == INF ? -1 : dist[B]);
            }
        } // ~ test case loop
    }

    static void dijkstra(PriorityQueue<Integer>[][] edges, int[] dist, int N, int S, int D, int C) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(S, 0, 0, 0));

        dist[S] = 0;

        while (!pq.isEmpty()) {

            Node u = pq.poll();

            if (dist[u.node] < u.dist) continue;
            if (u.node == D) break;

            for (int adjNode = 0; adjNode < N + 1; adjNode++) {

                if (edges[u.node][adjNode] != null) {

                    for (int weight : edges[u.node][adjNode]) {

                        if (weight > u.prevCost && dist[adjNode] > u.dist + weight) {

                            if (u.nEdge + 1 <= C) {

                                dist[adjNode] = u.dist + weight;
                                pq.add(new Node(adjNode, dist[adjNode], weight, u.nEdge + 1));
                            }
                        }
                    }
                }
            }
        }
    }
}

class Node implements Comparable<Node> {

    int node;
    int dist;
    int prevCost;
    int nEdge;

    Node(int node, int dist, int prevCost, int nEdge) {

        this.node = node;
        this.dist = dist;
        this.prevCost = prevCost;
        this.nEdge = nEdge;
    }

    @Override
    public int compareTo(Node o) {

        return this.dist < o.dist ? -1 : 1;
    }
}