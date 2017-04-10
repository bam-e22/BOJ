import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ#2589 보물섬
 * https://www.acmicpc.net/problem/2589
 */

public class Main {

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    static int nRow, nCol;
    static int[][] map = new int[51][51];
    static boolean[][] discovered = new boolean[51][51];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        nRow = Integer.parseInt(st.nextToken());
        nCol = Integer.parseInt(st.nextToken());

        for (int i = 0; i < nRow; i++) {

            String s = br.readLine();
            for (int j = 0; j < nCol; j++) {

                char c = s.charAt(j);
                map[i][j] = c == 'L' ? 0 : -1;
            }
        }

        int maxDistance = 0;
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {

                if (map[i][j] == -1) continue;
                maxDistance = Math.max(maxDistance, bfs(i, j));
            }
        }

        System.out.println(maxDistance);
    }

    static int bfs(int row, int col) {

        int step = -1;

        initArr();

        Queue<Point> queue = new LinkedList<Point>();
        queue.add(new Point(row, col));
        discovered[row][col] = true;

        while (!queue.isEmpty()) {

            step++;

            int size = queue.size();
            for (int i = 0; i < size; i++) {

                Point u = queue.poll();

                for (int j = 0; j < 4; j++) {

                    int nextRow = u.row + dRow[j];
                    int nextCol = u.col + dCol[j];

                    if (nextRow < 0 || nextRow >= nRow || nextCol < 0 || nextCol >= nCol) continue;
                    if (discovered[nextRow][nextCol]) continue;
                    if (map[nextRow][nextCol] == -1) continue;

                    queue.add(new Point(nextRow, nextCol));
                    discovered[nextRow][nextCol] = true;
                }
            }
        }

        return step;
    }

    static void initArr() {

        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {

                discovered[i][j] = false;
            }
        }
    }
}

class Point {

    int row, col;

    Point(int row, int col) {

        this.row = row;
        this.col = col;
    }
}