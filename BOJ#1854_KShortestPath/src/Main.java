import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ#1854 K번째 최단경로 찾기
 * https://www.acmicpc.net/problem/1854
 */

public class Main {

    static final int INF = 100000000;

    public static void main(String[] args) throws IOException {

        int N, M, K;
        int[][] W = new int[1001][1001];

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer>[] distQueue = new PriorityQueue[N + 1];

        Comparator<Integer> cp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                // 오름차순
                return o1 < o2 ? 1 : -1;
            }
        };

        for (int i = 0; i < N + 1; i++) {

            distQueue[i] = new PriorityQueue<Integer>(K, cp);
        }

        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            W[a][b] = c;
        }

        // solve
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(1, 0));
        distQueue[1].add(0);

        while (!pq.isEmpty()) {

            Node u = pq.poll();

            // 중복체크는 하지 않음, K개의 최단 경로를 찾을 때까지
            /*
            if (dist[0][u.node] < u.cost) {

                continue;
            }
            */

            for (int adjNode = 1; adjNode <= N; adjNode++) {

                // 연결된 모든 노드에 대하여
                if (W[u.node][adjNode] != 0) {

                    // 저장된 경로가 K개가 안될 경우 그냥 추가한다.
                    if (distQueue[adjNode].size() < K) {

                        distQueue[adjNode].add(u.cost + W[u.node][adjNode]);

                        pq.add(new Node(adjNode, u.cost + W[u.node][adjNode]));
                    }
                    // 저장된 경로가 K개이고, 현재 가장 큰 값보다 작다면
                    else if (distQueue[adjNode].peek() > u.cost + W[u.node][adjNode]) {

                        distQueue[adjNode].poll();
                        distQueue[adjNode].add(u.cost + W[u.node][adjNode]);

                        pq.add(new Node(adjNode, u.cost + W[u.node][adjNode]));
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {

            if (distQueue[i].size() == K) {

                bw.write(distQueue[i].peek() + "\n");

            } else {

                bw.write(-1 + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();

    } // ~main
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