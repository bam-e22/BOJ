import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * BOJ#1927 최소 힙
 * https://www.acmicpc.net/problem/1927
 */

public class Main {

    static final int POLL = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int cmd;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (N-- > 0) {

            cmd = Integer.parseInt(br.readLine());

            if (cmd == POLL) {

                if (pq.isEmpty()) System.out.println(0);
                else System.out.println(pq.poll());
            } else {

                pq.add(cmd);
            }
        } // ~ while loop
    }
}
