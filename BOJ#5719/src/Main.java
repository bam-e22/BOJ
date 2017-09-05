import java.io.*;
import java.util.*;

public class Main {

    private static final int INF = 100000000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {

            // Input
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) break;

            st = new StringTokenizer(br.readLine());

            int S = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());

            int[][] weight = new int[N][N];
            int[] dist = new int[N];

            for (int i = 0; i < N; i++) {

                Arrays.fill(weight[i], INF);
                dist[i] = INF;
            }

            for (int i = 0; i < M; i++) {

                st = new StringTokenizer(br.readLine());

                int U = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());

                weight[U][V] = P;
            }

            // Solve
            dijkstra(weight, dist, N, S, D);

            removeShortestPath(weight, dist, N, S, D);

            for (int i = 0; i < N; i++) {

                dist[i] = INF;
            }

            dijkstra(weight, dist, N, S, D);

            bw.write(dist[D] == INF ? "-1\n" : dist[D] + "\n");


        } // ~test case loop

        bw.flush();

        bw.close();
        br.close();
    }

    static void dijkstra(int[][] weight, int[] dist, int N, int S, int D) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(S, 0));

        dist[S] = 0;

        while (!pq.isEmpty()) {

            Node u = pq.poll();

            if (dist[u.node] < u.dist) continue;
            if (u.node == D) break;

            // 탐색
            for (int adjNode = 0; adjNode < N; adjNode++) {

                if (weight[u.node][adjNode] != INF) {

                    if (dist[adjNode] > u.dist + weight[u.node][adjNode]) {

                        dist[adjNode] = u.dist + weight[u.node][adjNode];
                        pq.add(new Node(adjNode, dist[adjNode]));
                    }
                }
            }
        }
    }

    static void removeShortestPath(int[][] weight, int[] dist, int N, int S, int D) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(D);

        while (!queue.isEmpty()) {

            int j = queue.poll();

            for (int i = 0; i < N; i++) {

                if (dist[j] == dist[i] + weight[i][j]) {

                    weight[i][j] = INF;
                    queue.add(i);
                }
            }
        }
    }

    static void printWeight(int[][] weight) {

        for (int i = 0; i < weight.length; i++) {

            for (int j = 0; j < weight[i].length; j++) {

                if (weight[i][j] != INF) System.out.println(i + " " + j + " " + weight[i][j]);

            }
        }
    }
}

class Node implements Comparable<Node> {

    int node;
    int dist;

    Node(int node, int dist) {

        this.node = node;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node o) {

        return this.dist < o.dist ? -1 : 1;
    }
}