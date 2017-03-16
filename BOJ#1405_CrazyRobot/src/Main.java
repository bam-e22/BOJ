import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#1405 미친 로봇
 * https://www.acmicpc.net/problem/1405
 */

public class Main {

    static boolean[][] visited = new boolean[31][31];
    static double[] p = new double[4]; // 각 방향으로 이동할 확률
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        int N; // 로봇의 행동 수, 1 <= N <= 14

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 4; i++) {

            p[i] = Integer.parseInt(st.nextToken()) / (double) 100;
        }

        System.out.println(backtracking(N, 15, 15));
    }

    // (x, y) 위치에서 N만큼 더 이동할 때 단순 경로가 될 확률
    static double backtracking(int N, int x, int y) {

        // 이전 단계에서 1번 이동할 때 단순 경로가 될 확률
        if (visited[y][x]) {

            return 0.0;
        }

        // 이전 단계에서 1번 이동할 때 단순 경로가 될 확률
        if (N == 0) {

            return 1.0;
        }

        visited[y][x] = true;

        double ret = 0.0;
        for (int i = 0; i < 4; i++) {

            ret += p[i] * backtracking(N - 1, x + dx[i], y + dy[i]);
        }

        // 다른 경로로 또 방문할 수 있으므로 false로 설정해준다
        visited[y][x] = false;

        return ret;
    }
}
