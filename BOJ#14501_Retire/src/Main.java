import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#14501 퇴사
 * https://www.acmicpc.net/problem/14501
 */

public class Main {

    static final int INF = 10000000;

    static int[] T = new int[16];
    static int[] P = new int[16];
    static int[] dp = new int[16];
    static int N;

    static {

        Arrays.fill(dp, -1);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 1; i < N + 1; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve(1));
        System.out.println(Arrays.toString(dp));
    }

    // return dp[idx] : 날짜가 idx일때 이후 더 받을 수 있는 금액의 최대값
    static int solve(int idx) {

        // 기저 사례 : 날짜가 N+1일 때
        if (idx == N + 1) return 0;

        // 기저 사례 : 날짜가 N+1을 넘을 경우
        if (idx > N + 1) return -INF;

        // memoization
        if (dp[idx] != -1) return dp[idx];

        return dp[idx] = Math.max(solve(idx + 1), solve(idx + T[idx]) + P[idx]);
    }
}
