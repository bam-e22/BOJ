import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ#7682 틱택토
 * https://www.acmicpc.net/problem/7682
 */

public class Main {

    static final int BLANK = 0;
    static final int X = 1;
    static final int O = 2;

    public static void main(String[] args) throws IOException {

        String s = "";
        int[][] map = new int[3][3];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            s = br.readLine();

            if (s.equals("end")) break;

            for (int i = 0; i < 9; i++) {

                char c = s.charAt(i);
                int row = i / 3;
                int col = i % 3;

                switch (c) {

                    case '.':
                        map[row][col] = BLANK;
                        break;

                    case 'X':
                        map[row][col] = X;
                        break;

                    case 'O':
                        map[row][col] = O;
                        break;
                }
            }



        } // ~while loop
    }


    // Check - 더 움직일 수 있는 경우
    // Check - 더 움직인 경우 (완료 기준 이상, nO > nX)


}
