import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#2965 SKOCIMIS 캥거루 세마리
 * https://www.acmicpc.net/problem/2965
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int A, B, C;
        int leftDiff, rightDiff;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        leftDiff = B - A;
        rightDiff = C - B;

        System.out.println(leftDiff < rightDiff ? rightDiff - 1 : leftDiff - 1);
    }
}
