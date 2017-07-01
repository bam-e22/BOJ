import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#7676 Saruman's Army
 * https://www.acmicpc.net/problem/7676
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int R = 0; // The maximum effective range of all palantirs (where 0 <= R <= 1000)
        int N = 0; // The number of troops in saruman's army (where 1 <= N <= 1000)
        int[] X; // The positions of x1, . . ., xn of each troop (where 0 ≤ xi ≤ 1000)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // main loop
        while (true) {

            // input
            StringTokenizer st = new StringTokenizer(br.readLine());

            R = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            // exit
            if (R == -1 & N == -1) {

                break;
            }

            X = new int[N];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {

                X[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(X);

            solve(R, N, X);

        }

    }

    static void solve(int R, int N, int[] X) {

        int i = 0;
        int ans = 0;

        while (i < N) {

            // s : 커버되지 않은 가장 왼쪽 점의 위치
            int s = X[i];

            // s(가장 왼쪽 점)으로부터 거리 R을 초과하는 점까지 진행
            while (i < N && X[i] <= s + R) {

                i++;
            }

            // p : palantir 위치
            int p = X[i - 1];
            // p(palantir 위치)으로부터 거리 R을 초과하는 점까지 진행 (palantir 영향력 밖에 있는 가장 왼쪽점을 구한다.)
            while (i < N && X[i] <= p + R) {

                i++;
            }

            ans++;
        }

        System.out.println(ans);
    }
}