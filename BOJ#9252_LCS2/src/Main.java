import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ#9252 Longest Common Subsequence 2
 * https://www.acmicpc.net/problem/9252
 */

public class Main {

    static int[][] dp = new int[1001][1001]; // i번째 글자가 마지막 글자인 LCS의 length

    public static void main(String[] args) throws IOException {

        StringBuffer S;
        StringBuffer T;
        int sLength, tLength;
        StringBuffer LCS = new StringBuffer();
        int length;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = new StringBuffer(br.readLine());
        T = new StringBuffer(br.readLine());

        sLength = S.length();
        tLength = T.length();

        for (int i = 0; i < sLength; i++) {

            for (int j = 0; j < tLength; j++) {

                if (S.charAt(i) == T.charAt(j)) {

                    dp[i + 1][j + 1] = dp[i][j] + 1;

                } else {

                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        length = dp[sLength][tLength];

        int i = sLength;
        int j = tLength;
        while (length > 0) {

            if (dp[i][j] == dp[i][j - 1]) {

                j--;

            } else if (dp[i][j] == dp[i - 1][j]) {

                i--;
            } else if (dp[i][j] == dp[i - 1][j - 1] + 1) {

                LCS.append(S.charAt(i - 1));

                i--;
                j--;
                length--;
            }
        }

        LCS.reverse();

        System.out.println(dp[sLength][tLength]);
        System.out.println(LCS);

    } // main
}
