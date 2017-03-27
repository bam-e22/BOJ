import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#13458 시험 감독
 * https://www.acmicpc.net/problem/13458
 */

public class Main {

    static int[] dp = new int[1000001];

    public static void main(String[] args) throws IOException {

        int N; // 시험장의 개수 <= 1,000,000
        int[] A; // 각 시험장의 응시자 수 <= 1,000,000
        int B; // 총 감독관이 한 방에서 감시할 수 있는 응시자의 수 <= 1,000,000
        int C; // 부 감독관이 한 방에서 감시할 수 있는 응시자의 수
        long ans = 0;

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {

            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // solve
        int a;
        int cnt;
        for (int i = 0; i < N; i++) {

            a = A[i];
            cnt = 0;

            if (dp[a] > 0) {

                ans += dp[a];
                continue;
            }

            a -= B;

            if (a > 0) {

                if (a % C == 0) {

                    cnt += (a / C);
                } else {

                    cnt += (a / C) + 1;
                }
            }
            cnt += 1;

            dp[A[i]] = cnt;
            ans += cnt;
        }


        System.out.println(ans);
    }
}
