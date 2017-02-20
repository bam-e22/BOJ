import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ#2565 전깃줄
 * https://www.acmicpc.net/problem/2565
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int N; // 전깃줄의 갯수
        Wire[] list;

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine().trim());

        list = new Wire[N];

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine().trim());

            list[i] = new Wire(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // solve
        Arrays.sort(list);

        int[] tailTable = new int[N];
        int length;

        // 초기값
        tailTable[0] = list[0].b;
        length = 1;

        for (int i = 1; i < N; i++) {

            int candidate = list[i].b;

            if (candidate < tailTable[0]) {

                tailTable[0] = candidate;
            } else if (candidate > tailTable[length - 1]) {

                tailTable[length++] = candidate;
            } else {

                int idx = Arrays.binarySearch(tailTable, 0, length, candidate);
                idx = idx < 0 ? -idx - 1 : idx;

                tailTable[idx] = candidate;
            }
        }

        bw.write(String.valueOf(N - length));
        bw.flush();

        bw.close();
        br.close();
    }
}

class Wire implements Comparable<Wire> {

    int a;
    int b;

    Wire(int a, int b) {

        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Wire o) {

        return this.a < o.a ? -1 : 1;
    }
}