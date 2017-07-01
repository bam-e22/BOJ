import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ#13913 숨바꼭질4
 * https://www.acmicpc.net/problem/13913
 */

public class Main {

    static final int MIN_POSITION = 0;
    static final int MAX_POSITION = 100000;
    static final int INF = 10000000;

    static int[] dist = new int[100001];
    static boolean[] discovered = new boolean[100001];
    static int[] prevNode = new int[300001];

    static {

        Arrays.fill(dist, INF);
    }

    public static void main(String[] args) throws IOException {

        int N;
        int K;

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // solve - dijkstra
        Queue<Node> q = new LinkedList<Node>();

        q.add(new Node(N, 0));

        dist[N] = 0;
        discovered[N] = true;
        prevNode[N] = -1;

        while (!q.isEmpty()) {

            Node u = q.poll();

            // 종료 조건
            if (u.node == K) {

                System.out.println(u.step);
                break;
            }

            // -1
            if (u.node > MIN_POSITION && !discovered[u.node - 1]) {

                if (dist[u.node - 1] > dist[u.node] + 1) {

                    dist[u.node - 1] = dist[u.node] + 1;
                    discovered[u.node - 1] = true;
                    prevNode[u.node - 1] = u.node;

                    q.add(new Node(u.node - 1, u.step + 1));
                }
            }

            // +1
            if (u.node < MAX_POSITION && !discovered[u.node + 1]) {

                if (dist[u.node + 1] > dist[u.node] + 1) {

                    dist[u.node + 1] = dist[u.node] + 1;
                    discovered[u.node + 1] = true;
                    prevNode[u.node + 1] = u.node;

                    q.add(new Node(u.node + 1, u.step + 1));
                }
            }

            // *2
            if (u.node * 2 <= MAX_POSITION && !discovered[u.node * 2]) {

                if (dist[u.node * 2] > dist[u.node] + 1) {

                    dist[u.node * 2] = dist[u.node] + 1;
                    discovered[u.node * 2] = true;
                    prevNode[u.node * 2] = u.node;

                    q.add(new Node(u.node * 2, u.step + 1));
                }
            }
        }

        printPath(K);
    }

    static void printPath(int node) {

        if (prevNode[node] != -1) {

            printPath(prevNode[node]);
        }

        System.out.print(node + " ");
    }
}

class Node {

    int node;
    int step;

    Node(int node, int step) {

        this.node = node;
        this.step = step;
    }
}