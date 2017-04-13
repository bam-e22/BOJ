import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * BOJ#2665 미로 만들기
 * https://www.acmicpc.net/problem/2665
 */

public class Main {

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};
    static final int BLACK = 0;
    static final int WHITE = 1;
    static final int INF = 1000000000;

    static int n;
    static int[][] map = new int[51][51];
    static int[][] dist = new int[51][51];
    static boolean[][] discovered = new boolean[51][51];

    static {

        for (int i = 0; i < 51; i++) {

            Arrays.fill(dist[i], INF);
        }
    }

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {

            String s = br.readLine();
            for (int j = 0; j < n; j++) {

                map[i][j] = s.charAt(j) - '0';
            }
        }

        // solve
        PriorityQueue<Point> pq = new PriorityQueue<Point>();

        pq.add(new Point(0, 0, 0));
        dist[0][0] = 0;

        while (!pq.isEmpty()) {

            Point u = pq.poll();

            if (u.row == n - 1 && u.col == n - 1) break;

            if (dist[u.row][u.col] < u.dist) continue;

            for (int i = 0; i < 4; i++) {

                int nextRow = u.row + dRow[i];
                int nextCol = u.col + dCol[i];

                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
                if (discovered[nextRow][nextCol]) continue;

                // WHITE
                if (map[nextRow][nextCol] == WHITE) {

                    if (dist[nextRow][nextCol] > dist[u.row][u.col]) {

                        dist[nextRow][nextCol] = dist[u.row][u.col];
                    }
                }
                // BLACK
                else {

                    if (dist[nextRow][nextCol] > dist[u.row][u.col] + 1) {

                        dist[nextRow][nextCol] = dist[u.row][u.col] + 1;
                    }
                }

                pq.add(new Point(nextRow, nextCol, dist[nextRow][nextCol]));
                discovered[nextRow][nextCol] = true;
            }

        } // ~while loop

        System.out.println(dist[n-1][n-1]);
    }
}

class Point implements Comparable<Point> {

    int row, col;
    int dist;

    Point(int row, int col, int dist) {

        this.row = row;
        this.col = col;
        this.dist = dist;
    }

    @Override
    public int compareTo(Point o) {

        return this.dist < o.dist ? -1 : 1;
    }
}