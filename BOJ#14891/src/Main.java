import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] state = new int[4][8];

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {

            String input = br.readLine();

            for (int j = 0; j < 8; j++) {

                state[i][j] = input.charAt(j) - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());

        while (K-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            rotate(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()), 0);
        }

        System.out.println(getScore());
    }

    static void rotate(int num, int dir, int toDir) {

        if (toDir <= 0 && num > 0 && (state[num - 1][2] != state[num][6])) {

            rotate(num - 1, -dir, -1);
        }

        if (toDir >= 0 && num < 3 && (state[num][2] != state[num + 1][6])) {

            rotate(num + 1, -dir, 1);
        }

        if (dir == 1) rightRotate(state[num], 0, 7);
        if (dir == -1) leftRotate(state[num], 0, 7);
    }

    static int getScore() {

        int score = 0;

        if (state[0][0] == 1) score += 1;
        if (state[1][0] == 1) score += 2;
        if (state[2][0] == 1) score += 4;
        if (state[3][0] == 1) score += 8;

        return score;
    }

    static void rightRotate(int[] arr, int start, int end) {

        int last = arr[end];

        for (int i = end; i > start; i--) {

            arr[i] = arr[i - 1];
        }

        arr[start] = last;
    }

    static void leftRotate(int[] arr, int start, int end) {

        int first = arr[start];

        for (int i = start; i < end; i++) {

            arr[i] = arr[i + 1];
        }

        arr[end] = first;
    }
}
