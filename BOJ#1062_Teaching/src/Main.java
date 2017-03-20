import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#1062 가르침
 * https://www.acmicpc.net/problem/1062
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int N; // 단어의 수
        int K; // 가르칠 글자의 수
        int[] word;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        word = new int[N];

        // 입력받는 String -> char[] -> 중복없이 int로 저장
        for (int i = 0; i < N; i++) {

            char[] tempStr = br.readLine().toCharArray();

            for (char x : tempStr) {

                word[i] |= (1 << x - 'a');
            }
        }

        System.out.println(solve(0, K, 0, word));
    }

    static int solve(int alphabetIdx, int K, int mask, int[] word) {

        // 재귀 종료 조건
        if (K < 0) {

            return 0;
        }

        // 재귀 종료 조건 : 알파벳 범위를 넘은 경우(끝까지 진행한 경우 검사)
        if (alphabetIdx == 26) {

            int cnt = 0;
            for (int i = 0; i < word.length; i++) {

                if (word[i] == (word[i] & mask)) {

                    cnt++;
                }
            }

            return cnt;
        }

        int ans = 0;
        int case1, case2;

        // 1. 해당 알파벳을 배우는 경우
        case1 = solve(alphabetIdx + 1, K - 1, mask | (1 << alphabetIdx), word);
        case2 = 0;

        // 2. 해당 알파벳을 배우지 않는 경우
        switch (alphabetIdx) {

            case 'a' - 'a':
            case 'n' - 'a':
            case 't' - 'a':
            case 'i' - 'a':
            case 'c' - 'a':
                break;

            default:

                case2 = solve(alphabetIdx + 1, K, mask, word);
                break;
        }

        ans = Math.max(case1, case2);

        return ans;

    }
}

