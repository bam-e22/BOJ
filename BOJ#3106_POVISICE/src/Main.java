import java.io.*;
import java.util.StringTokenizer;

/**
 * BOJ#3106_POVISICE
 * https://www.acmicpc.net/problem/3106
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Node[] nodes = new Node[N + 1];
        nodes[0] = new Node(-1, Integer.parseInt(br.readLine()));

        for (int i = 1; i <= N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int cost = Integer.parseInt(st.nextToken());
            int prevNode = Integer.parseInt(st.nextToken());

            nodes[i] = new Node(prevNode, cost);

            bw.write(getSalaryRiseCount(nodes, i) + "\n");
        }

        bw.flush();

        bw.close();
        br.close();
    }

    static int getSalaryRiseCount(Node[] nodes, int idx) {

        int count = 0;
        int prevNode = nodes[idx].prevNode;
        int cost = nodes[idx].cost;

        while (prevNode != -1) {

            if (nodes[prevNode].cost < cost) {

                count++;

                nodes[prevNode].cost = cost;
            }

            prevNode = nodes[prevNode].prevNode;
        }

        return count;
    }
}

class Node {

    int prevNode;
    int cost;

    Node(int prevNode, int cost) {

        this.prevNode = prevNode;
        this.cost = cost;
    }
}
