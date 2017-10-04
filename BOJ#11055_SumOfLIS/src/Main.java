import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#11055 가장 큰 증가 부분 수열
 * https://www.acmicpc.net/problem/11055
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int N;
        int[] A;
        int[] dp;

        int max = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = new int[N];
        dp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {

            A[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {

            dp[i] = A[i];
        }

        // O(n^2)
        for (int i = 1; i < N; i++) {

            int temp = 0;
            for (int j = 0; j < i; j++) {

                if (A[j] < A[i]) {

                    temp = temp < dp[j] ? dp[j] : temp;
                }
            }

            dp[i] += temp;
            max = max < dp[i] ? dp[i] : max;
        }

        System.out.println(N == 1 ? A[0] : max);
    }

}
