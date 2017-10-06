import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_VALUE = 1000000000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] edges = new int[N][N];

        for (int i = 0; i < N; i++) {

            Arrays.fill(edges[i], MAX_VALUE);
            edges[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;

            edges[A][B] = 1;
            edges[B][A] = 1;
        }

        // solve
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    edges[i][j] = Math.min(edges[i][j], edges[i][k] + edges[k][j]);
                }
            }
        }

        int[] dist = new int[N];

        int min = MAX_VALUE;
        int minIdx = 0;
        for (int i = 0; i < N; i++) {


            for (int j = 0; j < N; j++) {

                dist[i] += edges[i][j];
            }

            if (min > dist[i]) {

                min = dist[i];
                minIdx = i;
            }
        }

        System.out.println(minIdx + 1);
    }
}
