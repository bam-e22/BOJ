import java.io.*;
import java.util.StringTokenizer;

/**
 * BOJ#10942 팰린드롬?
 * https://www.acmicpc.net/problem/10942
 */

// Manacher's algorithm
public class Main {

    static int[] S = new int[4000];
    static int[] A = new int[4000];

    static final int YES = 1;
    static final int NO = 0;

    public static void main(String[] args) throws IOException {

        int N; // 수열의 크기
        int M; // 질문의 개수

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        N = 2 * N - 1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i += 2) {

            S[i] = Integer.parseInt(st.nextToken());
        }

        manacher(N);

        M = Integer.parseInt(br.readLine());

        int left, right, center, radius;

        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());

            left = (Integer.parseInt(st.nextToken()) - 1) * 2;
            right = (Integer.parseInt(st.nextToken()) - 1) * 2;
            center = (left + right) / 2;

            radius = (right - left) / 2;

            bw.write(A[center] >= radius ? YES + "\n" : NO + "\n");
        }

        bw.flush();

        bw.close();
        br.close();
    }

    static void manacher(int N) {

        // R = max(i+A[i]), p는 R이 최대가 되는 i. R = p + A[p]
        int R = -1, p = -1;

        for (int i = 0; i < N; i++) {

            // 초기값 설정
            if (R < i) {

                A[i] = 0;
            } else {

                A[i] = Math.min(R - i, A[2 * p - 1]);
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
}
