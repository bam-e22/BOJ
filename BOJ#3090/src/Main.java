import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static final boolean DEBUG = true;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        PriorityQueue<Node> pq = new PriorityQueue<>();

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {

            arr[i] = Integer.parseInt(st.nextToken());

            if (i > 0 && i < N) {

                pq.add(new Node(arr, i - 1, i));
            }
        }

        if (DEBUG) printPQ(pq);
        if (DEBUG) System.out.println("------------");
        if (DEBUG) System.out.println();
        if (DEBUG) System.out.println();

        while (T-- > 0) {

            if (DEBUG) System.out.println("Iteration #" + T);

            Node minDiffNode = pq.poll();
            if (DEBUG) System.out.println("minDiffNode:" + minDiffNode);

            // Left Node
            if (minDiffNode.getDiff() < 0) {

                arr[minDiffNode.leftIdx]--;
            }
            // Right Node
            else {

                arr[minDiffNode.rightIdx]--;
            }

            pq.add(minDiffNode);

            if (DEBUG) System.out.println("\nAfter::");
            if (DEBUG) printPQ(pq);
            if (DEBUG) System.out.println(Arrays.toString(arr));
            if (DEBUG) System.out.println("------------");
        }

        for (int num : arr) {

            System.out.print(num + " ");
        }
    }

    static void printPQ(PriorityQueue<Node> pq) {

        Iterator<Node> it = pq.iterator();
        while (it.hasNext()) {

            System.out.println(it.next());
        }
    }
}

class Node implements Comparable<Node> {

    private int[] arr;
    int leftIdx, rightIdx;

    Node(int[] arr, int leftIdx, int rightIdx) {

        this.arr = arr;
        this.leftIdx = leftIdx;
        this.rightIdx = rightIdx;
    }

    int getABSDiff() {

        return Math.abs(this.arr[rightIdx] - this.arr[leftIdx]);
    }

    int getDiff() {

        return this.arr[rightIdx] - this.arr[leftIdx];
    }

    @Override
    public int compareTo(Node o) {

        return this.getABSDiff() < o.getABSDiff() ? 1 : this.getABSDiff() > o.getABSDiff() ? -1 : 0;
    }

    @Override
    public String toString() {

        return "(" + leftIdx + ", " + rightIdx + "), diff=" + getABSDiff();
    }
}