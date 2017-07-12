import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, R, G, B;
    static long[][][][] dp = new long[11][101][101][101];
    static long[][] dp_combination = new long[11][11];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());


        System.out.println(solve(N, R, G, B));

    }

    // return dp[N][R][G][B]
    static long solve(int n, int r, int g, int b) {

        if (r < 0 || g < 0 || b < 0) return 0;
        if (n <= 0) return 1;

        // Memoization
        if (dp[n][r][g][b] != 0) return dp[n][r][g][b];

        // 레벨 N에서, 3가지 경우 탐색
        // 1가지 색만 쓰는 경우
        dp[n][r][g][b] += solve(n - 1, r - n, g, b);
        dp[n][r][g][b] += solve(n - 1, r, g - n, b);
        dp[n][r][g][b] += solve(n - 1, r, g, b - n);

        // 2가지 색만 쓰는 경우
        if (n % 2 == 0) {

            int piece = n / 2;
            long combination = getCombination(n, piece, 2);

            dp[n][r][g][b] += combination * solve(n - 1, r - piece, g - piece, b);
            dp[n][r][g][b] += combination * solve(n - 1, r, g - piece, b - piece);
            dp[n][r][g][b] += combination * solve(n - 1, r - piece, g, b - piece);
        }

        // 3가지 색만 쓰는 경우
        if (n % 3 == 0) {

            int piece = n / 3;
            long combination = getCombination(n, piece, 3);

            dp[n][r][g][b] += combination * solve(n - 1, r - piece, g - piece, b - piece);
        }

        return dp[n][r][g][b];
    }

    static long getCombination(int n, int piece, int k) {

        // memoization
        if (dp_combination[n][piece] != 0) return dp_combination[n][piece];

        long ret = 1L;

        for (int i = 1; i <= n; i++) {

            ret *= i;
        }

        for (int i = 0; i < k; i++) {

            for (int j = 1; j <= piece; j++) {

                ret /= j;
            }
        }

        return dp_combination[n][piece] = ret;
    }
}