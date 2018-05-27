import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ#1707 이분 그래프
 * https://www.acmicpc.net/problem/1707
 */

public class Main {

    static final int RED = 1;
    static final int BLUE = -1;

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

            // solve
            boolean checkBipartite = true;

            Queue<Integer> queue = new LinkedList<Integer>();

            for (int i = 1; i < V + 1; i++) {

                if (color[i] == 0) {

                    queue.offer(i);
                    color[i] = RED;

                    while (!queue.isEmpty() && checkBipartite) {

                        int node = queue.poll();

                        for (int adjNode : graph.get(node)) {

                            if (color[adjNode] == 0) {

                                queue.offer(adjNode);
                                color[adjNode] = color[node] * -1;
                            }
                            else if (color[node] + color[adjNode] != 0) {

                                checkBipartite = false;
                                break;
                            }
                        }
                    }
                }
            }

            System.out.println(checkBipartite ? "YES" : "NO");

        } // test case loop

    }


}

/*

1
9 8
1 2
1 3
2 4
2 5
3 5
7 8
7 9
8 9


 */