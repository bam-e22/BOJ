import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int[][][] bulb = {
            {{1, 1, 1}, {1, 0, 1}, {1, 0, 1}, {1, 0, 1}, {1, 1, 1}},
            {{0, 0, 1}, {0, 0, 1}, {0, 0, 1}, {0, 0, 1}, {0, 0, 1}},
            {{1, 1, 1}, {0, 0, 1}, {1, 1, 1}, {1, 0, 0}, {1, 1, 1}},
            {{1, 1, 1}, {0, 0, 1}, {1, 1, 1}, {0, 0, 1}, {1, 1, 1}},
            {{1, 0, 1}, {1, 0, 1}, {1, 1, 1}, {0, 0, 1}, {0, 0, 1}},
            {{1, 1, 1}, {1, 0, 0}, {1, 1, 1}, {0, 0, 1}, {1, 1, 1}},
            {{1, 1, 1}, {1, 0, 0}, {1, 1, 1}, {1, 0, 1}, {1, 1, 1}},
            {{1, 1, 1}, {0, 0, 1}, {0, 0, 1}, {0, 0, 1}, {0, 0, 1}},
            {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}, {1, 0, 1}, {1, 1, 1}},
            {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}, {0, 0, 1}, {1, 1, 1}}
    };
    //static int[][] possibleNum = new int[10][10];
    static ArrayList<ArrayList<Integer>> possibleNum = new ArrayList<>();

    static {

        // possibleNum
        for (int i = 0; i < 10; i++) {

            //Arrays.fill(possibleNum[i], -1);
            possibleNum.add(new ArrayList<>());
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[5][4 * N - 1];

        for (int i = 0; i < 5; i++) {

            String input = br.readLine();

            for (int j = 0; j < 4 * N - 1; j++) {

                char c = input.charAt(j);

                if (c == '#') map[i][j] = 1;
                else if (c == '.') map[i][j] = 0;
            }
        }

        for (int i = 0; i < N; i++) {

            checkPossibleNum(map, i);
        }

        double ave = 0.0;
        for (int i = 0; i < N; i++) {

            double temp = 0.0;
            for (int num : possibleNum.get(i)) {

                temp += (num * Math.pow(10, N - i - 1));
            }

            if (possibleNum.get(i).size() != 0) {

                temp /= (double) possibleNum.get(i).size();
            }

            ave += temp;
        }

        if (ave == 0.0) {

            System.out.println(-1);
        } else {

            System.out.printf("%.10f", ave);
        }
    }

    static void checkPossibleNum(int[][] map, int idx) {

        for (int num = 0; num < 10; num++) {

            boolean ret = true;
            for (int row = 0; row < 5 && ret; row++) {
                for (int col = idx * 4; col < idx * 4 + 3 && ret; col++) {

                    if (bulb[num][row][col % 4] == 0 && map[row][col] == 1) {

                        ret = false;
                    }
                }
            }

            if (ret) possibleNum.get(idx).add(num);
        }
    }
}
