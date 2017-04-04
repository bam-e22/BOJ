import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#12969 ABC
 * https://www.acmicpc.net/problem/12969
 */

/**
 * dp[x][a][b][k] : x번째까 A의 개수가 a, B의 개수가 b일 때,
 * S[i] < S[j]를 만족하는 쌍의 개수가 k인 문자열이 존재하는지 검사 여부
 * 1) x번째 원소가 A인 경우 : dp[x+1][a+1][b][p];
 * 2) x번째 원소가 B인 경우 : dp[x+1][a][b+1][p+a];
 * 3) x번째 원소가 C인 경우 : dp[x+1][a][b][p+a+b];
 */

public class Main {

    static final int NOT_EXIST = -1;
    static boolean[][][][] dp = new boolean[31][31][31][436];
    static char[] S;
    static int N, K;

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        S = new char[N];

        // solve
        if (solve(0, 0, 0, 0)) {

            System.out.println(new String(S));
        } else {

            System.out.println(NOT_EXIST);
        }
    }

    /**
     * 정수 N, K가 주어졌을 때, 다음 조건을 만족하는 문자열 S가 존재하는지 여부를 판단하는 함수
     * 조건#1 : 문자열 S의 길이는 N이고, 'A','B','C'로 이루어져 있다.
     * 조건#2 : 문자열 S에는 0 <= i < j < N이면서, S[i] < S[j]를 만족하는 쌍이 K개가 있다.
     */
    static boolean solve(int x, int a, int b, int p) {

        // 종료 조건
        if (x == N) {

            if (p == K) return true;
            else return false;
        }

        // 방문했었지만, 다시 방문했다면 해당 문자열이 없는 것이다. return false;
        if (dp[x][a][b][p]) return false;
        // 방문 여부 체크
        dp[x][a][b][p] = true;

        // 1. x번째 원소가 A인 경우
        S[x] = 'A';
        if (solve(x + 1, a + 1, b, p)) return true;

        // 2. x번째 원소가 B인 경우
        S[x] = 'B';
        if (solve(x + 1, a, b + 1, p + a)) return true;

        // 3. x번째 원소가 C인 경우
        S[x] = 'C';
        if (solve(x + 1, a, b, p + a + b)) return true;

        return false;
    }
}
