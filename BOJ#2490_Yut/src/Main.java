import java.io.*;
import java.util.StringTokenizer;

/**
 * BOJ#2490 윷놀이
 * https://www.acmicpc.net/problem/2490
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int sum = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 3; i++) {

            sum = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {

                sum += Integer.parseInt(st.nextToken());
            }

            switch (sum) {

                case 0:
                    System.out.println("D");
                    break;

                case 1:
                    System.out.println("C");
                    break;

                case 2:
                    System.out.println("B");
                    break;

                case 3:
                    System.out.println("A");
                    break;

                case 4:
                    System.out.println("E");
                    break;
            }
        }
    }
}
