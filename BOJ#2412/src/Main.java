import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        Node[] nodes = new Node[n];
        boolean[] discovered = new boolean[n];

        for (int i = 0; i < n; i++) {

            st = new StringTokenizer(br.readLine());

            nodes[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(nodes);

        Comparator<Node> cp = (o1, o2) -> {

            if (o1.col < o2.col) return -1;
            if (o1.col > o2.col) return 1;
            if (o1.col == o2.col) {

                if (o1.row < o2.row) return -1;
                if (o1.row > o2.row) return 1;
            }

            return 0;
        };

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0));

        int step = -1;

        while (!queue.isEmpty()) {

            step++;

            int size = queue.size();
            for (int i = 0; i < size; i++) {

                Node u = queue.poll();

                if (u.col == T) {

                    System.out.println(step);
                    return;
                }

                // 탐색의 범위를 줄여보자
                int startIdx = Arrays.binarySearch(nodes, new Node(u.row - 2 < 0 ? 0 : u.row - 2, u.col - 2 < 0 ? 0 : u.col - 2), cp);
                startIdx = startIdx < 0 ? -startIdx - 1 : startIdx;

                // 탐색
                for (int j = startIdx; j < n; j++) {

                    int nextRow = nodes[j].row;
                    int nextCol = nodes[j].col;

                    // 체크할 조건들
                    if (discovered[j]) continue;
                    if (Math.abs(u.row - nextRow) > 2) continue;
                    if (u.col - nextCol < -2) break; // 탐색의 범위를 줄여보자
                    if (u.col - nextCol > 2) continue;

                    queue.add(new Node(nextRow, nextCol));
                    discovered[j] = true;
                }
            }
        }

        System.out.println(-1);
    }
}

class Node implements Comparable<Node> {

    int row, col;

    Node(int row, int col) {

        this.row = row;
        this.col = col;
    }

    @Override
    public int compareTo(Node o) {

        return this.col < o.col ? -1 : this.col > o.col ? 1 : this.row < o.row ? -1 : 1;
    }

    @Override
    public String toString() {

        return "(" + row + ", " + col + ")\n";
    }
}