import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#10942 팰린드롬?
 * https://www.acmicpc.net/problem/10942
 */

// 22328 KB	2984 MS

public class Main2 {

    static final int YES = 1;
    static final int NO = 0;

    static int[] A = new int[2001];
    static int[][] dp = new int[2001][2001];

    public static void main(String[] args) throws IOException {

        int N; // 수열의 크기
        int M; // 질문의 개수

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {

            A[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());

        // solve
        for (int i = 0; i < M; i++) {

            int S, E;

            st = new StringTokenizer(br.readLine());

            S = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            System.out.println(solve(S, E));
        }
    }

    static int solve(int S, int E) {

        // memoization
        if (dp[S][E] > 0) {

            return dp[S][E];
        }

        // solve
        if (S == E) {

            return YES;
        }

        int ret = NO;
        if (S + 1 == E) {

            if (A[S] == A[E]) {

                return dp[S][E] = YES;
            }
        } else {

            if (A[S] == A[E]) {

                ret = solve(S + 1, E - 1);
            }
        }

        return dp[S][E] = ret;
    }
}