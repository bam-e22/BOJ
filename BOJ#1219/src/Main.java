import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] dist = new long[N];
        Arrays.fill(dist, Long.MIN_VALUE);

        Edge[] edge = new Edge[M];
        int[] money = new int[N];

        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edge[i] = new Edge(s, d, -w);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {

            money[i] = Integer.parseInt(st.nextToken());
        }

        dist[S] = money[S];

        // Solve

        // 충분히 Relaxation을 해준다
        for (int i = 0; i < N + 100; i++) {

            for (int j = 0; j < M; j++) {

                if (dist[edge[j].s] == Long.MIN_VALUE) continue; // Relaxation이 불필요한 경우
                else if (dist[edge[j].s] == Long.MAX_VALUE) dist[edge[j].d] = Long.MAX_VALUE; // 무한대로 증가하는 경우
                else if (dist[edge[j].d] < dist[edge[j].s] + edge[j].w + money[edge[j].d]) {

                    dist[edge[j].d] = dist[edge[j].s] + edge[j].w + money[edge[j].d];

                    if (i >= N - 1) dist[edge[j].d] = Long.MAX_VALUE; // 무한대로 증가하는 경우
                }
            }
        }

        if (dist[D] == Long.MIN_VALUE) System.out.println("gg");
        else if (dist[D] == Long.MAX_VALUE) System.out.println("Gee");
        else System.out.println(dist[D]);
    }
}

class Edge {

    int s;
    int d;
    int w;

    Edge(int s, int d, int w) {

        this.s = s;
        this.d = d;
        this.w = w;
    }
}