import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_VALUE = 100000000;
    static int N;
    static int[] A = new int[1001];
    static int[] dp = new int[1001];

    static {

        Arrays.fill(dp, MAX_VALUE);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {

            A[i] = Integer.parseInt(st.nextToken());
        }

        if (N == 1) {

            System.out.println(0);
        } else {

            for (int i = 1; i <= A[0]; i++) {

                dp[0] = Math.min(dp[0], solve(i) + 1);
            }

            System.out.println(dp[0] == MAX_VALUE ? -1 : dp[0]);
        }
    }

    // return : dp[idx], idx 위치일 때 오른쪽 끝으로 가기 위한 최소 점프 수
    static int solve(int idx) {

        if (idx >= N - 1) return 0;

        if (dp[idx] != MAX_VALUE) return dp[idx];

        for (int i = 1; i <= A[idx]; i++) {

            dp[idx] = Math.min(dp[idx], solve(idx + i) + 1);
        }
        return dp[idx];
    }
}
