import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#11657 타임머신
 * https://www.acmicpc.net/problem/11657
 */

public class Main {

    static final int NONE = -1;
    static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {

        int N; // 도시의 개수
        int M; // 버스 노선의 개수
        Edge[] edge;
        int[] dist; // 시작 노드에서 각 노드까지의 최단 거리

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 버스 정보, 거리 배열 초기화
        edge = new Edge[M];
        dist = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {

            dist[i] = INF;
        }

        // start node
        dist[1] = 0;

        // 버스 정보 입력
        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            edge[i] = new Edge(A, B, C);
        }

        if (bellmanFord(edge, dist, N, M)) {

            for (int i = 2; i < N + 1; i++) {

                System.out.println(dist[i] == INF ? -1 : dist[i]);
            }
        } else {

            System.out.println(NONE);
        }
    }

    // O(VE)
    static boolean bellmanFord(Edge[] edge, int[] dist, int N, int M) {

        // 사이클을 가지지 않는 최대 iteration : 1 ~ |V[G]|-1
        // : N-1 번 검사
        for (int i = 0; i < N - 1; i++) {

            // for each edge
            for (int j = 0; j < M; j++) {

                // Relaxation
                if (dist[edge[j].dest] > dist[edge[j].source] + edge[j].weight) {

                    dist[edge[j].dest] = dist[edge[j].source] + edge[j].weight;
                }
            }
        }

        // check - Negative edge weight cycles
        // : 한번 더 검사하여(N 번째), 값이 더 내려간다면? 음수 사이클
        for (int i = 0; i < M; i++) {

            if (dist[edge[i].dest] > dist[edge[i].source] + edge[i].weight) {

                return false;
            }
        }

        return true;
    }
}

class Edge {

    int source;
    int dest;
    int weight;

    public Edge(int source, int dest, int weight) {

        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }
}