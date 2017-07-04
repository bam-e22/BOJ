import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    static char[][] map = new char[101][101];
    static boolean[][] visited = new boolean[101][101];

    static int[] nColorSection = new int[2];

    static final int COLORSECTION = 0;
    static final int BLINDSECTION = 1;

    static final int VAILD_VALUD = 'R' + 'G';

    static int N;

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            String inputLine = br.readLine();

            for (int j = 0; j < N; j++) {

                map[i][j] = inputLine.charAt(j);
            }
        }

        // Solve - BFS

        // color/blind loop
        for (int i = 0; i < 2; i++) {

            clearVisited();

            // map loop
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {

                    if (!visited[row][col]) {

                        bfs(new Node(row, col), i);

                        nColorSection[i]++;
                    }
                }
            }
        }

        System.out.println(nColorSection[COLORSECTION] + " " + nColorSection[BLINDSECTION]);
    } // ~main

    static void bfs(Node node, int isBlind) {

        char color = map[node.row][node.col];

        Queue<Node> queue = new LinkedList<>();

        queue.add(node);

        while (!queue.isEmpty()) {

            Node u = queue.poll();

            for (int i = 0; i < 4; i++) {

                int newRow = u.row + dRow[i];
                int newCol = u.col + dCol[i];

                if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) continue;
                if (visited[newRow][newCol]) continue;
                if (isBlind == COLORSECTION && map[newRow][newCol] != color) continue;
                if (isBlind == BLINDSECTION && (color + map[newRow][newCol] != VAILD_VALUD) && map[newRow][newCol] != color)
                    continue;

                visited[newRow][newCol] = true;

                queue.add(new Node(newRow, newCol));
            }
        }
    } // ~bfs

    static void clearVisited() {

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {

                visited[i][j] = false;
            }
        }
    } // ~clearVisited
} // ~Main

class Node {

    int row, col;

    Node(int row, int col) {

        this.row = row;
        this.col = col;
    }
}