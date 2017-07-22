import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][M + 1];

        for (int i = 1; i < N + 1; i++) {

            String input = br.readLine();
            for (int j = 1; j < M + 1; j++) {

                map[i][j] = input.charAt(j - 1) - '0';
            }
        }

        // solve - bfs
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1, 1));

        boolean[][] discovered = new boolean[N + 1][M + 1];
        discovered[1][1] = true;

        int step = 0;

        while (!queue.isEmpty()) {

            step++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {

                Node u = queue.poll();

                if (u.row == N && u.col == M) {

                    System.out.println(step);
                    return;
                }

                for (int j = 0; j < 4; j++) {

                    int nextRow = u.row + dRow[j];
                    int nextCol = u.col + dCol[j];

                    if (nextRow < 1 || nextRow > N || nextCol < 1 || nextCol > M) continue;
                    if (map[nextRow][nextCol] == 0) continue;
                    if (discovered[nextRow][nextCol]) continue;

                    discovered[nextRow][nextCol] = true;
                    queue.add(new Node(nextRow, nextCol));
                }
            }
        }

        System.out.println(step);
    }
}

class Node {

    int row, col;

    Node(int row, int col) {

        this.row = row;
        this.col = col;
    }
}
