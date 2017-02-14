import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * BOJ#2577 숫자의 개수
 * https://www.acmicpc.net/problem/2577
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int A = Integer.parseInt(br.readLine().trim());
        int B = Integer.parseInt(br.readLine().trim());
        int C = Integer.parseInt(br.readLine().trim());

        String result = String.valueOf(A * B * C);

        int[] numOfNums = new int[10];

        Arrays.fill(numOfNums, 0);

        for (int i = 0; i < result.length(); i++) {

            numOfNums[result.charAt(i) - '0']++;
        }

        for (int i = 0; i < 10; i++) {

            System.out.println(numOfNums[i]);
        }
    }
}
