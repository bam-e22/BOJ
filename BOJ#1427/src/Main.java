import java.io.*;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<Character> pq = new PriorityQueue<>((o1, o2) -> o1 < o2 ? 1 : -1);
        char[] arr = br.readLine().toCharArray();

        for (char c : arr) pq.add(c);

        while (!pq.isEmpty()) bw.write(pq.poll());

        bw.flush();

        bw.close();
        br.close();
    }
}
