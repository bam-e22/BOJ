import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ#1697 숨바꼭질
 * https://www.acmicpc.net/problem/1697
 */

public class Main {

    static final int MAX_POSITION = 100_000;
    static final int MIN_POSITION = 0;

    public static void main(String[] args) throws IOException {

        int N; // 수빈이의 위치
        int K; // 동생의 위치
        boolean[] visited = new boolean[100001];

        Arrays.fill(visited, false);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs(N, K, visited);
    }

    static void bfs(int N, int K, boolean[] visited) {

        int count = -1;
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(N);

        while (!queue.isEmpty()) {

            count++;

            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {

                N = queue.poll();

                if (N == K) {

                    System.out.println(count);
                    return;
                }

                if (N - 1 >= MIN_POSITION && !visited[N - 1]) {

                    queue.add(N - 1);
                    visited[N - 1] = true;
                }

                if (N + 1 <= MAX_POSITION && !visited[N + 1]) {

                    queue.add(N + 1);
                    visited[N + 1] = true;
                }

                if (2 * N <= MAX_POSITION && !visited[2 * N]) {

                    queue.add(2 * N);
                    visited[2 * N] = true;
                }
            }
        }
    }
}
