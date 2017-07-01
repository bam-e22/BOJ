import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ#1707 이분 그래프
 * https://www.acmicpc.net/problem/1707
 */

public class Main_DFS {

    static final int RED = 1;
    static final int BLUE = -1;

    static boolean checkBipartite;

    public static void main(String[] args) throws IOException {

        int K; // 테스트케이스
        int V; // 정점의 개수
        int E; // 간선의 개수
        ArrayList<ArrayList<Integer>> graph;
        int[] color;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        for (int k = 0; k < K; k++) {

            // input
            StringTokenizer st = new StringTokenizer(br.readLine());

            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            color = new int[V + 1];
            graph = new ArrayList<ArrayList<Integer>>();

            checkBipartite = true;

            for (int i = 0; i < V + 1; i++) {

                Arrays.fill(color, 0);

                graph.add(new ArrayList<Integer>());
            }

            for (int e = 0; e < E; e++) {

                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            for (int i = 1; i < V + 1; i++) {

                if (!checkBipartite) {

                    break;
                }

                if (color[i] == 0) {

                    dfs(i, RED, color, graph, V);
                }
            }

            System.out.println(checkBipartite ? "YES" : "NO");
        } // test case loop

    } // main

    static void dfs(int node, int c, int[] color, ArrayList<ArrayList<Integer>> graph, int V) {

        color[node] = c;

        for (int adjNode : graph.get(node)) {

            if (color[adjNode] == c) {

                checkBipartite = false;
                return;
            }

            if (color[adjNode] == 0) {

                dfs(adjNode, -c, color, graph, V);
            }
        }
    }


}
