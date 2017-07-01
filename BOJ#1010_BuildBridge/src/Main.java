import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * BOJ#1010 다리 놓기
 * https://www.acmicpc.net/problem/1010
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int T;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if (N == M) {

                System.out.println(1);
                continue;
            }

            BigInteger num = new BigInteger("1");

            // nCr = n! / ((n-r)! * r!)
            for (int i = M; i > M - N; i--) {

                num = num.multiply(BigInteger.valueOf(i));
            }

            for (int i = N; i > 0; i--) {

                num = num.divide(BigInteger.valueOf(i));
            }

            System.out.println(num);

        }
    }
}
