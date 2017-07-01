import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#1965 상자넣기
 * https://www.acmicpc.net/problem/1965
 */

public class Main2 {

    public static void main(String[] args) throws IOException {

        int n; // 상자의 개수
        int[] list; // 상자의 크기 List

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        n = Integer.parseInt(br.readLine());
        list = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {

            list[i] = Integer.parseInt(st.nextToken());
        }

        // solve : O(N^2)
        System.out.println(getLISLength(list, n));

    }

    static int getLISLength(int[] list, int n) {

        int[] dp = new int[n];
        int length = 1;

        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {

            for (int j = 0; j < i; j++) {

                if (list[j] < list[i]) {

                    if (dp[j] >= dp[i]) {

                        dp[i] = dp[j] + 1;
                        length = length < dp[i] ? dp[i] : length;
                    }
                }
            }
        }

        return length;
    }
}