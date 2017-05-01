import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#9084 동전
 * https://www.acmicpc.net/problem/9084
 */

public class Main {

    public static void main(String[] argS) throws IOException {

        int N;
        int M;
        int[] coin;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            N = Integer.parseInt(br.readLine());
            coin = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {

                coin[i] = Integer.parseInt(st.nextToken());
            }

            M = Integer.parseInt(br.readLine());

            int[] dp = new int[M + 1];
            Arrays.fill(dp, 0);

            dp[0] = 1;
            for (int i = 0; i < N; i++) {
                for (int j = coin[i]; j <= M; j++) {

                    dp[j] += dp[j - coin[i]];
                }
            }

            System.out.println(dp[M]);

        } // ~ testcase loop
    }

}
