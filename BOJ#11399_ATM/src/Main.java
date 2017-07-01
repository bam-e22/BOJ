import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#1700 ATM
 * https://www.acmicpc.net/problem/11399
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int N;
        int[] P;
        int sum = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        P = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {

            P[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(P);

        for (int i = 0; i < N; i++) {

            for (int j = 0; j <= i; j++) {

                sum += P[j];
            }
        }

        System.out.println(sum);
    }
}
