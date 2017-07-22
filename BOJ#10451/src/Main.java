import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        // Test Case
        while (T-- > 0) {

            // input
            int N = Integer.parseInt(br.readLine());

            int[] edge = new int[N + 1];
            boolean[] visited = new boolean[N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= N; i++) {

                edge[i] = Integer.parseInt(st.nextToken());
            }

            // solve
            int cnt = 0;

            for (int i = 1; i <= N; i++) {

                if (visited[i]) continue;
                visited[i] = true;

                cnt++;

                int nextNode = edge[i];
                while (!visited[nextNode]) {

                    visited[nextNode] = true;

                    nextNode = edge[nextNode];
                }
            }

            bw.write(cnt + "\n");
        } // ~ Test Case

        bw.flush();
    }
}
