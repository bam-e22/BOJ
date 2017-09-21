import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static char[][] map = new char[12][6];
    static boolean[][] discovered = new boolean[12][6];
    static boolean[][] checkedMap = new boolean[12][6];

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++) {

            String inputLine = br.readLine();
            for (int j = 0; j < 6; j++) {

                map[i][j] = inputLine.charAt(j);
            }
        }

        // Solve
        int cnt = 0;
        while (true) {

            clearDiscoveredArr();

            int nBlocks = bfs();
            if (nBlocks >= 4) {

                cnt++;
                updateMap();
            } else if (nBlocks == 0) {

                break;
            }
        }

        System.out.println(cnt);
    }

    static int bfs() {

        int nBlocks = 0;

        for (int row = 0; row < 12; row++) {
            for (int col = 0; col < 6; col++) {

                if (discovered[row][col]) continue;
                if (map[row][col] == '.') continue;

                int nConnectedBlocks = 0;

                Queue<Node> queue = new LinkedList<>();
                Queue<Node> storedQueue = new LinkedList<>();
                queue.add(new Node(row, col));
                storedQueue.add(new Node(row, col));

                discovered[row][col] = true;
                nConnectedBlocks++;

                while (!queue.isEmpty()) {

                    Node u = queue.poll();

                    for (int i = 0; i < 4; i++) {

                        int nextRow = u.row + dRow[i];
                        int nextCol = u.col + dCol[i];

                        if (nextRow < 0 || nextCol < 0 || nextRow >= 12 || nextCol >= 6) continue;
                        if (discovered[nextRow][nextCol]) continue;
                        if (map[nextRow][nextCol] != map[row][col]) continue;

                        queue.add(new Node(nextRow, nextCol));
                        storedQueue.add(new Node(nextRow, nextCol));
                        discovered[nextRow][nextCol] = true;
                        nConnectedBlocks++;
                    }
                }

                if (nConnectedBlocks >= 4) {

                    nBlocks += nConnectedBlocks;

                    while (!storedQueue.isEmpty()) {

                        Node u = storedQueue.poll();
                        checkedMap[u.row][u.col] = true;
                    }
                }
            }
        }

        return nBlocks;
    }

    static void updateMap() {

        Queue<Character> colBlocks = new LinkedList<>();

        for (int col = 0; col < 6; col++) {
            for (int row = 11; row >= 0; row--) {

                if (!checkedMap[row][col]) colBlocks.add(map[row][col]);
            }

            for (int row = 11; row >= 0; row--) {

                if (!colBlocks.isEmpty()) map[row][col] = colBlocks.poll();
                else map[row][col] = '.';
            }
        }
    }

    static void clearDiscoveredArr() {

        for (int i = 0; i < 12; i++) {

            Arrays.fill(discovered[i], false);
            Arrays.fill(checkedMap[i], false);
        }
    }
}

class Node {

    int row, col;

    Node(int row, int col) {

        this.row = row;
        this.col = col;
    }
}