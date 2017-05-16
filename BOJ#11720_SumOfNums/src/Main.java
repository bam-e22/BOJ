import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ#11720 숫자의 합
 * https://www.acmicpc.net/problem/11720
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        char[] input = br.readLine().toCharArray();
        int sum = 0;

        for (char x : input) {

            sum += (x - '0');
        }

        System.out.println(sum);
    }
}
