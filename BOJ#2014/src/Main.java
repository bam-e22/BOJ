import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        Long[] prime = new Long[K];

        PriorityQueue<Long> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {

            prime[i] = Long.parseLong(st.nextToken());
            pq.add(prime[i]);
        }

        long u = 0L;
        for (int i = 0; i < N - 1; i++) {

            u = pq.poll();

            for (int j = 0; j < K; j++) {

                pq.add(u * prime[j]);

                if (u % prime[j] == 0) break;
            }
        }

        System.out.println(pq.poll());
    }
}
