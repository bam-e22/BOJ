import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ#1110 더하기 사이클
 * https://www.acmicpc.net/problem/1110
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int number = N;
        int cnt = 0;


        do {

            int a = number / 10;
            int b = number % 10;

            number = b * 10 + ((a + b) % 10);
            cnt++;

        }
        while (number != N);

        System.out.println(cnt);

    }
}
