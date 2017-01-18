import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#1865 웜홀
 * https://www.acmicpc.net/problem/1865
 */

public class Main {

    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {

        int testCase; // 테스트케이스 개수
        int N; // 지점의 개수
        int M; // 도로의 개수
        int W; // 웜홀의 개수
        Edge[] edges;
        int S; // source1
        int E; // source2
        int T; // weight
        int[] sourceList; // sourceList : 웜홀 도착지들의 모임 = 벨만-포드 출발지들의 모임
        int totalEdgeCount;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        testCase = Integer.parseInt(br.readLine());
        for (int t = 0; t < testCase; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            totalEdgeCount = 2 * M + W;

            int idx_edges = -1;
            edges = new Edge[totalEdgeCount];

            for (int m = 0; m < M; m++) {

                st = new StringTokenizer(br.readLine());

                S = Integer.parseInt(st.nextToken());
                E = Integer.parseInt(st.nextToken());
                T = Integer.parseInt(st.nextToken());

                edges[++idx_edges] = new Edge(S, E, T);
                edges[++idx_edges] = new Edge(E, S, T);
            }

            sourceList = new int[W];
            for (int w = 0; w < W; w++) {

                st = new StringTokenizer(br.readLine());

                S = Integer.parseInt(st.nextToken());
                E = Integer.parseInt(st.nextToken());
                T = Integer.parseInt(st.nextToken());

                edges[++idx_edges] = new Edge(S, E, -T);

                sourceList[w] = E;
            }

            boolean isNegativeWeightCycles = false;
            for (int i = 0; i < W; i++) {

                if (!BellmanFord(edges, N, totalEdgeCount, sourceList[i])) {

                    isNegativeWeightCycles = true;
                    break;
                }
            }

            System.out.println(isNegativeWeightCycles ? "YES" : "NO");
        }
    }

    private static boolean BellmanFord(Edge[] edges, int N, int totalEdgeCount, int source) {

        int[] dist = new int[N + 1]; // 거리 배열

        // initialize
        Arrays.fill(dist, INF);
        dist[source] = 0;

        // Bellman-Ford
        // N-1 Iteration
        for (int i = 0; i < N - 1; i++) {

            // for all edges
            for (int j = 0; j < totalEdgeCount; j++) {

                // Relaxation
                if (dist[edges[j].dest] > dist[edges[j].source] + edges[j].weight) {

                    dist[edges[j].dest] = dist[edges[j].source] + edges[j].weight;
                }
            }
        }

        // more(=N) Iteration -> Check negative edge weight cycles
        for (int i = 0; i < totalEdgeCount; i++) {

            if (dist[edges[i].dest] > dist[edges[i].source] + edges[i].weight) {

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

    Edge(int source, int dest, int weight) {

        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }
}
