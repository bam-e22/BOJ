import java.io.*;

/**
 * BOJ#11729 하노이 탑 이동 순서
 * https://www.acmicpc.net/problem/11729
 */

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {

        int N;

        // input
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        // solve
        bw.write((int) Math.pow(2, N) - 1 + "\n");

        solve(N, 1, 3);

        bw.flush();

        bw.close();
        br.close();

    } // ~ main

    static void solve(int N, int from, int to) throws IOException {

        if (N == 0) return;

        solve(N - 1, from, 6 - from - to);
        bw.write(from + " " + to + "\n");
        solve(N - 1, 6 - from - to, to);
    }
}
