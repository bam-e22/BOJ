import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#9328 열쇠
 * https://www.acmicpc.net/problem/9328
 */

public class Main {

    static final int BLANK = -1;
    static final int WALL = -2;
    static final int DOC = 100;

    static int[][] map = new int[101][101];

    public static void main(String[] args) throws IOException {

        int t;
        int h, w;
        char[] keys = new char[26];

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            for (int i = 0; i < h; i++) {

                String s = br.readLine();
                for (int j = 0; j < w; j++) {

                    char c = s.charAt(j);

                    if (c == '.') {

                        map[i][j] = BLANK;
                    } else if (c == '*') {

                        map[i][j] = WALL;
                    } else if (c == '$') {

                        map[i][j] = DOC;
                    } else if (c >= 'A' && c <= 'Z') {

                        map[i][j] = c - 'A';
                    } else if (c >= 'a' && c <= 'z') {

                        map[i][j] = c - 'a';
                    }
                }
            }

            printMap(h, w);

        } // test case loop
    } // main

    static void printMap(int h, int w) {

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {

                System.out.printf("%4d", map[i][j]);
            }
            System.out.println();
        }
    }
}
