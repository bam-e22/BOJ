import java.io.*;
import java.util.StringTokenizer;

/**
 * BOJ#4307 개미
 * https://www.acmicpc.net/problem/4307
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int l; // 막대의 길이
        int n; // 개미의 수
        int maxTime, minTime;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            maxTime = 0;
            minTime = Integer.MAX_VALUE;

            StringTokenizer st = new StringTokenizer(br.readLine());

            l = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            int half = l / 2;
            int innerPos = l;
            int outerPos = half;

            for (int i = 0; i < n; i++) {

                int pos = Integer.parseInt(br.readLine());

                innerPos = Math.abs(half - innerPos) > Math.abs(half - pos) ? pos : innerPos;
                outerPos = Math.abs(half - outerPos) < Math.abs(half - pos) ? pos : outerPos;
            }

            minTime = Math.min(minTime, Math.abs(0 - innerPos));
            minTime = Math.min(minTime, Math.abs(l - innerPos));

            maxTime = Math.max(maxTime, Math.abs(0 - outerPos));
            maxTime = Math.max(maxTime, Math.abs(l - outerPos));

            bw.write(minTime + " " + maxTime + "\n");
        } // ~ test case loop

        bw.flush();

        bw.close();
        br.close();
    }

}