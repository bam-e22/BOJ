import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        // input
        ArrayList<ArrayList<Integer>> edge = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int maxValue = 0;
        StringBuilder ret = new StringBuilder();

        for (int i = 0; i < N + 1; i++) {

            edge.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            edge.get(B).add(A);
        }

        // solve

        for (int i = 1; i < N + 1; i++) {

            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);

            boolean[] discovered = new boolean[N + 1];
            discovered[i] = true;

            int cnt = 0;
            while (!queue.isEmpty()) {

                int node = queue.poll();

                for (int x : edge.get(node)) {

                    if (discovered[x]) continue;

                    discovered[x] = true;
                    cnt++;
                    queue.add(x);
                }
            }

            if (cnt > maxValue) {

                maxValue = cnt;

                ret = new StringBuilder();
                ret.append(i + " ");

            } else if (cnt == maxValue) {

                ret.append(i + " ");
            }
        }

        System.out.println(ret);
    }
}
