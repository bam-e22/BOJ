import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_VALUE = 1000000000;
    static int min = MAX_VALUE;

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // Graph Modeling
        ArrayList<ArrayList<Integer>> floor = new ArrayList<>();
        ArrayList<ArrayList<Integer>> elevator = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {

            floor.add(new ArrayList<>());
        }

        for (int i = 0; i < M + 1; i++) {

            elevator.add(new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {

            st = new StringTokenizer(br.readLine());

            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            while (X <= N) {

                floor.get(X).add(i); // 층 정보 : 각 층마다 탈 수 있는 엘레베이터
                elevator.get(i).add(X); // 엘레베이터 정보 : 엘레베이터마다 갈 수 있는 각 층

                X += Y;
            }
        }

        st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // solve
        PriorityQueue<Node> pq = new PriorityQueue<>();

        int[] dist = new int[M + 1];
        int[] prev = new int[M + 1];
        Arrays.fill(dist, MAX_VALUE);

        for (int i = 0; i < floor.get(A).size(); i++) {

            pq.add(new Node(floor.get(A).get(i), 1));
            dist[floor.get(A).get(i)] = 1;
        }

        while (!pq.isEmpty()) {

            Node u = pq.poll();

            if (dist[u.elevator] < u.dist) continue;

            for (int i = 0; i < elevator.get(u.elevator).size(); i++) {

                int level = elevator.get(u.elevator).get(i);

                for (int j = 0; j < floor.get(level).size(); j++) {

                    int nextElevator = floor.get(level).get(j);

                    if (dist[nextElevator] > u.dist + 1) {

                        dist[nextElevator] = u.dist + 1;
                        prev[nextElevator] = u.elevator;

                        pq.add(new Node(nextElevator, dist[nextElevator]));
                    }
                }
            }
        }

        int lastElevator = 0;
        for (int i = 0; i < floor.get(B).size(); i++) {

            if (min > dist[floor.get(B).get(i)]) {

                min = dist[floor.get(B).get(i)];
                lastElevator = floor.get(B).get(i);
            }
        }

        if (min == MAX_VALUE) {

            System.out.println(-1);
        } else {

            System.out.println(min);
            print(prev, lastElevator);
        }
    } // ~main

    static void print(int[] prev, int elevator) {

        if (elevator == 0) return;
        print(prev, prev[elevator]);
        System.out.println(elevator);
    }
}

class Node implements Comparable<Node> {

    int elevator;
    int dist;

    Node(int elevator, int dist) {

        this.elevator = elevator;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node o) {

        return this.dist < o.dist ? -1 : 1;
    }
}