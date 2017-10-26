import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        int N = input.length() * 2 - 1;

        char[] S = new char[N];

        int idx = 0;
        for (int i = 0; i < N; i += 2) {

            S[i] = input.charAt(idx++);
        }

        int[] A = new int[N];

        // Solve

        int[] dp = new int[N];

        dp[0] = 1;

        manacher(S, A, N);

        for (int i = 2; i < N; i += 2) {

            dp[i] = N;
            for (int j = 0; j <= i; j += 2) {

                int center = (i + j) / 2;
                int radius = (i - j) / 2;

                if (A[center] >= radius) {

                    dp[i] = Math.min(dp[i], j - 1 >= 0 ? dp[j - 2] + 1 : 1);
                }
            }
        }

        System.out.println(dp[N - 1]);
    }

    static void manacher(char[] S, int[] A, int N) {

        int R = -1;
        int p = -1;

        for (int i = 0; i < N; i++) {

            // A[i]의 초기값 설정
            if (R < i) A[i] = 0;
            else A[i] = Math.min(R - i, A[2 * p - i]);

            // i를 기준으로 양 옆으로 범위를 넓혀가면서 검사
            while ((i - A[i] - 1 >= 0) && (i + A[i] + 1 < N)
                    && (S[i - A[i] - 1] == S[i + A[i] + 1])) {

                A[i]++;
            }

            // R과 p를 갱신
            if (i + A[i] > R) {

                R = i + A[i];
                p = i;
            }
        }
    }
}
