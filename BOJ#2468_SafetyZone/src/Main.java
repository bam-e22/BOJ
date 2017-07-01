import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ#2468 안전영역
 * https://www.acmicpc.net/problem/2468
 */

public class Main {

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    static int[][] map = new int[101][101];
    static boolean[][] visited = new boolean[101][101];

    static int minValue = Integer.MAX_VALUE;
    static int maxValue = 0;

    public static void main(String[] args) throws IOException {

        int N;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {

                int a = Integer.parseInt(st.nextToken());

                map[i][j] = a;
                maxValue = maxValue < a ? a : maxValue;
                minValue = minValue > a ? a : minValue;
            }
        }

        // solve

        int ans = 1;

        while (minValue <= maxValue) {

            int tempAns = 0;

            visitedInit(N);

            Queue<Node> queue = new LinkedList<Node>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    if (!visited[i][j] && map[i][j] > minValue) {

                        queue.add(new Node(i, j));

                        visited[i][j] = true;
                        tempAns++;

                        while (!queue.isEmpty()) {

                            Node node = queue.poll();

                            for (int k = 0; k < 4; k++) {

                                int nextRow = node.row + dRow[k];
                                int nextCol = node.col + dCol[k];

                                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;

                                // 침수 지역이면
                                if (map[nextRow][nextCol] <= minValue || visited[nextRow][nextCol]) {

                                    continue;
                                }

                                queue.add(new Node(nextRow, nextCol));
                                visited[nextRow][nextCol] = true;
                            }
                        }
                    }
                }
            }

            ans = ans < tempAns ? tempAns : ans;
            minValue++;
        }

        System.out.println(ans);
    }

    static void visitedInit(int N) {

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {

                visited[i][j] = false;
            }
        }
    }
}

class Node {

    int row;
    int col;

    Node(int row, int col) {

        this.row = row;
        this.col = col;
    }
}
