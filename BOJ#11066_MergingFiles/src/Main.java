import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#11066 Merging Files
 * https://www.acmicpc.net/problem/11066
 */

public class Main {

    static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        int T; // TC
        int K; // 소설을 구성하는 장의 수
        int[] C; // 각 장의 파일 크기
        int[] S;
        int[][] dp; // i번째 수 부터 j번째 수 까지 수를 합치는 데 필요한 최소 비용

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            K = Integer.parseInt(br.readLine());
            C = new int[K + 1];
            S = new int[K + 1];
            dp = new int[K + 1][K + 1];

            S[0] = 0;
            // input & initialize
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i < K + 1; i++) {

                C[i] = Integer.parseInt(st.nextToken());
                S[i] = S[i - 1] + C[i];

                for (int j = 1; j < K + 1; j++) {

                    // 최소값을 구해야하므로 초기값으로 MAX값을 설정한다
                    dp[i][j] = MAX;
                }
            }

            System.out.println(solve(dp, C, S, 1, K));
        }
    }

    static int solve(int[][] dp, int[] C, int[] S, int start, int end) {

        // 자기 자신인 경우 합치는 비용은 0
        if (start >= end) {

            return 0;
        }

        // 이웃한 경우
        if (end == start + 1) {

            return C[start] + C[end];
        }

        if (dp[start][end] < MAX) {

            return dp[start][end];
        }

        for (int i = start; i < end; i++) {

            int temp = solve(dp, C, S, start, i) + solve(dp, C, S, i + 1, end) + S[end] - S[start - 1];

            dp[start][end] = Math.min(dp[start][end], temp);
        }

        return dp[start][end];
    }
}
