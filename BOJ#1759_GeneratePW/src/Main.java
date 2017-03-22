import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#1759 암호 만들기
 * https://www.acmicpc.net/problem/1759
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int L; // 알파벳 소문자 개수
        int C; // 후보 개수
        char[] candidate; // 후보

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        candidate = new char[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {

            candidate[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(candidate);

        permutation(0, L, C, candidate);
    }

    static void permutation(int depth, int k, int n, char[] candidate) {

        if (depth == k) {

            if (checkValid(candidate, k)) {

                print(candidate, k);
            }
        }

        boolean exit = false;
        for (int i = depth; i < n; i++) {

            for (int j = 0; j < depth - 1; j++) {

                if (candidate[j] - candidate[j + 1] > 0) {

                    exit = true;
                    break;
                }
            }

            if (exit) {

                break;
            }

            swap(depth, i, candidate);
            permutation(depth + 1, k, n, candidate);
            swap(depth, i, candidate);
        }
    }

    static void swap(int i, int j, char[] candidate) {

        char temp = candidate[i];

        candidate[i] = candidate[j];
        candidate[j] = temp;
    }

    static void print(char[] candidate, int k) {

        for (int i = 0; i < k; i++) {

            System.out.print(candidate[i]);
        }
        System.out.println();
    }


    static boolean checkValid(char[] candidate, int k) {

        int vowel = 0;

        for (int i = 0; i < k; i++) {

            // 자음 모음 개수 체크
            if (candidate[i] == 'a' || candidate[i] == 'e' || candidate[i] == 'i' || candidate[i] == 'o' || candidate[i] == 'u') {

                vowel++;
            }

            // 오름차순 확인
            if (i < k - 1) {

                if (candidate[i] - candidate[i + 1] > 0) {

                    return false;
                }
            }
        }

        // 자음, 모음 조건 확인
        if (vowel < 1 || k - vowel < 2) {

            return false;
        }

        return true;
    }

}
