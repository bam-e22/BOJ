import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ#1857 발레리노
 * https://www.acmicpc.net/problem/1857
 */

public class Main {

    static final int BLANK = 0;
    static final int SAFE = 1;
    static final int STONE = 2;
    static final int START = 3;
    static final int END = 4;

    static final int INF = Integer.MAX_VALUE;
    static final int NO_SOLUTION = -1;
    static final int[] dRow = {-1, -2, -2, -1, 1, 2, 2, 1};
    static final int[] dCol = {-2, -1, 1, 2, 2, 1, -1, -2};

    static int m, n;
    static int[][] map = new int[31][31];
    static int[][] dist = new int[31][31];
    static boolean[][][][] visited = new boolean[31][31][31][31];

    static {

        for (int i = 0; i < 31; i++) {

            Arrays.fill(dist[i], INF);
        }
    }

    public static void main(String[] args) throws IOException {

        int startRow = 0, startCol = 0;
        HashMap<Integer, Long> solution = new HashMap<Integer, Long>();

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == START) {

                    startRow = i;
                    startCol = j;
                }
            }
        }

        // solve - bfs, 1) 최소 방석의 수, 2) 방석을 놓는 방법의 수
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        int minCost = Integer.MAX_VALUE;

        queue.add(new Node(startRow, startCol, 0));
        dist[startRow][startCol] = 0;

        while (!queue.isEmpty()) {

            Node u = queue.poll();

            // 종료조건
            if (map[u.row][u.col] == END) {

                solution.put(dist[u.row][u.col], solution.get(dist[u.row][u.col]) == null ? 1 : solution.get(dist[u.row][u.col]) + 1);
                minCost = minCost > u.cost ? u.cost : minCost;
            }

            // 중복 제거
            if (dist[u.row][u.col] < u.cost) {

                continue;
            }

            // 탐색
            for (int i = 0; i < 8; i++) {

                int nextRow = u.row + dRow[i];
                int nextCol = u.col + dCol[i];

                if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n) continue;
                if (map[nextRow][nextCol] == STONE) continue;

                if (map[nextRow][nextCol] == SAFE && !visited[u.row][u.col][nextRow][nextCol]) {

                    //if (dist[nextRow][nextCol] >= dist[u.row][u.col]) {
                    if (dist[nextRow][nextCol] > dist[u.row][u.col]) {

                        dist[nextRow][nextCol] = dist[u.row][u.col];
                        queue.add(new Node(nextRow, nextCol, u.cost));
                        visited[u.row][u.col][nextRow][nextCol] = true;
                    }

                } else if (map[nextRow][nextCol] == BLANK) {

                    if (dist[nextRow][nextCol] >= dist[u.row][u.col] + 1) {

                        dist[nextRow][nextCol] = dist[u.row][u.col] + 1;
                        queue.add(new Node(nextRow, nextCol, u.cost + 1));
                    }
                } else if (map[nextRow][nextCol] == END) {

                    if (dist[nextRow][nextCol] >= dist[u.row][u.col]) {

                        dist[nextRow][nextCol] = dist[u.row][u.col];
                        queue.add(new Node(nextRow, nextCol, u.cost));
                    }

                }
            }
        }

        if (solution.size() == 0) {

            System.out.println(NO_SOLUTION);
        } else {

            System.out.println(minCost);
            System.out.println(solution.get(minCost));
        }
    } // main
}

class Node implements Comparable<Node> {

    int row;
    int col;
    int cost;

    Node(int row, int col, int cost) {

        this.row = row;
        this.col = col;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {

        return this.cost < o.cost ? -1 : 1;
    }
}