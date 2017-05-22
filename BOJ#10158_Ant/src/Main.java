import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#10158 개미
 * https://www.acmicpc.net/problem/10158
 */

public class Main {

    static final int UP_RIGHT = 0;
    static final int UP_LEFT = 1;
    static final int DOWN_RIGHT = 2;
    static final int DOWN_LEFT = 3;
    static final int[] dRow = {1, 1, -1, -1};
    static final int[] dCol = {1, -1, 1, -1};

    public static void main(String[] args) throws IOException {

        int w, h;
        int row, col;
        int t;
        int dir = UP_RIGHT;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {

            row += dRow[dir];
            col += dCol[dir];

            //System.out.println(row + " " + col);

            if (row < h && row > 0 && col < w && col > 0) continue;

            switch (dir) {

                case UP_RIGHT:

                    if (row == h && col == w) dir = DOWN_LEFT;
                    else if (row == h) dir = DOWN_RIGHT;
                    else if (col == w) dir = UP_LEFT;
                    break;

                case UP_LEFT:
                    if (row == h && col == 0) dir = DOWN_RIGHT;
                    else if (row == h) dir = DOWN_LEFT;
                    else if (col == 0) dir = UP_RIGHT;
                    break;

                case DOWN_RIGHT:
                    if (row == 0 && col == w) dir = UP_LEFT;
                    else if (row == 0) dir = UP_RIGHT;
                    else if (col == w) dir = DOWN_LEFT;
                    break;

                case DOWN_LEFT:
                    if (row == 0 && col == 0) dir = UP_RIGHT;
                    else if (row == 0) dir = UP_LEFT;
                    else if (col == 0) dir = DOWN_RIGHT;
                    break;
            }
        } // ~while loop

        System.out.println(col + " " + row);

    }
}
