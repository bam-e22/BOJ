import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ#1562 계단 수
 * https://www.acmicpc.net/problem/1562
 */

public class Main {

    static final int MOD = 1_000_000_000;

    // dp[N][L][B] : 길이가 N이고 마지막 수가 L, bitmask가 B인 계단 수의 개수
    // dp[N][L][B] = dp[N-1][L-1] + dp[N-1][L+1]
    static int[][][] dp = new int[101][10][];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());




    }
}
