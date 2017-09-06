import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final int WALL = -1;
    private static final int EMPTY = 0;
    private static final int FIRE = 2;

    private static final int[] dRow = {0, -1, 0, 1};
    private static final int[] dCol = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[][] map = new int[R][C];

        boolean[][] discovered = new boolean[R][C];
        boolean[][] fireDiscovered = new boolean[R][C];

        Queue<Node> queue = new LinkedList<>();
        Queue<Node> fireQueue = new LinkedList<>();

        for (int i = 0; i < R; i++) {

            String inputLine = br.readLine();
            for (int j = 0; j < C; j++) {

                char c = inputLine.charAt(j);

                switch (c) {

                    case '.':

                        map[i][j] = EMPTY;
                        break;

                    case '#':

                        map[i][j] = WALL;
                        break;

                    case 'F':

                        map[i][j] = FIRE;
                        fireQueue.add(new Node(i, j));
                        fireDiscovered[i][j] = true;
                        break;

                    case 'J':

                        map[i][j] = EMPTY;
                        queue.add(new Node(i, j));
                        discovered[i][j] = true;
                        break;
                }
            }
        }

        // Solve
        int step = -1;

        while (!queue.isEmpty()) {

            step++;

            // fire
            if (!fireQueue.isEmpty()) {

                int size = fireQueue.size();

                for (int i = 0; i < size; i++) {

                    Node u = fireQueue.poll();

                    // 탐색
                    for (int j = 0; j < 4; j++) {

                        int nextRow = u.row + dRow[j];
                        int nextCol = u.col + dCol[j];

                        if (nextRow < 0 || nextCol < 0 || nextRow >= R || nextCol >= C) continue;
                        if (fireDiscovered[nextRow][nextCol]) continue;
                        if (map[nextRow][nextCol] == WALL) continue;

                        map[nextRow][nextCol] = FIRE;

                        fireQueue.add(new Node(nextRow, nextCol));
                        fireDiscovered[nextRow][nextCol] = true;
                    }
                }
            }

            // humal
            int size = queue.size();

            for (int i = 0; i < size; i++) {

                Node u = queue.poll();

                if (u.row == 0 || u.col == 0 || u.row == R - 1 || u.col == C - 1) {

                    System.out.println(step + 1);
                    return;
                }

                // 탐색
                for (int j = 0; j < 4; j++) {

                    int nextRow = u.row + dRow[j];
                    int nextCol = u.col + dCol[j];

                    if (nextRow < 0 || nextCol < 0 || nextRow >= R || nextCol >= C) continue;
                    if (discovered[nextRow][nextCol]) continue;
                    if (map[nextRow][nextCol] == WALL) continue;
                    if (map[nextRow][nextCol] == FIRE) continue;

                    queue.add(new Node(nextRow, nextCol));
                    discovered[nextRow][nextCol] = true;
                }
            }
        }

        System.out.println("IMPOSSIBLE");
    } // ~main
}

class Node {

    int row, col;

    Node(int row, int col) {

        this.row = row;
        this.col = col;
    }
}