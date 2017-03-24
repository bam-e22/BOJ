import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ#12851 숨바꼭질2
 * https://www.acmicpc.net/problem/12851
 */

public class Main {

    static final int MIN_VALUE = 0;
    static final int MAX_VALUE = 100000;
    static boolean[] visited = new boolean[100001];
    static int ans = 1000000000;

    public static void main(String[] args) throws IOException {

        int N; // 수빈이 위치 0 <= N <= 100,000
        int K; // 동생의 위치 0 <= K <= 100,000
        HashMap<Integer, Integer> map = new HashMap<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs(N, K, map);
        System.out.println(ans);
        System.out.println(map.get(ans));
    }

    static void bfs(int N, int K, HashMap<Integer, Integer> map) {

        int time = -1;
        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();

        q.add(N);

        while (!q.isEmpty()) {

            time++;

            int size = q.size();
            for (int i = 0; i < size; i++) {

                int u = q.poll();
                visited[u] = true;

                if (u == K) {

                    if (ans >= time) {
                        ans = time;

                        if (map.get(ans) == null) {

                            map.put(ans, 1);
                        } else {

                            map.put(ans, map.get(ans) + 1);
                        }
                    }
                }

                if (u > MIN_VALUE && !visited[u - 1]) {

                    q.add(u - 1);
                }

                if (u < MAX_VALUE && !visited[u + 1]) {

                    q.add(u + 1);
                }

                if (u * 2 <= MAX_VALUE && !visited[u * 2]) {

                    q.add(u * 2);
                }
            }
        }
    }
}
