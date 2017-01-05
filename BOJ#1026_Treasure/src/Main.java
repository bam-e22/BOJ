import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#1026 보물
 * https://www.acmicpc.net/problem/1026
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int N;
        int[] A;
        int[] B;
        int S = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        N = Integer.parseInt(br.readLine());

        A = new int[N];
        B = new int[N];

        // A 배열 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {

            A[i] = Integer.parseInt(st.nextToken());
        }

        // B 배열 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {

            B[i] = Integer.parseInt(st.nextToken());
        }

        // A 배열 정렬
        Arrays.sort(A);

        int targetIdx = 0;
        for (int i = 0; i < N; i++) {

            int tempMaxOfB = 0;
            for (int j = 0; j < N; j++) {

                if (B[j] > tempMaxOfB) {

                    tempMaxOfB = B[j];
                    targetIdx = j;
                }
            }

            S += A[i] * B[targetIdx];
            B[targetIdx] = 0;
        }

        System.out.println(S);
    }

}
