import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, S, M;
    static int[] V = new int[101];
    static boolean[][] dp = new boolean[101][1001];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 곡의 개수
        S = Integer.parseInt(st.nextToken()); // 시작 볼륨
        M = Integer.parseInt(st.nextToken()); // 최대 볼륨

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {

            V[i] = Integer.parseInt(st.nextToken());
        }

        // Solve
        // dp[idx][volume] : N번째 곡일 때, Volume 가능 여부
        dp[0][S] = true;

        for (int idx = 1; idx <= N; idx++) {

            for (int volume = 0; volume <= M; volume++) {

                if (!dp[idx - 1][volume]) continue;

                if (volume - V[idx] >= 0) dp[idx][volume - V[idx]] = true;

                if (volume + V[idx] <= M) dp[idx][volume + V[idx]] = true;
            }
        }

        for (int volume = M; volume >= 0; volume--) {

            if (dp[N][volume]) {

                System.out.println(volume);
                return;
            }
        }

        System.out.println(-1);
    }
}