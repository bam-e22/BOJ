import java.io.*;
import java.util.StringTokenizer;

/**
 * BOJ#3106_POVISICE
 * https://www.acmicpc.net/problem/3106
 *
 * O(N^2) -> 시간초과 소스 코드
 */

public class Main2 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Node2[] nodes = new Node2[N + 1];
        nodes[0] = new Node2(-1, Integer.parseInt(br.readLine()));

        for (int i = 1; i <= N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int cost = Integer.parseInt(st.nextToken());
            int prevNode = Integer.parseInt(st.nextToken());

            nodes[i] = new Node2(prevNode, cost);

            bw.write(getSalaryRiseCount(nodes, i) + "\n");
        }

        bw.flush();

        bw.close();
        br.close();
    }

    static int getSalaryRiseCount(Node2[] nodes, int idx) {

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

class Node2 {

    int prevNode;
    int cost;

    Node2(int prevNode, int cost) {

        this.prevNode = prevNode;
        this.cost = cost;
    }
}
