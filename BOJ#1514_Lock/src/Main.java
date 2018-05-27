import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * BOJ#1514 자물쇠
 * https://www.acmicpc.net/problem/1514
 */

public class Main {

    static final int LIMIT = 3;
    static final boolean DEBUG = true;
    static final int INF = 100000000;

    static int N; // 자물쇠의 크기 <= 100
    static int lockValue;
    static int password;
    static int[][] dp = new int[101][10];

    static {

        for (int i = 0; i < 101; i++) {

            Arrays.fill(dp[i], -1);
        }
    }

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());
        lockValue = Integer.parseInt(br.readLine().trim());
        password = Integer.parseInt(br.readLine().trim());

        if (DEBUG) System.out.println(N);
        if (DEBUG) System.out.println(lockValue);
        if (DEBUG) System.out.println(password);

        // solve
        // 인접한 최대 3개 디스크, 최대 3칸(-3 ~ +3)


    }

    // return dp[a][b], a < b
    static int solve(int a, int b) {

        // memoization
        if (dp[a][b] != -1) {

            return dp[a][b];
        }

        int diff = Math.abs(a - b);

        // 차이가 3이 넘는 경우

        if (a == b) {

            dp[a][b] = 0;
            dp[b][a] = 0;
        } else if (diff > LIMIT) {

            int min = INF;
            for (int i = a; i <= b; i++) {

                min = Math.min(min, solve(i, b));
            }
        }
        // 차이가 3이 안넘는 경우
        else {

            dp[a][b] = 1;
            dp[b][a] = 1;
        }

        return dp[a][b];
    }
}
