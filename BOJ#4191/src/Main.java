import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<ArrayList<Integer>> edge;
    static boolean[] visited;
    static int ans = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        // Test Case Loop
        while (T-- > 0) {

            // Input
            edge = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            visited = new boolean[n + 1];

            for (int i = 0; i < n + 1; i++) {

                edge.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {

                st = new StringTokenizer(br.readLine());

                edge.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));
            }

            ans = 0;

            for (int i = 0; i < l; i++) {

                knockDominos(Integer.parseInt(br.readLine()));
            }

            System.out.println(ans);
        }
    }

    static void knockDominos(int domino) {

        if (visited[domino]) return;

        ans++;
        visited[domino] = true;

        for (int i = 0; i < edge.get(domino).size(); i++) {

            knockDominos(edge.get(domino).get(i));
        }

    }
}
