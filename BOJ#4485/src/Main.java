import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 100000000;

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nCase = 0;

        while (true) {

            nCase++;

            // Input
            int N = Integer.parseInt(br.readLine());

            if (N == 0) return;

            int[][] map = new int[N][N];
            int[][] dist = new int[N][N];

            for (int i = 0; i < N; i++) {

                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {

                    map[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = INF;
                }
            }

            // Solve
            System.out.println("Problem " + nCase + ": " + dijkstra(new Node(0, 0, map[0][0]), map, N, dist));

        } // ~test case loop
    }

    static int dijkstra(Node src, int[][] map, int N, int[][] dist) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(src);

        dist[src.row][src.col] = map[src.row][src.col];

        while (!pq.isEmpty()) {

            Node u = pq.poll();

            if (dist[u.row][u.col] < u.cost) continue;

            for (int i = 0; i < 4; i++) {

                int nextRow = u.row + dRow[i];
                int nextCol = u.col + dCol[i];

                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;

                if (dist[nextRow][nextCol] > dist[u.row][u.col] + map[nextRow][nextCol]) {

                    dist[nextRow][nextCol] = dist[u.row][u.col] + map[nextRow][nextCol];
                    pq.add(new Node(nextRow, nextCol, dist[nextRow][nextCol]));
                }
            }
        }

        return dist[N - 1][N - 1];
    }
}

class Node implements Comparable<Node> {

    int row, col;
    int cost;

    Node(int row, int col, int cost) {

        this.row = row;
        this.col = col;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {

        return this.cost <= o.cost ? -1 : 1;
    }
}