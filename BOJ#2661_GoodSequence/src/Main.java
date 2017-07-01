import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ#2661 좋은 수열
 * https://www.acmicpc.net/problem/2661
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int N; // 수열의 길이
        String sequence = "1";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        backtracking(N, 1, sequence);
    }

    static int backtracking(int N, int step, String sequence) {

        // 나쁜 수열인지 검사
        if (checkBadSequence(sequence)) {

            return -1;
        }

        // 길이가 N에 도달하였는지 체크
        if (step == N) {

            System.out.println(sequence);
            return 0;
        }

        for (int i = 1; i <= 3; i++) {

            sequence = sequence.concat(String.valueOf(i));
            if (backtracking(N, step + 1, sequence) == 0) {

                return 0;
            }
            sequence = sequence.substring(0, sequence.length() - 1);
        }

        return -1;
    }

    static boolean checkBadSequence(String sequence) {

        for (int i = 1; i <= sequence.length() / 2; i++) {

            for (int j = 0; j + 2 * i <= sequence.length(); j++) {

                if (sequence.substring(j, j + i).equals(sequence.substring(j + i, j + 2 * i))) {

                    return true;
                }
            }
        }

        return false;
    }
}
