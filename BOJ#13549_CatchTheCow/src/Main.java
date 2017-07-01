import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ#13549 숨바꼭질3
 * https://www.acmicpc.net/problem/13549
 */

public class Main {

    static final int MIN_POSITION = 0;
    static final int MAX_POSITION = 100000;

    static boolean[] visited = new boolean[100001];

    public static void main(String[] args) throws IOException {

        int N; // 수빈이 위치 0 <= N <= 100,000
        int K; // 동생 위치 0 <= K <= 100,000

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(solve(N, K));
    }

    static int solve(int N, int K) {

        Queue<Node> queue = new LinkedList<Node>();
        int minTime = 10000000;

        queue.add(new Node(N, 0));

        while (!queue.isEmpty()) {

            Node u = queue.poll();
            visited[u.node] = true;

            if (u.node == K) {

                minTime = minTime > u.time ? u.time : minTime;
            }

            if (u.node > MIN_POSITION && !visited[u.node - 1]) {

                queue.add(new Node(u.node - 1, u.time + 1));
            }

            if (u.node < MAX_POSITION && !visited[u.node + 1]) {

                queue.add(new Node(u.node + 1, u.time + 1));
            }

            if (u.node * 2 <= MAX_POSITION && !visited[u.node * 2]) {

                queue.add(new Node(u.node * 2, u.time));
            }
        }

        return minTime;
    }
}

class Node {

    int node;
    int time;

    Node(int node, int time) {

        this.node = node;
        this.time = time;
    }
}
