import java.io.*;
import java.util.StringTokenizer;

/**
 * 선데이코딩 베타라운드1 - E. 수열의 최소값
 * BOJ#14439 수열과 쿼리 17
 * https://www.acmicpc.net/problem/14438
 */

public class Main {

    static final int QUERY_CHANGE = 1;
    static final int QUERY_FIND_MIN = 2;

    public static void main(String[] args) throws IOException {

        int N; // 수열의 크기
        int nQuery; // 쿼리의 개수
        int[] A;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {

            A[i] = Integer.parseInt(st.nextToken());
        }

        // solve

        SegmentTree segmentTree = new SegmentTree(A, N);

        nQuery = Integer.parseInt(br.readLine());

        while (nQuery-- > 0) {

            st = new StringTokenizer(br.readLine());

            int queryType = Integer.parseInt(st.nextToken());


            // QUERY_CHANGE
            if (queryType == QUERY_CHANGE) {

                int i = Integer.parseInt(st.nextToken()) - 1;
                int j = Integer.parseInt(st.nextToken());

                segmentTree.update(i, j, 1, 0, N - 1);
            }
            // QUERY_FIND_MIN
            else if (queryType == QUERY_FIND_MIN) {

                int i = Integer.parseInt(st.nextToken()) - 1;
                int j = Integer.parseInt(st.nextToken()) - 1;

                bw.write(segmentTree.query(i, j, 1, 0, N - 1) + "\n");
            }

        } // ~while loop

        bw.flush();

        bw.close();
        br.close();
    }
}

class SegmentTree {

    final int MAX_VALUE = Integer.MAX_VALUE;
    int[] segmentArr; // The array that stores segment tree nodes

    SegmentTree(int[] arr, int n) {

        int x = (int) Math.ceil(Math.log(n) / Math.log(2));

        int segmentSize = (int) Math.pow(2, x) * 2;

        segmentArr = new int[segmentSize];

        init(arr, 0, n - 1, 1);
    }

    // node를 root로 하는 서브트리를 초기화하고, 이 구간의 최소치를 반환한다.
    int init(int[] arr, int left, int right, int node) {

        if (left == right) {

            return segmentArr[node] = arr[left];
        }

        int mid = (left + right) / 2;

        return segmentArr[node] = Math.min(init(arr, left, mid, node * 2), init(arr, mid + 1, right, node * 2 + 1));
    }

    // func#1. 구간 최소값 query
    int query(int left, int right, int node, int nodeLeft, int nodeRight) {

        // 두 구간이 겹치지 않는 경우
        if (left > nodeRight || right < nodeLeft) return MAX_VALUE;

        // 노드 구간이 완전히 속하는 경우
        if (left <= nodeLeft && right >= nodeRight) return segmentArr[node];

        // 그 외의 경우
        int nodeMid = (nodeLeft + nodeRight) / 2;

        return Math.min(query(left, right, node * 2, nodeLeft, nodeMid), query(left, right, node * 2 + 1, nodeMid + 1, nodeRight));
    }

    // func#2. SegmentTree 노드 값 갱신
    int update(int index, int newValue, int node, int nodeLeft, int nodeRight) {

        // 노드 구간에 포함되지 않는 경우
        if (index < nodeLeft || index > nodeRight) return segmentArr[node];

        // 노드 구간에 포함되는 경우
        // Leaf인 경우
        if (nodeLeft == nodeRight) return segmentArr[node] = newValue;

        int nodeMid = (nodeLeft + nodeRight) / 2;

        // Leaf가 아닌 경우
        return segmentArr[node] = Math.min(update(index, newValue, node * 2, nodeLeft, nodeMid), update(index, newValue, node * 2 + 1, nodeMid + 1, nodeRight));
    }

}