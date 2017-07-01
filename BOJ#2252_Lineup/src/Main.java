import java.io.*;
import java.util.*;

/**
 * BOJ#2252 줄세우기
 * https://www.acmicpc.net/problem/2252
 */

// 32000 * 32000 배열 선언 불가
// 배열 최대 사이즈?

public class Main {


    public static void main(String[] args) throws IOException {

        int N; // 학생의 수
        int M; // 비교한 횟수
        int[] indegree; // 진입차수 배열
        ArrayList<ArrayList<Integer>> edge = new ArrayList<ArrayList<Integer>>();

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        indegree = new int[N + 1];

        Arrays.fill(indegree, 0);

        for (int i = 0; i < N + 1; i++) {

            edge.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            edge.get(a).add(b);
            indegree[b]++;
        }

        // Solve

        Queue<Integer> indegreeQueue = new LinkedList<Integer>();

        // 위상정렬, indegree가 0인 것들을 queue에 넣는다.
        for (int i = 1; i <= N; i++) {

            if (indegree[i] == 0) {

                indegreeQueue.add(i);
                bw.write(i + " ");
            }
        }

        while (!indegreeQueue.isEmpty()) {

            int node = indegreeQueue.poll();

            // 연결된 노드들의 indegree를 감소시킨다

            for (int adjNode : edge.get(node)) {

                indegree[adjNode]--;

                if (indegree[adjNode] == 0) {

                    indegreeQueue.add(adjNode);
                    bw.write(adjNode + " ");
                }
            }
        }

        bw.flush();

        bw.close();
        br.close();
    }
}
