import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * BOJ#1334 다음 팰린드롬 수
 * https://www.acmicpc.net/problem/1334
 */

public class Main {

    static int[] S = new int[100];
    static int[] A = new int[100];

    static {

        Arrays.fill(S, -1);
    }

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        int N = input.length() * 2 - 1;

        int idx = 0;
        for (int i = 0; i < N; i += 2) {

            S[i] = input.charAt(idx++) - '0';
        }

        // solve
        manacher(N);

        int left = 0;
        int right = N - 1;
        int center = (left + right) / 2;
        int radius = (right - left) / 2;

        // 팰린드롬이면 1을 더해준다
        if (A[center] >= radius) {

            input = String.valueOf(Integer.parseInt(input) + 1);
        }

        N = input.length() * 2 - 1;

        idx = 0;
        for (int i = 0; i < N; i += 2) {

            S[i] = input.charAt(idx++) - '0';
        }

        // solve
        manacher(N);

        left = 0;
        right = N - 1;
        center = (left + right) / 2;
        radius = (right - left) / 2;


        // 팰린드롬이면 출력
        if (A[center] >= radius) {

            System.out.println(input);
        } else {
            // 팰린드롬 아님
            // ACB 형태에서

            int cp = compareAB(center, N);

            String A = getString(left, center - 1);
            String A_reverse = reverseString(A);

            // A' > B인 경우 -> ACA'
            if (cp == -1) {

                System.out.println(A + (S[center] == -1 ? "" : S[center]) + A_reverse);
            }
            // A' < B인 경우 -> (A+1)(C+1)(A+1)'
            else if (cp == 1) {

                if (S[center] + 1 > 9) {

                    S[center] = 0;
                    A = updateString(A);
                    A_reverse = reverseString(A);

                    System.out.println(A + S[center] + A_reverse);
                } else {

                    System.out.println(A + (S[center] == -1 ? "" : S[center] + 1) + A_reverse);
                }
            }
        }
    }

    static void manacher(int N) {

        int R = -1;
        int p = -1;

        for (int i = 0; i < N; i++) {

            if (R < i) {

                A[i] = 0;
            } else {

                A[i] = Math.min(R - i, A[2 * p - i]);
            }

            while ((i - A[i] - 1 >= 0 && i + A[i] + 1 < N)
                    && (S[i - A[i] - 1] == S[i + A[i] + 1])) {

                A[i]++;
            }

            if (i + A[i] > R) {

                R = i + A[i];
                p = i;
            }
        }
    }

    static String getString(int left, int right) {

        String num = "";

        for (int i = left; i <= right; i++) {

            if (S[i] == -1) continue;
            num += S[i];
        }

        return num;
    }

    static String reverseString(String s) {

        return (new StringBuilder(s)).reverse().toString();
    }

    static String updateString(String s) {

        int length = s.length();

        StringBuilder sb = new StringBuilder(s);

        for (int i = length - 1; i >= 0; i--) {

            int x = sb.charAt(i) - '0';

            if (x + 1 > 9) {

                sb.replace(i, i + 1, "0");

                if (i == 0) {

                    sb.insert(0, "1");
                }
            } else {

                sb.replace(i, i + 1, String.valueOf(x + 1));
                break;
            }
        }

        return new String(sb);
    }

    static int compareAB(int center, int N) {

        int left = center - 1;
        int right = center + 1;

        while (left >= 0 && right < N) {

            if (S[left] > S[right]) return -1;
            else if (S[left] < S[right]) return 1;

            left--;
            right--;
        }

        return 0;
    }
}
