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
                int sumB = 0;

                for (int i = 0; i < N/2; i++) {
                    for (int j = 0; j < N/2; j++) {

                        sumA += S[teamA.get(i)][teamA.get(j)];
                        sumB += S[teamB.get(i)][teamB.get(j)];
                    }
                }

                int diff = Math.abs(sumA - sumB);
                minDiff = minDiff > diff ? diff : minDiff;
            }
        }

        System.out.println(minDiff);
    }
}
