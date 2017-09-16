import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static boolean[] visited = new boolean[102];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            Arrays.fill(visited, false);

            int N = Integer.parseInt(br.readLine());

            for (int i = 2; i <= N; i++) {
                for (int j = i; j <= N; j += i) {

                    visited[j] = !visited[j];
                }
            }

            int cnt = 0;
            for (int i = 1; i <= N; i++) {

                if (!visited[i]) cnt++;
            }

            System.out.println(cnt);
        }
    }
}
