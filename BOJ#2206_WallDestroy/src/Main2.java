import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ#2206 벽 부수고 이동하기
 * https://www.acmicpc.net/problem/2206
 */

public class Main2 {

    static final int WALL = 1;
    static final int INF = 10000000;

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    static int[][] map = new int[1001][1001];
    static int[][][] dist = new int[1001][1001][2];

    static {

        for (int i = 0; i < 1001; i++) {

            for (int j = 0; j < 1001; j++) {

                for (int k = 0; k < 2; k++) {

                    dist[i][j][k] = INF;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        int N, M; // <= 1,000

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {

            String s = br.readLine();

            for (int j = 0; j < M; j++) {

                map[i][j] = s.charAt(j) - '0';
            }
        }

        PriorityQueue<Node2> queue = new PriorityQueue<Node2>();

        queue.add(new Node2(0, 0, 1, 0));
        dist[0][0][0] = 1;
        dist[0][0][1] = 1;

        while (!queue.isEmpty()) {

            Node2 u = queue.poll();

            // 중복 제거
            if (dist[u.row][u.col][u.step] < u.cost) {

                continue;
            }

            if (u.row == N - 1 && u.col == M - 1) {

                break;
            }

            // 탐색
            for (int i = 0; i < 4; i++) {

                int nextRow = u.row + dRow[i];
                int nextCol = u.col + dCol[i];

                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;

                if (map[nextRow][nextCol] == WALL) {

                    // 벽을 부수는 경우
                    if (u.step < 1 && dist[nextRow][nextCol][1] > dist[u.row][u.col][0] + 1) {

                        dist[nextRow][nextCol][1] = dist[u.row][u.col][0] + 1;

                        queue.add(new Node2(nextRow, nextCol, dist[nextRow][nextCol][1], 1));
                    }

                } else {

                    if (dist[nextRow][nextCol][u.step] > dist[u.row][u.col][u.step] + 1) {

                        dist[nextRow][nextCol][u.step] = dist[u.row][u.col][u.step] + 1;

                        queue.add(new Node2(nextRow, nextCol, dist[nextRow][nextCol][u.step], u.step));
                    }
                }
            }
        }

        int ans = Math.min(dist[N - 1][M - 1][0], dist[N - 1][M - 1][1]);
        System.out.println(ans >= INF ? -1 : ans);
    }
}

class Node2 implements Comparable<Node2> {

    int row;
    int col;
    int cost;
    int step;

    Node2(int row, int col, int cost, int step) {

        this.row = row;
        this.col = col;
        this.cost = cost;
        this.step = step;
    }

    @Override
    public int compareTo(Node2 o) {

        return this.cost < o.cost ? -1 : 1;
    }
}