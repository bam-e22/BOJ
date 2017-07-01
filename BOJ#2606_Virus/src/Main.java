import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ#2606 바이러스
 * https://www.acmicpc.net/problem/2606
 */

// BFS 풀이

public class Main {

    public static void main(String[] args) throws IOException {

        int N; // 컴퓨터의 수
        int M; // 간선의 수
        int[][] edge;
        boolean[] visited;

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        edge = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < N + 1; i++) {

            Arrays.fill(edge[i], 0);
            Arrays.fill(visited, false);
        }

        for (int i = 0; i < M; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            edge[a][b] = 1;
            edge[b][a] = 1;
        }

        // solve
        Queue<Integer> queue = new LinkedList<Integer>();

        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {

            int node = queue.poll();

            for (int i = 1; i < N + 1; i++) {

                if (edge[node][i] == 1 && !visited[i]) {

                    queue.add(i);
                    visited[i] = true;
                }
            }
        }

        int count = 0;

        for (int i = 1; i < N + 1; i++) {

            if (visited[i]) {

                count++;
            }
        }


        System.out.println(count - 1);

    }
}
