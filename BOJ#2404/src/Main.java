import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /*

2 3 120 3
2 3 300 3
2 3 299 3
2 3 12 3
2 3 12000 7
54 795 12000 7
2 3 300 1
2 1 200 5
2 4 54 2

4
7
6
2
42
1
0
9
3
     */


    static int P, Q, A, N;

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        // solve : 분할의 개수를 구하여라
        System.out.println(solve(0, 1, 0, 1, 0));
    }

    static private int solve(int p, int q, int n, int t, int depth) {

        for (int i = 0; i < depth; i++) {

            System.out.print("   ");
        }
        System.out.println("(" + p + ", " + q + ", " + n + ", " + t + ")");

        // if P/Q == p/q인 경우 정답
        if (P * q == p * Q) {

            printRet(depth, 1);
            return 1;
        }
        // if P/Q < p/q인 경우 중단
        if (P * q < p * Q) {
            printRet(depth, 0);
            return 0;
        }
        if (N == n) {
            printRet(depth, -1);
            return 0;
        }

        int ret = 0;

        for (int i = t; q * i <= A; i++) {
            ret += solve(p * i + q, q * i, n + 1, i, depth + 1);
        }

        return ret;
    }

    static private void printRet(int depth, int ret) {

        for (int i = 0; i < depth; i++) {

            System.out.print("   ");
        }

        System.out.println("return " + ret);
    }
}
