import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#2163 초콜릿 자르기
 * https://www.acmicpc.net/problem/2163
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int N, M;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        System.out.println(N * M - 1);

    }
}
