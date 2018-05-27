import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ#1014 컨닝
 * https://www.acmicpc.net/problem/1014
 */

public class Main {

    static final int UNAVAILABLE = 0;
    static final int AVAILABLE = 1;
    static int[][] dp = new int[11][1 << 11];

    public static void main(String[] args) throws IOException {

        int C; // 테스트케이스의 개수
        int N, M; // 교실 사이즈
        int[][] map;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        C = Integer.parseInt(br.readLine());

        while (C-- > 0) {

            int ans = 0;

            // input
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][M];

            for (int i = 0; i < N; i++) {

                String s = br.readLine();
                for (int j = 0; j < M; j++) {

                    char c = s.charAt(j);

                    if (c == '.') map[i][j] = AVAILABLE;
                    else if (c == 'x') map[i][j] = UNAVAILABLE;
                }
            }

            // solve
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {


                }
            }



            System.out.println(ans);

        } // ~while loop
    }
}
