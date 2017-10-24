import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2 {

    static int N;
    static int[][] S = new int[21][21];
    static boolean[] visited = new boolean[21];
    static int minDiff = Integer.MAX_VALUE;
    static ArrayList<Integer> teamA = new ArrayList<>();
    static ArrayList<Integer> teamB = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {

                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // Solve
        visited[0] = true;
        teamA.add(0);
        dfs(0, teamA);

        System.out.println(minDiff);
    }

    static void dfs(int node, ArrayList<Integer> teamA) {

        if (teamA.size() == N / 2) {

            for (int i = 0; i < N; i++) {

                if (!visited[i]) teamB.add(i);
            }

            int sumA = 0;
            int sumB = 0;

            for (int i = 0; i < N / 2; i++) {
                for (int j = 0; j < N / 2; j++) {

                    sumA += S[teamA.get(i)][teamA.get(j)];
                    sumB += S[teamB.get(i)][teamB.get(j)];
                }
            }

            int diff = Math.abs(sumA - sumB);
            minDiff = minDiff > diff ? diff : minDiff;

            teamB.clear();

        } else {

            for (int i = node + 1; i < N; i++) {

                if (!visited[i]) {

                    visited[i] = true;
                    teamA.add(i);

                    dfs(i, teamA);

                    visited[i] = false;
                    teamA.remove(teamA.size() - 1);
                }
            }
        }
    }
}
