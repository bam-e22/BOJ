import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * BOJ#2667 단지번호붙이기
 * https://www.acmicpc.net/problem/2667
 */

public class Main {

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    static int N;
    static int[][] map = new int[25][25];
    static boolean[][] visited = new boolean[25][25];
    static ArrayList<Integer> a = new ArrayList<Integer>();

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            String s = br.readLine();

            for (int j = 0; j < N; j++) {

                map[i][j] = s.charAt(j) - '0';
            }
        }

        // solve
        int name = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (!visited[i][j] && map[i][j] != 0) {

                    name++;

                    int cnt = dfs(i, j, name, 0);
                    a.add(cnt);
                }
            }
        }

        Collections.sort(a);

        System.out.println(name);
        for (int x : a) {

            System.out.println(x);
        }
    }

    static int dfs(int row, int col, int name, int cnt) {

        cnt++;
        visited[row][col] = true;

        for (int i = 0; i < 4; i++) {

            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
            if (map[nextRow][nextCol] == 0) continue;
            if (visited[nextRow][nextCol]) continue;

            cnt = dfs(nextRow, nextCol, name, cnt);
        }

        return cnt;
    }
}