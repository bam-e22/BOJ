import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int ROW;
    static int COL;

    static int[][] map = new int[301][301];
    static int[][] deleteMap = new int[301][301];

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ROW = Integer.parseInt(st.nextToken());
        COL = Integer.parseInt(st.nextToken());

        for (int i = 0; i < ROW; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < COL; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // solve
        int cnt;
        int step = 0;
        boolean isUpdated;

        do {

            isUpdated = setDeleteMap();

            if (isUpdated) {

                cnt = getCount();
                step++;
            }
            else {

                break;
            }
        }
        while (cnt < 2);

        System.out.println(!isUpdated ? 0 : step);
    }

    static boolean setDeleteMap() {

        boolean isUpdated = false;

        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {

                if (map[row][col] == 0) continue;

                int cnt = 0;
                for (int k = 0; k < 4; k++) {

                    int nextRow = row + dRow[k];
                    int nextCol = col + dCol[k];

                    if (nextRow < 0 || nextCol < 0 || nextRow >= ROW || nextCol >= COL) continue;
                    if (map[nextRow][nextCol] == 0) cnt++;
                }

                if (cnt > 0) {

                    isUpdated = true;
                }
                deleteMap[row][col] = cnt;
            }
        }

        if (isUpdated) {

            for (int row = 0; row < ROW; row++) {
                for (int col = 0; col < COL; col++) {

                    map[row][col] = map[row][col] - deleteMap[row][col] < 0 ? 0 : map[row][col] - deleteMap[row][col];
                }
            }
        }

        return isUpdated;
    }

    static void bfs(int row, int col, boolean[][] discovered) {

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(row, col));
        discovered[row][col] = true;

        while(!queue.isEmpty()) {

            Node u = queue.poll();

            for (int i = 0; i < 4; i++) {

                int nextRow = u.row + dRow[i];
                int nextCol = u.col + dCol[i];

                if (nextRow < 0 || nextCol < 0 || nextRow >= ROW || nextCol >= COL) continue;
                if (discovered[nextRow][nextCol]) continue;
                if (map[nextRow][nextCol] == 0) continue;

                queue.add(new Node(nextRow, nextCol));
                discovered[nextRow][nextCol] = true;
            }
        }
    }

    static int getCount() {

        int cnt = 0;
        boolean[][] discovered = new boolean[ROW][COL];

        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {

                if (!discovered[row][col] && map[row][col] > 0) {

                    bfs(row, col, discovered);
                    cnt++;
                    if (cnt == 2) return cnt;
                }
            }
        }

        return cnt;
    }
}

class Node {

    int row, col;

    Node(int row, int col) {

        this.row = row;
        this.col = col;
    }
}