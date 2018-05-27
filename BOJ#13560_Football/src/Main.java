import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#13560 Football
 * https://www.acmicpc.net/problem/13560
 */

public class Main {

    static final int VALID = 1;
    static final int NOT_VALID = -1;

    public static void main(String[] args) throws IOException {

        int n; // number of teams
        int sum = 0;
        int sum2 = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        int maxValueFlag = 0;
        for (int i = 0; i < n; i++) {

            sum2 += i;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {

            int temp = Integer.parseInt(st.nextToken());

            if (temp == n - 1) {

                maxValueFlag++;
            }
            sum += temp;
        }

        if (sum == sum2 && maxValueFlag < 2) {

            System.out.println(VALID);
        } else {

            System.out.println(NOT_VALID);
        }

    }
}
