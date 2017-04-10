import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ#2146 다리 만들기
 * https://www.acmicpc.net/problem/2146
 */

public class Main {

    static final int SEA = 0;
    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};
    static final int INF = 1000000;

    static int N;
    static int[][] map = new int[101][101];
    static boolean[][] visited = new boolean[101][101];
    static ArrayList<ArrayList<Seashore>> shores = new ArrayList<ArrayList<Seashore>>();

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // solve
        // 1. search & set nationality
        int nCountry = 0;
        shores.add(new ArrayList<Seashore>());
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (!visited[i][j] && map[i][j] != SEA) {

                    nCountry++;
                    shores.add(new ArrayList<Seashore>());

                    dfs(i, j, nCountry, shores.get(nCountry));
                }
            }
        }

        // 2. make bridge & find min cost
        int minCost = INF;
        for (int i = 1; i <= nCountry; i++) {

            for (Seashore shore : shores.get(i)) {

                minCost = Math.min(minCost, bfs(shore));
            }
        }

        System.out.println(minCost - 1);

    } // ~main

    static ArrayList<Seashore> dfs(int row, int col, int nationality, ArrayList<Seashore> shoreList) {

        boolean isShore = false;

        map[row][col] = nationality;
        visited[row][col] = true;

        for (int i = 0; i < 4; i++) {

            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
            if (visited[nextRow][nextCol]) continue;
            if (map[nextRow][nextCol] == SEA) {

                isShore = true;
                continue;
            }

            dfs(nextRow, nextCol, nationality, shoreList);
        }

        if (isShore) shoreList.add(new Seashore(row, col, nationality));
        return shoreList;
    }

    static int bfs(Seashore p) {

        for (int i = 0; i < N; i++) {

            Arrays.fill(visited[i], false);
        }

        Queue<Seashore> queue = new LinkedList<Seashore>();
        int step = -1;

        queue.add(p);
        visited[p.row][p.col] = true;

        while (!queue.isEmpty()) {

            step++;

            int size = queue.size();
            for (int i = 0; i < size; i++) {

                Seashore u = queue.poll();

                // 종료조건
                if (map[u.row][u.col] != SEA && map[u.row][u.col] != u.nationality) {

                    return step;
                }

                // 탐색
                for (int j = 0; j < 4; j++) {

                    int nextRow = u.row + dRow[j];
                    int nextCol = u.col + dCol[j];

                    if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
                    if (visited[nextRow][nextCol]) continue;
                    if (map[nextRow][nextCol] != SEA && map[nextRow][nextCol] == map[u.row][u.col]) continue;

                    queue.add(new Seashore(nextRow, nextCol, u.nationality));
                    visited[nextRow][nextCol] = true;
                }
            }
        }

        return INF;
    }
}

class Seashore {

    int nationality;
    int row;
    int col;

    Seashore(int row, int col, int nationality) {

        this.row = row;
        this.col = col;
        this.nationality = nationality;
    }
}

