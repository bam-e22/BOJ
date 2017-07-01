import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ#3665 Rankings 최종 순위
 * https://www.acmicpc.net/problem/3665
 */

public class Main {

    static final int NONE = 0;
    static final int IMPOSSIBLE = 1;
    static final int NOT_DETERMINED = 2;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            // 개수 n 입력
            int n = Integer.parseInt(br.readLine());

            // indegree배열과 인접행렬 생성
            int[] indegree = new int[n + 1];
            int[][] graph = new int[n + 1][n + 1];

            int[] t = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {

                t[i] = Integer.parseInt(st.nextToken());
            }

            // n^2 으로 인접행렬 생성, indegree 생성
            for (int i = 0; i < n; i++) {

                for (int j = i + 1; j < n; j++) {

                    graph[t[i]][t[j]] = 1;
                    indegree[t[j]]++;
                }
            }

            // 바뀐 순위의 개수 m
            int m = Integer.parseInt(br.readLine());

            // 바뀐 순위에 따라 간선과 indegree를 갱신한다
            for (int i = 0; i < m; i++) {

                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());


                if (graph[a][b] == 1) {

                    graph[a][b] = 0;
                    graph[b][a] = 1;

                    indegree[a]++;
                    indegree[b]--;
                } else {

                    graph[b][a] = 0;
                    graph[a][b] = 1;

                    indegree[a]--;
                    indegree[b]++;
                }
            }

            // 위상정렬
            // queue에 2개 이상 입력된다면 순위를 정할 수 없다. -> ? 출력
            // loop가 n번 돌기 전에 queue가 비워진다면 사이클이 생긴 것이므로 IMPOSSIBLE -> 노드는 남아있는데 indegree가 0인 노드가 없는 상태

            Queue<Integer> queue = new LinkedList<>();
            int[] rankings = new int[n];

            for (int i = 1; i <= n; i++) {

                if (indegree[i] == 0) {

                    queue.add(i);
                }
            }

            int ans = NONE;
            for (int i = 0; i < n; i++) {

                if (queue.isEmpty()) {

                    ans = IMPOSSIBLE;
                    break;
                }

                if (queue.size() > 1) {

                    ans = NOT_DETERMINED;
                    break;
                }

                int u = queue.poll();
                rankings[i] = u;

                for (int j = 1; j < n + 1; j++) {

                    if (graph[u][j] == 1) {

                        indegree[j]--;

                        if (indegree[j] == 0) queue.add(j);
                    }
                }
            }

            if (ans == NONE) {

                for (int x : rankings) bw.write(x + " ");
                bw.write("\n");
            } else if (ans == IMPOSSIBLE) bw.write("IMPOSSIBLE\n");
            else if (ans == NOT_DETERMINED) bw.write("?\n");

        } // ~test case loop

        bw.flush();

        bw.close();
        br.close();
    }
}
