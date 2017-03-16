import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#2169 로봇 조종하기
 * https://www.acmicpc.net/problem/2169
 */

public class Main {

    static int[][] mapValue = new int[1003][1003];
    static int[][] dp = new int[1003][1003];    // dp[i][j] : (1, 1)에서 (i, j)까지 도달할 때 최대 비용
    static int[][][] tempDP = new int[2][1003][1003];

    public static void main(String[] args) throws IOException {

        int N, M; // 지형의 크기

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {

                mapValue[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // Solve
        dp[1][1] = mapValue[1][1];

        // i = 1
        // : 1행의 경우 위쪽에서 접근할 수 없고, 오른쪽에서도 접근할 수 없다.
        for (int j = 2; j <= M; j++) {

            dp[1][j] += dp[1][j - 1] + mapValue[1][j];
        }

        // i = 2 ~ N
        // : 각 행에서 1열부터 M열까지 진행한다.
        for (int i = 2; i <= N; i++) {

            // 위쪽과 왼쪽에서 오는 경우
            tempDP[0][i][1] = dp[i - 1][1] + mapValue[i][1];
            for (int j = 2; j <= M; j++) {

                tempDP[0][i][j] = Math.max(dp[i - 1][j], tempDP[0][i][j - 1]) + mapValue[i][j];
            }

            // 위쪽과 오른쪽에서 오는 경우
            tempDP[1][i][M] = dp[i - 1][M] + mapValue[i][M];
            for (int j = M - 1; j >= 1; j--) {

                tempDP[1][i][j] = Math.max(dp[i - 1][j], tempDP[1][i][j + 1]) + mapValue[i][j];
            }

            // 둘 중 max 값을 취한다
            for (int j = 1; j <= M; j++) {

                dp[i][j] = Math.max(tempDP[0][i][j], tempDP[1][i][j]);
            }
        }

        System.out.println(dp[N][M]);

    } // main
}