import java.io.*;
import java.util.StringTokenizer;

/**
 * BOJ#11046 팰린드롬??
 * https://www.acmicpc.net/problem/11046
 */

public class Main {

    static final int YES = 1;
    static final int NO = 0;

    static int[] S = new int[2000000];
    static int[] A = new int[2000000];

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

        // solve
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

        int R = -1;
        int p = -1;

        for (int i = 0; i < N; i++) {

            // 1. A[i]의 초기값 설정

            // R범위에 i가 포함되지 않는 경우
            if (R < i) {

                A[i] = 0;
            }
            // R범위에 i가 포함되는 경우, 팰린드롬의 정의에 따라 불필요한 검사를 하지 않는다
            else {

                A[i] = Math.min(R - i, A[2 * p - i]);
            }

            // 2. i를 기준으로 양옆으로 범위를 넓혀가면서 검사
            while (((i - A[i] - 1) >= 0 && (i + A[i] + 1) < N)
                    && (S[i - A[i] - 1] == S[i + A[i] + 1])) {

                A[i]++;
            }

            // 3. R과 p를 갱신
            if (i + A[i] > R) {

                R = i + A[i];
                p = i;
            }
        }
    } // ~ Manacher
}