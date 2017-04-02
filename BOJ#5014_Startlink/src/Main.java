import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ#5014 스타트링크
 * https://www.acmicpc.net/problem/5014
 */

public class Main {

    static final int INF = 100000000;
    static final String NOTFOUND = "use the stairs";

    static int[] dist = new int[1000001];

    static {

        Arrays.fill(dist, INF);
    }

    public static void main(String[] args) throws IOException {

        int F; // 건물의 층수
        int G; // goal
        int S; // 현재 층수
        int U, D; // 엘리베이터 UP, DOWN

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> queue = new PriorityQueue<Node>();

        queue.add(new Node(S, 0));
        dist[S] = 0;

        while (!queue.isEmpty()) {

            Node node = queue.poll();

            if (node.pos == G) {

                break;
            }

            if (dist[node.pos] < node.cost) {

                continue;
            }

            if (node.pos - D >= 1 && dist[node.pos - D] > dist[node.pos] + 1) {

                dist[node.pos - D] = dist[node.pos] + 1;
                queue.add(new Node(node.pos - D, dist[node.pos - D]));
            }

            if (node.pos + U <= F && dist[node.pos + U] > dist[node.pos] + 1) {

                dist[node.pos + U] = dist[node.pos] + 1;
                queue.add(new Node(node.pos + U, dist[node.pos + U]));
            }
        }

        System.out.println(dist[G] >= INF ? NOTFOUND : dist[G]);
    }
}

class Node implements Comparable<Node> {

    int pos;
    int cost;

    Node(int pos, int cost) {

        this.pos = pos;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {

        return this.cost < o.cost ? -1 : 1;
    }

    @Override
    public String toString() {

        return "pos=" + pos + ", cost=" + cost;
    }
}