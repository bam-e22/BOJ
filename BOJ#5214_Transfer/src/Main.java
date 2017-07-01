import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ#5214 환승
 * https://www.acmicpc.net/problem/5214
 */

public class Main {

    static final int INF = 10000000;

    static int[] dist = new int[101005];
    static boolean[] discovered = new boolean[101005];

    static {

        Arrays.fill(dist, INF);
    }

    public static void main(String[] args) throws IOException {

        int N; // 역의 개수 <= 100,000
        int K; // 하이퍼튜브가 서로 연결하는 역의 개수 <= 1,000
        int M; // 하이퍼튜브의 개수
        ArrayList<ArrayList<Integer>> edge = new ArrayList<ArrayList<Integer>>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N + M; i++) {

            edge.add(new ArrayList<Integer>());
        }

        for (int i = 1; i <= M; i++) {

            st = new StringTokenizer(br.readLine());

            int dummy = N + i;

            for (int j = 0; j < K; j++) {

                int node = Integer.parseInt(st.nextToken());

                edge.get(dummy).add(node);
                edge.get(node).add(dummy);
            }
        }

        Queue<Integer> queue = new LinkedList<Integer>();

        queue.add(1);
        discovered[1] = true;
        dist[1] = 1;

        while (!queue.isEmpty()) {

            int u = queue.poll();

            if (u == N) {

                break;
            }

            for (int x : edge.get(u)) {

                if (!discovered[x] && dist[x] > dist[u] + 1) {

                    queue.add(x);
                    discovered[x] = true;
                    dist[x] = dist[u] + 1;
                }
            }
        }

        System.out.println(dist[N] >= INF ? -1 : (dist[N] + 1) / 2);
    }
}
