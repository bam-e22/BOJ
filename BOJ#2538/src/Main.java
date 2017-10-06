import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    static int ROW;
    static int COL;
    static int K;

    static boolean[][] discovered = new boolean[101][101];
    static int[] sections = new int[101];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ROW = Integer.parseInt(st.nextToken());
        COL = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {

            st = new StringTokenizer(br.readLine());

            int col1 = Integer.parseInt(st.nextToken());
            int row1 = Integer.parseInt(st.nextToken());
            int col2 = Integer.parseInt(st.nextToken());
            int row2 = Integer.parseInt(st.nextToken());

            for (int row = row1; row < row2; row++) {
                for (int col = col1; col < col2; col++) {

                    discovered[row][col] = true;
                }
            }
        }

        int cnt = 0;

        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {

                if (!discovered[row][col]) {

                    bfs(row, col, cnt);
                    cnt++;
                }
            }
        }

        System.out.println(cnt);

        Arrays.sort(sections, 0, cnt);
        for (int i = 0; i < cnt; i++) {

            System.out.print(sections[i] + " ");
        }
    }

    static void bfs(int row, int col, int cnt) {

        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(row, col));
        discovered[row][col] = true;
        sections[cnt] = 1;

        while (!queue.isEmpty()) {

            Node u = queue.poll();

            for (int i = 0; i < 4; i++) {

                int nextRow = u.row + dRow[i];
                int nextCol = u.col + dCol[i];

                if (nextRow >= ROW || nextCol >= COL || nextRow < 0 || nextCol < 0) continue;
                if (discovered[nextRow][nextCol]) continue;

                queue.add(new Node(nextRow, nextCol));
                discovered[nextRow][nextCol] = true;
                sections[cnt]++;
            }
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
