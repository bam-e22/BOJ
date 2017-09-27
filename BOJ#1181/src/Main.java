import java.io.*;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<String> pq = new PriorityQueue<>(
                (o1, o2) -> o1.length() < o2.length() ? -1 : o1.length() > o2.length() ? 1 : o1.compareTo(o2));

        for (int i = 0; i < N; i++) {

            pq.add(br.readLine());
        }

        String prevStr = pq.poll();
        bw.write(prevStr + "\n");
        while (!pq.isEmpty()) {

            String curStr = pq.poll();

            if (curStr.compareTo(prevStr) == 0) continue;

            bw.write(curStr + "\n");
            prevStr = curStr;
        }

        bw.flush();

        bw.close();
        br.close();
    }
}
