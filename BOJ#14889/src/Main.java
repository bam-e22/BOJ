import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] S = new int[N][N];

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {

                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // Solve
        int minDiff = Integer.MAX_VALUE;

        for (int teamInfo = 0; teamInfo < (1 << N); teamInfo++) {

            if (Integer.bitCount(teamInfo) == N / 2) {

                ArrayList<Integer> teamA = new ArrayList<>();
                ArrayList<Integer> teamB = new ArrayList<>();

                for (int j = 0; j < N; j++) {

                    if ((teamInfo & (1 << j)) > 0) {

                        teamA.add(j);
                    } else {

                        teamB.add(j);
                    }
                }

                int sumA = 0;
                for (int a1 : teamA) {
                    for (int a2 : teamA) {

                        sumA += S[a1][a2];
                    }
                }

                int sumB = 0;
                for (int b1 : teamB) {
                    for (int b2 : teamB) {

                        sumB += S[b1][b2];
                    }
                }

                int diff = Math.abs(sumA - sumB);
                minDiff = minDiff > diff ? diff : minDiff;
            }
        }

        System.out.println(minDiff);
    }
}
