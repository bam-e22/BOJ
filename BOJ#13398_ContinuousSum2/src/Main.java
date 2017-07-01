import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#13398 연속합2
 * https://www.acmicpc.net/problem/13398
 */

public class Main {

    static final int MIN = -100000000;
    static int n;

    static int[] arr = new int[100010];
    static int[][] dp = new int[2][100010];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 2; i <= n + 1; i++) {

            arr[i] = Integer.parseInt(st.nextToken());
        }

        int maxValue = MIN;
        for (int i = 2; i <= n + 1; i++) {

            dp[0][i] = Math.max(dp[0][i - 1] + arr[i], arr[i]);
            dp[1][i] = Math.max(dp[0][i - 2] + arr[i], dp[1][i - 1] + arr[i]);

            maxValue = Math.max(dp[0][i], maxValue);
            maxValue = Math.max(dp[1][i], maxValue);
        }

        /**
         * dp[0][i] : 0번 건너뛴 i까지의 최대 연속합 = (i-1까지의 최대 연속합 + A[i])값과 A[i]값 중 큰 값
         * dp[1][i] : 1번 건너뛴 i까지의 최대 연속합 = (i-1을 건너뛴 i까지의 최대 연속합)값과 (i-1을 안건너뛴 i까지의 최대 연속합)값 중 큰 값
         */

        System.out.println(maxValue);
    }
}
