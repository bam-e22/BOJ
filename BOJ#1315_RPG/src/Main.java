import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#1315 RPG
 * https://www.acmicpc.net/problem/1315
 */

public class Main {

    static final int NOT_VISITED = -1;

    static int N;
    static int[] STR = new int[101];
    static int[] INT = new int[101];
    static int[] PNT = new int[101];
    static int[][] dp = new int[1010][1010];

    static {

        for (int i = 0; i < 1010; i++) {

            Arrays.fill(dp[i], NOT_VISITED);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            STR[i] = Integer.parseInt(st.nextToken());
            INT[i] = Integer.parseInt(st.nextToken());
            PNT[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve(1, 1));

    } // ~ main

    // return dp[strength][intellect] : 현재 상태가 strength, intellect일 때 꺨 수 있는 퀘스트의 최대 개수
    static int solve(int strength, int intellect) {

        if (strength > 1000) strength = 1000;
        if (intellect > 1000) intellect = 1000;

        // memoization
        if (dp[strength][intellect] > NOT_VISITED) {

            return dp[strength][intellect];
        }

        // 현재 획득할 수 있는 포인트와 꺨 수 있는 퀘스트의 개수
        int points = 0;
        int nQuest = 0;

        // Complete all possible missions that are not completed yet
        for (int i = 0; i < N; i++) {

            if (strength >= STR[i] || intellect >= INT[i]) {

                nQuest++;
                points += PNT[i];
            }
        }

        int totalFreePoint = points - (strength + intellect) + 2;

        // If there is no free points, return
        if (totalFreePoint == 0) {

            return dp[strength][intellect] = 0;
        } else if (totalFreePoint >= 999) {

            return dp[strength][intellect] = N;
        } else {

            for (int i = 0; i <= totalFreePoint; i++) {

                nQuest = max(nQuest, solve(strength + i, intellect + totalFreePoint - i));
            }

            return dp[strength][intellect] = nQuest;
        }
    }

    static int max(int a, int b) {

        return a > b ? a : b;
    }
}
