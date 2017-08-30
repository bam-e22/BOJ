import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            // Input
            String input = br.readLine();

            if (input.equals("0")) return;

            StringTokenizer st = new StringTokenizer(input);

            int N = Integer.parseInt(st.nextToken());

            int[] histogram = new int[N];
            for (int i = 0; i < N; i++) histogram[i] = Integer.parseInt(st.nextToken());

            // Solve
            SegmentTree segmentTree = new SegmentTree(histogram, N);

            System.out.println(queryMaxArea(histogram, segmentTree, N, 0, N - 1));

        } // ~test case loop
    } // ~main

    static long queryMaxArea(int[] histogram, SegmentTree segmentTree, int N, int left, int right) {

        if (left == right) return histogram[left];

        int minIdx = segmentTree.query(histogram, left, right, 1, 0, N - 1);

        long maxArea = (long) histogram[minIdx] * (right - left + 1);

        if (minIdx - 1 >= left) {

            long leftTempMax = queryMaxArea(histogram, segmentTree, N, left, minIdx - 1);
            maxArea = maxArea < leftTempMax ? leftTempMax : maxArea;
        }

        if (minIdx + 1 <= right) {

            long rightTempMax = queryMaxArea(histogram, segmentTree, N, minIdx + 1, right);
            maxArea = maxArea < rightTempMax ? rightTempMax : maxArea;
        }

        return maxArea;
    }
}

class SegmentTree {

    int[] segmentArr; // The array that stores segment tree nodes

    SegmentTree(int[] arr, int n) {

        int x = (int) Math.ceil(Math.log(100000) / Math.log(2));

        int segmentSize = (int) Math.pow(2, x) * 2;

        segmentArr = new int[segmentSize];

        init(arr, 0, n - 1, 1);
    }

    int init(int[] arr, int left, int right, int node) {

        if (left == right) return segmentArr[node] = left;

        int mid = (left + right) / 2;

        int leftChildNode = init(arr, left, mid, node * 2);
        int rightChildNode = init(arr, mid + 1, right, node * 2 + 1);

        return segmentArr[node] = arr[leftChildNode] <= arr[rightChildNode] ? leftChildNode : rightChildNode;
    }

    int query(int[] arr, int left, int right, int node, int nodeLeft, int nodeRight) {

        // 두 구간이 겹치지 않는 경우
        if (right < nodeLeft || left > nodeRight) return -1;

        // 노드 구간이 완전히 속하는 경우
        if (left <= nodeLeft && right >= nodeRight) return segmentArr[node];

        int nodeMid = (nodeLeft + nodeRight) / 2;

        int leftChildNode = query(arr, left, right, node * 2, nodeLeft, nodeMid);
        int rightChildNode = query(arr, left, right, node * 2 + 1, nodeMid + 1, nodeRight);

        if (leftChildNode == -1) return rightChildNode;
        else if (rightChildNode == -1) return leftChildNode;
        else return arr[leftChildNode] <= arr[rightChildNode] ? leftChildNode : rightChildNode;
    }
}
