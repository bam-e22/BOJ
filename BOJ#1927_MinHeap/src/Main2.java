import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ#1927 최소 힙
 * https://www.acmicpc.net/problem/1927
 */

public class Main2 {

    static final int POLL = 0;
    static final int MAX_SIZE = 100_001;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int cmd;
        StringBuilder sb = new StringBuilder();

        Heap heap = new Heap(MAX_SIZE);
        while (N-- > 0) {

            cmd = Integer.parseInt(br.readLine());

            if (cmd == POLL) {

                if (heap.getLength() > 0) sb.append(heap.poll() + "\n");
                else sb.append("0\n");
            } else {

                heap.add(cmd);
            }
        } // ~ while loop

        System.out.println(sb);
    }
}

class Heap {

    static final int HEAD = 1;

    private int[] A = new int[100_001];
    private int length = 0;

    Heap(int size) {

        A = new int[size];
    }

    void add(int a) {

        A[++length] = a;

        for (int i = length / 2; i >= 1; i--) {

            heapify(i);
        }
    }

    void heapify(int parentNode) {

        // there is no child
        if (parentNode * 2 > length) return;

        // smallest child
        int childNode = getSmallestChildNode(parentNode);

        // min heap property check
        if (A[parentNode] <= A[childNode]) return;

        swap(parentNode, childNode);

        // recursive
        heapify(childNode);
    }

    int getSmallestChildNode(int parentNode) {

        if (parentNode * 2 + 1 > length) return parentNode * 2;
        else return A[parentNode * 2] < A[parentNode * 2 + 1] ? parentNode * 2 : parentNode * 2 + 1;
    }

    int poll() {

        int min = A[HEAD];

        swap(HEAD, length);
        length--;

        heapify(HEAD);

        return min;
    }

    void swap(int idx1, int idx2) {

        A[idx1] ^= A[idx2];
        A[idx2] ^= A[idx1];
        A[idx1] ^= A[idx2];
    }

    int getLength() {

        return length;
    }
}
