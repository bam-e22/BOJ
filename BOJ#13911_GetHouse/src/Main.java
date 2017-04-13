import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ#13911 집 구하기
 * https://www.acmicpc.net/problem/13911
 */

public class Main {

    static final int INF = 100000000;
    static final int MCDONALDS = 0;
    static final int STARBUCKS = 1;

    static int nV, nE;
    static int M; // 맥세권
    static int S; // 스세권
    static int[] limit = new int[2];
    static ArrayList<ArrayList<Edge>> adjList = new ArrayList<ArrayList<Edge>>();
    static int[][] dist = new int[2][10100];

    static {

        for (int i = 0; i < 2; i++) {

            Arrays.fill(dist[i], INF);
        }
    }

    public static void main(String[] args) throws IOException {

        // 집이 아닌 것들의 모음(맥도날드, 스타벅스)
        HashSet<Integer> notHouse = new HashSet<Integer>();

        // input - edge
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        nV = Integer.parseInt(st.nextToken());
        nE = Integer.parseInt(st.nextToken());

        // 1부터 ~ nV까지 + 더미노드 2개 = 1부터 nV+2까지
        for (int i = 0; i < nV + 3; i++) {

            adjList.add(new ArrayList<Edge>());
        }

        for (int i = 0; i < nE; i++) {

            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adjList.get(u).add(new Edge(v, w));
            adjList.get(v).add(new Edge(u, w));
        }

        // input - mcdonals
        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        limit[MCDONALDS] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {

            int src = Integer.parseInt(st.nextToken());

            notHouse.add(src);

            // 모든 맥도날드의 부모 더미노드 1개 생성 후 weight 0으로 연결해준다
            adjList.get(nV + 1).add(new Edge(src, 0));
        }

        // input - starbucks
        st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        limit[STARBUCKS] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {

            int src = Integer.parseInt(st.nextToken());

            notHouse.add(src);

            // 모든 스타벅스의 부모 더미노드 1개 생성 후 weight 0으로 연결해준다
            adjList.get(nV + 2).add(new Edge(src, 0));
        }

        // solve - dijkstra
        dijkstra(nV + 1, MCDONALDS);
        dijkstra(nV + 2, STARBUCKS);

        // solve - condition check
        int minCost = INF;

        // 실수했던 부분 #1 : 2차원 배열 조사 x, nV만큼만 조사하면 된다.
        for (int i = 1; i <= nV; i++) {

            if (notHouse.contains(i)) continue;
            if (dist[MCDONALDS][i] <= limit[MCDONALDS] &&
                    dist[STARBUCKS][i] <= limit[STARBUCKS]) {

                minCost = Math.min(minCost, dist[MCDONALDS][i] + dist[STARBUCKS][i]);
            }
        }

        System.out.println(minCost >= INF ? -1 : minCost);
    } // ~ main

    static void dijkstra(int src, int idx) {

        PriorityQueue<Node> pq = new PriorityQueue<Node>();

        pq.add(new Node(src, 0));
        dist[idx][src] = 0;

        while (!pq.isEmpty()) {

            Node u = pq.poll();

            if (dist[idx][u.node] < u.dist) continue;

            // 실수했던 부분 #2 : u.node(현재노드)에 연결된 간선들만 조사
            ArrayList<Edge> list = adjList.get(u.node);
            for (Edge e : list) {

                if (dist[idx][e.toNode] > dist[idx][u.node] + e.weight) {

                    dist[idx][e.toNode] = dist[idx][u.node] + e.weight;
                    pq.add(new Node(e.toNode, dist[idx][e.toNode]));
                }
            }
        } // ~while loop
    } // ~ dijkstra

}

class Edge {

    int toNode;
    int weight;

    Edge(int toNode, int weight) {

        this.toNode = toNode;
        this.weight = weight;
    }
}

class Node implements Comparable<Node> {

    int node;
    int dist;

    Node(int node, int dist) {

        this.node = node;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node o) {

        return this.dist < o.dist ? -1 : 1;
    }
}