import java.io.*;
import java.util.StringTokenizer;

public class Main {

    // dp[n][k][m] : 길이가 n이고 인접한 비트 수가 k이고 마지막 비트가 m인 경우의 수
    // dp[n][k][0] = dp[n-1][k][0] + dp[n-1][k][1]
    // dp[n][k][1] = dp[n-1][k][0] + dp[n-1][k-1][1]
    static int[][][] dp = new int[101][100][2];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        dp[1][0][0] = 1;
        dp[1][0][1] = 1;

        for (int n = 2; n <= 100; n++) {
            for (int k = 0; k < n; k++) {

                dp[n][k][0] = dp[n - 1][k][0] + dp[n - 1][k][1];
                dp[n][k][1] = dp[n - 1][k][0] + ((k > 0) ? dp[n - 1][k - 1][1] : 0);
            }
        }

        while (T-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            bw.write(dp[N][K][0] + dp[N][K][1] + "\n");

        } // ~test case loop

        bw.flush();
        bw.close();
        br.close();
    }
}
