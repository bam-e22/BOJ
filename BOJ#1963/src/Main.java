import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static private int[] d = {1000, 100, 10, 1};
    static boolean[] isNotPrime = new boolean[10000];
    static boolean[] discovered = new boolean[10000];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        findPrimeNum();

        while (T-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if (A == B) {

                bw.write("0\n");
                continue;
            }

            int ret = bfs(A, B);
            bw.write(ret == -1 ? "Impossible\n" : ret + "\n");
        }

        bw.flush();

        bw.close();
        br.close();
    } // main

    static private int bfs(int A, int B) {

        discovered = new boolean[10000];

        Queue<Integer> queue = new LinkedList<>();

        queue.add(A);
        discovered[A] = true;

        int step = -1;

        while (!queue.isEmpty()) {

            step++;

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int u = queue.poll();

                // 종료 조건
                if (u == B) {

                    return step;
                }

                // u -> 자리수 분할
                int[] cipher = new int[4];
                for (int j = 0; j < 4; j++) {

                    cipher[j] = u / d[j];
                    u %= d[j];
                }

                // 탐색
                for (int j = 0; j < 4; j++) {

                    for (int k = 0; k < 10; k++) {

                        cipher[j] = cipher[j] + 1 > 9 ? 0 : cipher[j] + 1;

                        int nextNum = 0;

                        for (int l = 0; l < 4; l++) {

                            nextNum += cipher[l] * d[l];
                        }

                        // 조건
                        if (nextNum < 1000) continue;
                        if (discovered[nextNum]) continue;
                        if (isNotPrime[nextNum]) continue;

                        queue.add(nextNum);
                        discovered[nextNum] = true;
                    }
                }
            }
        }

        return -1;
    }

    static private void findPrimeNum() {

        for (int i = 2; i < 10000; i++) {

            if (isNotPrime[i]) continue;

            for (int j = 2; i * j < 10000; j++) {

                isNotPrime[i * j] = true;
            }
        }
    }
}