import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#2805 EKO 나무 자르기
 * https://www.acmicpc.net/problem/2805
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int N; // 나무의 수
        int M; // 가져가고 싶은 나무의 길이
        int[] tree;
        int maxHeight = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        tree = new int[N];
        for (int i = 0; i < N; i++) {

            tree[i] = Integer.parseInt(st.nextToken());
            maxHeight = maxHeight < tree[i] ? tree[i] : maxHeight;
        }

        // solve - Binary Search
        int left = 0;
        int right = maxHeight;
        int m = (left + right) / 2;
        maxHeight = 0;

        while (right - left > 1) {

            long amount = 0L;

            for (int i = 0; i < N; i++) {

                amount += tree[i] - m > 0 ? tree[i] - m : 0;
            }

            if (amount >= M) {

                maxHeight = maxHeight < m ? m : maxHeight;

                left = m;
            } else {

                right = m;
            }

            m = (left + right) / 2;
        }

        System.out.println(maxHeight);
    }
}
