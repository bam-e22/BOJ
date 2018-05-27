import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#1135 뉴스 전하기
 * https://www.acmicpc.net/problem/1135
 */

public class Main {

    static int N;
    static Node[] nodes = new Node[51];
    static ArrayList<ArrayList<Integer>> childList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {

            childList.add(new ArrayList<>());
        }

        nodes[0] = new Node();
        Integer.parseInt(st.nextToken());

        for (int i = 1; i < N; i++) {

            int prevNode = Integer.parseInt(st.nextToken());

            nodes[i] = new Node();

            nodes[prevNode].add(i);
        }

        // solve
        System.out.println(dfs(0));

    }

    static int dfs(int curNode) {

        int curSize = nodes[curNode].size;

        if (curSize == 0) return 0;

        int[] childCost = new int[curSize];

        for (int i = 0; i < curSize; i++) {

            int childNode = nodes[curNode].childNodes.get(i);

            childCost[i] = 1 + dfs(childNode);
        }

        Arrays.sort(childCost);

        int maxValue = 0;
        for (int i = 0; i < curSize; i++) {

            childCost[i] += (curSize - i - 1);

            maxValue = Math.max(maxValue, childCost[i]);
        }

        return maxValue;
    }
}

class Node {

    int size;
    ArrayList<Integer> childNodes;

    Node() {

        size = 0;
        childNodes = new ArrayList<>();
    }

    void add(int child) {

        size++;
        childNodes.add(child);
    }
}