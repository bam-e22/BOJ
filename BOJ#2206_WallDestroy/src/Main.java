import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ#2206 벽 부수고 이동하기
 * https://www.acmicpc.net/problem/2206
 */

public class Main {

    static final int WALL = 1;
    static final int YES = 1;
    static final int NO = 0;
    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    static int[][] map = new int[1001][1001];
    static boolean[][][] discovered = new boolean[1001][1001][2];

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

        Queue<Node> queue = new LinkedList<Node>();

        queue.add(new Node(0, 0, 0));
        discovered[0][0][NO] = true;
        discovered[0][0][YES] = true;

        boolean isSuccess = false;
        int step = 0;
        while (!queue.isEmpty() && !isSuccess) {

            step++;

            int size = queue.size();
            for (int i = 0; i < size; i++) {

                Node u = queue.poll();

                if (u.row == N - 1 && u.col == M - 1) {

                    isSuccess = true;
                    break;
                }

                // 탐색
                for (int j = 0; j < 4; j++) {

                    int nextRow = u.row + dRow[j];
                    int nextCol = u.col + dCol[j];

                    if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;

                    // 벽
                    if (map[nextRow][nextCol] == WALL) {

                        if (u.destroy < YES && !discovered[nextRow][nextCol][1]) {

                            queue.add(new Node(nextRow, nextCol, YES));
                            discovered[nextRow][nextCol][YES] = true;
                        }
                    }
                    // 빈칸
                    else {

                        if (!discovered[nextRow][nextCol][u.destroy]) {

                            queue.add(new Node(nextRow, nextCol, u.destroy));
                            discovered[nextRow][nextCol][u.destroy] = true;
                        }
                    }
                }
            }
        }

        System.out.println(isSuccess ? step : -1);
    }
}

class Node {

    int row;
    int col;
    int destroy;

    Node(int row, int col, int destroy) {

        this.row = row;
        this.col = col;
        this.destroy = destroy;
    }
}