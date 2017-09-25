import java.io.*;

public class Main {

    static long[] dp = new long[68];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        dp[2] = 2;
        dp[3] = 4;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            int n = Integer.parseInt(br.readLine());

            System.out.println(solve(n));
        } // ~Test Case loop
    }

    static long solve(int n) {

        if (n < 2) return 1;

        if (dp[n] != 0) return dp[n];

        return dp[n] = solve(n - 1) + solve(n - 2) + solve(n - 3) + solve(n - 4);
    }
}
