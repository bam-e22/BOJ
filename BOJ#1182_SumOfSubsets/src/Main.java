import java.io.*;
import java.util.StringTokenizer;

/**
 * BOJ#1182 부분집합의 합
 * https://www.acmicpc.net/problem/1182
 */

public class Main {

    static int N;
    static int S;
    static int cnt = 0;
    static int[] numArr = new int[21];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {

            numArr[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, 0);

        // S가 0인 경우 공집합도 포함되므로 개수에서 -1을 빼준다.
        if (S == 0) {
            cnt -= 1;
        }

        System.out.println(cnt);
    }

    static void solve(int sum, int step) {

        if (step == N) {

            if (sum == S) cnt++;
            return;
        }

        solve(sum + numArr[step], step + 1);
        solve(sum, step + 1);
    }
}
