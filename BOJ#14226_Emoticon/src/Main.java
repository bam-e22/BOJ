import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * BOJ#14226 이모티콘
 * https://www.acmicpc.net/problem/14226
 */

public class Main {

    static boolean[][] discovered = new boolean[10000][10000];

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());

        // solve - bfs
        PriorityQueue<Node> queue = new PriorityQueue<Node>();

        queue.add(new Node(1, 0, 0));

        while (!queue.isEmpty()) {

            Node u = queue.poll();

            if (u.value == S) {

                System.out.println(u.cost);
                break;
            }

            // copy
            if(!discovered[u.value][u.value]) {

                queue.add(new Node(u.value, u.value, u.cost + 1));
                discovered[u.value][u.value] = true;
            }

            // paste
            int addedValue = u.value + u.clipboard;
            if (!discovered[addedValue][u.clipboard] && addedValue < 10000) {

                queue.add(new Node(addedValue, u.clipboard, u.cost + 1));
                discovered[addedValue][u.clipboard] = true;
            }

            // delete
            int deletedValue = u.value - 1;
            if (deletedValue >= 0 && !discovered[deletedValue][u.clipboard]) {

                queue.add(new Node(deletedValue, u.clipboard, u.cost + 1));
                discovered[addedValue][u.clipboard] = true;
            }
        }
    }
}

class Node implements Comparable<Node> {

    int value;
    int clipboard;
    int cost;

    Node(int value, int clipboard, int cost) {

        this.value = value;
        this.clipboard = clipboard;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {

        return this.cost < o.cost ? -1 : 1;
    }
}