import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        int[][] edge = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            edge[x][y] = 1;
            edge[y][x] = 1;
        }

        // Solve
        System.out.println(bfs(edge, S, D, n));


    }

    static int bfs(int[][] edge, int S, int D, int N) {

        boolean[] discovered = new boolean[N + 1];

        int ret = -1;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(S);

        discovered[S] = true;

        while (!queue.isEmpty()) {

            ret++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int node = queue.poll();

                if (node == D) {

                    return ret;
                }

                for (int adjNode = 1; adjNode < N + 1; adjNode++) {

                    if (edge[node][adjNode] == 1 && !discovered[adjNode]) {

                        queue.add(adjNode);
                        discovered[adjNode] = true;
                    }
                }
            }
        }

        return -1;
    }
}
