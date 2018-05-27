import java.io.*;
import java.util.StringTokenizer;

/*

// 왼쪽
6 7
1 1
2 1
2 2
2 3
2 4
2 5
2 6

6 7
1 3
2 1
2 2
2 3
2 4
2 5
2 6

// 오른쪽
6 7
1 6
2 1
2 2
2 3
2 4
2 5
2 6

6 7
1 4
2 1
2 2
2 3
2 4
2 5
2 6

// 왼쪽
5 6
1 1
2 1
2 2
2 3
2 4
2 5

// 오른쪽
5 6
1 5
2 1
2 2
2 3
2 4
2 5

// 가운데
5 6
1 3
2 1
2 2
2 3
2 4
2 5

//////////////////
3 6
1 2
1 1
1 3
2 1
2 2
2 3

//
5 7
1 5
1 4
2 1
2 2
2 3
2 4
2 5

//
6 7
1 2
2 1
2 2
2 3
2 4
2 5
2 6

//
6 8
1 1
1 2
2 1
2 2
2 3
2 4
2 5
2 6

//
7 8
1 2
2 1
2 2
2 3
2 4
2 5
2 6
2 7


 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        for (int i = 0; i < N + 1; i++) arr[i] = i;

        SegmentTree segmentTree = new SegmentTree(arr, N + 1);

        System.out.println("[SegmentArr]");
        for (int i = 0; i < 20; i++) {
            System.out.print(segmentTree.segmentArr[i] + " ");
        }
        System.out.println();

        while (M-- > 0) {

            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            // x번 학생이 내린다 (Update)
            if (cmd == 1) {

                segmentTree.update(segmentTree.queryIdx(x, 1, 1, N), 0, 1, 1, N);
            }
            // x번 좌석을 검사한다 (Query)
            else if (cmd == 2) {

                bw.write(segmentTree.query(x, x, 1, 1, N) + "\n");
            }
        }

        bw.flush();

        bw.close();
        br.close();
    } // ~main
}

class SegmentTree {

    // The array that stores segment tree nodes
    int[] segmentArr;

    SegmentTree(int[] arr, int n) {

        int x = (int) Math.ceil(Math.log(200000) / Math.log(2));
        int segmentSize = (int) Math.pow(2, x) * 2;

        segmentArr = new int[segmentSize];

        init(arr, 1, n - 1, 1);
    }

    int init(int[] arr, int left, int right, int node) {

        if (left == right) return segmentArr[node] = arr[left];

        int mid = (left + right) / 2;

        return segmentArr[node] = Math.max(init(arr, left, mid, node * 2), init(arr, mid + 1, right, node * 2 + 1));
    }

    int query(int left, int right, int node, int nodeLeft, int nodeRight) {

        // 두 구간이 겹치지 않는 경우
        if (left > nodeRight || right < nodeLeft) return 0;

        // 노드 구간이 완전히 속하는 경우
        if (left <= nodeLeft && right >= nodeRight) return segmentArr[node];

        // 그 외의 경우
        int nodeMid = (nodeLeft + nodeRight) / 2;

        return Math.max(query(left, right, node * 2, nodeLeft, nodeMid), query(left, right, node * 2 + 1, nodeMid + 1, nodeRight));
    }

    int queryIdx(int value, int node, int nodeLeft, int nodeRight) {

        if (segmentArr[node] >= value) {

            if (segmentArr[node] == value && nodeLeft == nodeRight) return nodeLeft;
            else {

                int nodeMid = (nodeLeft + nodeRight) / 2;
                return Math.max(queryIdx(value, node * 2, nodeLeft, nodeMid), queryIdx(value, node * 2 + 1, nodeMid + 1, nodeRight));
            }
        } else {

            return -1;
        }
    }

    int update(int index, int newValue, int node, int nodeLeft, int nodeRight) {

        // 노드 구간에 포함되지 않는 경우
        if (index < nodeLeft || index > nodeRight) return segmentArr[node];

        // 노드 구간에 포함되는 경우
        // Leaf인 경우
        if (nodeLeft == nodeRight) return segmentArr[node] = newValue;

        // Leaf가 아닌 경우
        int nodeMid = (nodeLeft + nodeRight) / 2;

        return segmentArr[node] = update(index, newValue, node * 2, nodeLeft, nodeMid) + update(index, newValue, node * 2 + 1, nodeMid + 1, nodeRight);
    }
}