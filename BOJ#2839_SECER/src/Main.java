import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ#2839 설탕 배달
 * https://www.acmicpc.net/problem/2839
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int cnt = Integer.MAX_VALUE;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int loopCnt = N / 5;
        for (int portion_5 = 0; portion_5 <= loopCnt; portion_5++) {

            int tempCnt = 0;
            int tempN = N;

            // 5
            tempN -= 5 * portion_5;
            tempCnt += portion_5;

            // 3
            int portion_3 = tempN / 3;
            tempN -= 3 * portion_3;

            tempCnt += portion_3;

            if (tempN == 0) {

                cnt = cnt > tempCnt ? tempCnt : cnt;
            }

        }
        if (cnt == Integer.MAX_VALUE) {

            System.out.println(-1);
        } else {
            System.out.println(cnt);
        }

    }
}