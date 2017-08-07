import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static final int NONE = -1;
    static final int FALSE = 0;
    static final int TRUE = 1;

    // Pattern : P, File Name String :S
    static char[] P, S;
    static int pLength, sLength;

    static int[][] dp = new int[102][102];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        P = br.readLine().toCharArray();
        pLength = P.length;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            S = br.readLine().toCharArray();
            sLength = S.length;

            initMemoization();

            if (checkStringPattern(0, 0) == TRUE) System.out.println(S);
        }
    }

    static int checkStringPattern(int p, int s) {

        // 종료 조건
        if (p == pLength && s == sLength) return TRUE;

        if (p >= pLength) return FALSE;

        if (s >= sLength && P[p] != '*') return FALSE;

        // Memoization
        if (dp[p][s] != NONE) {

            return dp[p][s];
        }

        // 탐색
        // Pattern : *
        if (P[p] == '*') {

            int isMatch = FALSE;

            if (s <= sLength) {

                if (checkStringPattern(p + 1, s) == TRUE) isMatch = TRUE;
                if (checkStringPattern(p + 1, s + 1) == TRUE) isMatch = TRUE;
                if (checkStringPattern(p, s + 1) == TRUE) isMatch = TRUE;
            }

            return dp[p][s] = isMatch;
        }
        // Pattern : . 또는 영어 소문자
        else {

            if (P[p] == S[s]) return dp[p][s] = checkStringPattern(p + 1, s + 1);
            else return dp[p][s] = FALSE;
        }
    }

    static void initMemoization() {

        for (int i = 0; i < 102; i++) {

            Arrays.fill(dp[i], NONE);
        }
    }
}
