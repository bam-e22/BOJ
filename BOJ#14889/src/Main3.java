import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main3 {

    static int N;
    static int[][] S = new int[21][21];
    static int minDiff = Integer.MAX_VALUE;
    static int[] teamA = new int[21];
    static int[] teamB = new int[21];

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
        combination(0, N, N / 2, 0);
        System.out.println(minDiff);
    }

    static void combination(int idx, int n, int k, int target) {

        if (k == 0) {

            boolean[] check = new boolean[N];
            int teamBIdx = 0;

            for (int i = 0; i < N / 2; i++) check[teamA[i]] = true;
            for (int i = 0; i < N; i++) if (!check[i]) teamB[teamBIdx++] = i;

            int sumA = 0;
            int sumB = 0;

            for (int i = 0; i < N / 2; i++) {
                for (int j = i + 1; j < N / 2; j++) {

                    sumA += S[teamA[i]][teamA[j]] + S[teamA[j]][teamA[i]];
                    sumB += S[teamB[i]][teamB[j]] + S[teamB[j]][teamB[i]];
                }
            }

            int diff = Math.abs(sumA - sumB);
            minDiff = minDiff > diff ? diff : minDiff;

        } else if (target == n) {

            return;
        } else {

            teamA[idx] = target;
            combination(idx + 1, n, k - 1, target + 1);
            combination(idx, n, k, target + 1);
        }
    }
}
