import java.io.*;
import java.util.StringTokenizer;

public class Main {

    // dp[idx] : idx 쉼터에서 출발 했을 때, 방문할 수 있는 쉼터의 최대 개수
    static int[] dp = new int[5001];

    static int N;
    static int M;
    static int[] height = new int[5001];
    static int[][] edges = new int[5001][5001];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {

            height[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            edges[A][B] = 1;
            edges[B][A] = 1;
        }

        for (int idx = 1; idx <= N; idx++) {

            solve(idx);
        }

        for (int i = 1; i <= N; i++) {

            bw.write(dp[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int solve(int idx) {

        // memoizatoin
        if (dp[idx] != 0) return dp[idx];

        // 탐색
        boolean isEnd = true;

        for (int i = 1; i <= N; i++) {

            // 방문 조건
            if (edges[idx][i] == 1 && height[idx] < height[i]) {

                isEnd = false;
                dp[idx] = Math.max(dp[idx], solve(i));

            }
        }

        // 기저 조건 (더 이상 방문할 곳이 없는 경우)
        if (isEnd) return dp[idx] = 1;
        else dp[idx]++;

        return dp[idx];
    }
}
