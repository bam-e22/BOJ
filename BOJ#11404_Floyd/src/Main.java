import java.io.*;
import java.util.StringTokenizer;

/**
 * BOJ#11404 플로이드
 * https://www.acmicpc.net/problem/11404
 */

public class Main {

    static final int INF = 100000000;

    public static void main(String[] args) throws IOException {

        int n; // 도시의 개수
        int m; // 버스의 개수
        int[][] busMap; // 경로 배열

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        busMap = new int[n + 1][n + 1];

        for (int i = 0; i < n + 1; i++) {

            for (int j = 0; j < n + 1; j++) {

                busMap[i][j] = i == j ? 0 : INF;
            }
        }

        for (int i = 0; i < m; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            busMap[a][b] = busMap[a][b] > c ? c : busMap[a][b];
        }

        // solve
        for (int k = 1; k < n + 1; k++) {

            for (int i = 1; i < n + 1; i++) {

                for (int j = 1; j < n + 1; j++) {

                    busMap[i][j] = Integer.min(busMap[i][j], busMap[i][k] + busMap[k][j]);
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {

            for (int j = 1; j < n + 1; j++) {

                System.out.print(busMap[i][j] >= INF ? 0 : busMap[i][j] + " ");
            }

            System.out.println();
        }
    }
}
