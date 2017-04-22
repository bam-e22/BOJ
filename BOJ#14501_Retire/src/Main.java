import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#14501 퇴사
 * https://www.acmicpc.net/problem/14501
 */

public class Main {

    static int[] T = new int[16];
    static int[] P = new int[16];
    static int N;
    static int maxValue = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 1; i < N + 1; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        solve(1, 0);

        System.out.println(maxValue);
    }

    static void solve(int idx, int sum) {

        if (idx == N + 1) {

            maxValue = maxValue < sum ? sum : maxValue;
            return;
        }

        if (idx > N + 1) {

            return;
        }

        solve(idx + 1, sum);
        solve(idx + T[idx], sum + P[idx]);
    }
}
