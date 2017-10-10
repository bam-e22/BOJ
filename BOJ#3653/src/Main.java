import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            // Input
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken()); // 가지고 있는 영화 수
            int m = Integer.parseInt(st.nextToken()); // 보려고 하는 영화 수

            int[] movieStack = new int[n + m];
            int[] moviePosInStack = new int[n + 1];

            for (int i = 1; i <= n; i++) {

                moviePosInStack[i] = m + i - 1;
                movieStack[m + i - 1] = 1;
            }

            SegmentTree segmentTree = new SegmentTree(movieStack, n + m);

            st = new StringTokenizer(br.readLine());

            // m개의 Query
            for (int i = 0; i < m; i++) {

                int selectedMovie = Integer.parseInt(st.nextToken());

                // Query
                bw.write(segmentTree.query(0, moviePosInStack[selectedMovie] - 1, 1, 0, n + m - 1) + " ");

                // Update
                segmentTree.update(moviePosInStack[selectedMovie], 0, 1, 0, n + m - 1);
                moviePosInStack[selectedMovie] = (m - 1) - i;
                segmentTree.update(moviePosInStack[selectedMovie], 1, 1, 0, n + m - 1);
            }

            bw.write("\n");

        } // ~test case loop

        bw.flush();

        bw.close();
        br.close();
    } // ~main
}

// 세그먼트 트리 : 구간 내 영화 개수
class SegmentTree {

    int[] segmentArr;

    SegmentTree(int[] arr, int N) {

        int x = (int) Math.ceil(Math.log(N) / Math.log(2));

        int size = (int) Math.pow(2, x) * 2;

        segmentArr = new int[size];

        init(arr, 0, N - 1, 1);
    }

    int init(int[] arr, int left, int right, int node) {

        if (left == right) {

            if (arr[left] != 0) {
                return segmentArr[node] = 1;
            } else return segmentArr[node] = 0;
        }

        int mid = (left + right) / 2;

        return segmentArr[node] = init(arr, left, mid, node * 2) + init(arr, mid + 1, right, node * 2 + 1);
    }

    int query(int left, int right, int node, int nodeLeft, int nodeRight) {

        // 두 구간이 겹치지 않는 경우
        if (left > nodeRight || right < nodeLeft) return 0;

        // 노드 구간이 완전히 속하는 경우
        if (left <= nodeLeft && right >= nodeRight) return segmentArr[node];

        // 그 외의 경우
        int nodeMid = (nodeLeft + nodeRight) / 2;

        return segmentArr[node] = query(left, right, node * 2, nodeLeft, nodeMid) + query(left, right, node * 2 + 1, nodeMid + 1, nodeRight);
    }

    int update(int index, int newValue, int node, int nodeLeft, int nodeRight) {

        // 노드 구간에 포함되지 않는 경우
        if (index < nodeLeft || index > nodeRight) return segmentArr[node];

        // 노드 구간에 포함되는 경우
        // Leaf 노드인 경우
        if (nodeLeft == nodeRight) return segmentArr[node] = newValue;

        // Leaf 노드가 아닌 경우
        int nodeMid = (nodeLeft + nodeRight) / 2;

        return segmentArr[node] = update(index, newValue, node * 2, nodeLeft, nodeMid) + update(index, newValue, node * 2 + 1, nodeMid + 1, nodeRight);
    }
}