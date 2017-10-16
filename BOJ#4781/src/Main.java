import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // dp[m] : 돈을 m 만큼 갖고 있을 때, 얻을 수 있는 최대 칼로리
    static int[] dp = new int[10001];

    static int n, m;
    static int[] calorie = new int[5001];
    static int[] candyCost = new int[5001];
    static int minCandyCost = Integer.MAX_VALUE;

    static {

        Arrays.fill(dp, -1);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);

            if (n == 0 && m == 0) return;

            Arrays.fill(dp, -1);
            minCandyCost = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {

                st = new StringTokenizer(br.readLine());

                calorie[i] = Integer.parseInt(st.nextToken());
                candyCost[i] = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);

                minCandyCost = minCandyCost > candyCost[i] ? candyCost[i] : minCandyCost;
            }

            System.out.println(solve(m));
        }

    } // ~main

    // return dp[money]
    static int solve(int money) {

        // 아무것도 살 수 없을 때
        if (money < minCandyCost) return 0;

        // memoization
        if (dp[money] != -1) return dp[money];

        // 탐색
        dp[money] = 0;
        for (int i = 0; i < n; i++) {

            if (money - candyCost[i] >= 0) {

                dp[money] = Math.max(dp[money], solve(money - candyCost[i]) + calorie[i]);
            }
        }

        return dp[money];
    }
}

