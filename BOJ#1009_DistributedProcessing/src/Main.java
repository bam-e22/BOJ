import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * BOJ#1009 분산처리
 * https://www.acmicpc.net/problem/1009
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int T;
        BigInteger a, b;
        int computerNum;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            a = new BigInteger(st.nextToken());
            b = new BigInteger(st.nextToken());

            computerNum = a.modPow(b, BigInteger.TEN).intValue();

            System.out.println(computerNum == 0 ? 10 : computerNum);
        }
    }
}