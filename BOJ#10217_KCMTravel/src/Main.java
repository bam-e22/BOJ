import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#10217 KCM Travel
 * https://www.acmicpc.net/problem/10217
 */

public class Main {

    static final int START = 0;

    public static void main(String[] args) throws IOException {

        int T;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            // input
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            for (int i = 0; i < K; i++) {

                st = new StringTokenizer(br.readLine());



            }

        } // ~test case loop
    }
}

class Edge {

    int toNode;
    int cost;
    int dist;

    Edge(int toNode, int cost, int dist) {

        this.toNode = toNode;
        this.cost = cost;
        this.dist = dist;
    }
}

class Node implements Comparable<Node> {

    int node;
    int cost;
    int dist;

    Node(int node, int cost, int dist) {

        this.node = node;
        this.cost = cost;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node o) {

        return this.dist < o.dist ? -1 : this.dist > o.dist ? 1 : this.cost < o.cost ? -1 : 1;
    }
}