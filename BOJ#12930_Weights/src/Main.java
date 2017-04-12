import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * BOJ#12930 두 가중치
 * https://www.acmicpc.net/problem/12930
 */

public class Main {

    static int N;
    static int[][][] W = new int[2][21][21];
    static int[] dist = new int[21];

    static final int INF = 100000000;

    static {

        Arrays.fill(dist, INF);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int k = 0; k < 2; k++) {

            for (int i = 0; i < N; i++) {

                String s = br.readLine();
                for (int j = 0; j < N; j++) {

                    char c = s.charAt(j);

                    if (c == '.') W[k][i][j] = -1;
                    else W[k][i][j] = c - '0';
                }
            }
        }


        dijkstra();
        System.out.println(dist[1] >= INF ? -1 : dist[1]);
    }

    static void dijkstra() {

        PriorityQueue<Node> pq = new PriorityQueue<Node>();

        pq.add(new Node(0, 0, 0));
        dist[0] = 0;

        while (!pq.isEmpty()) {

            Node u = pq.poll();

            if (dist[u.node] < u.W1 * u.W2) continue;

            for (int adjNode = 0; adjNode < N; adjNode++) {

                if (W[0][u.node][adjNode] != -1) {

                    int tempDist = (u.W1 + W[0][u.node][adjNode]) * (u.W2 + W[1][u.node][adjNode]);
                    if (dist[adjNode] > tempDist) {

                        dist[adjNode] = tempDist;
                        pq.add(new Node(adjNode, u.W1 + W[0][u.node][adjNode], u.W2 + W[1][u.node][adjNode]));
                    }
                }
            }
        }

    }
}

class Node implements Comparable<Node> {

    int node;
    int W1;
    int W2;

    Node(int node, int W1, int W2) {

        this.node = node;
        this.W1 = W1;
        this.W2 = W2;
    }

    @Override
    public int compareTo(Node o) {

        return this.W1 * this.W2 < o.W1 * o.W2 ? -1 : 1;
    }
}