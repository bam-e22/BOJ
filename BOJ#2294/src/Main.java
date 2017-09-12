import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int k;
    static int[] coin = new int[101];
    static int[] dp = new int[10001];

    static final int INF = 100000000;

    static {

        Arrays.fill(dp, -1);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) coin[i] = Integer.parseInt(br.readLine());

        int ret = solve(0);
        System.out.println(ret == INF ? -1 : ret);
    }

    static int solve(int coinSum) {

        if (coinSum == k) return 0;
        if (coinSum > k) return INF;

        if (dp[coinSum] != -1) return dp[coinSum];

        dp[coinSum] = INF;

        for (int i = 0; i < n; i++) {

            dp[coinSum] = Math.min(dp[coinSum], solve(coinSum + coin[i]) + 1);
        }

        return dp[coinSum];
    }
}
