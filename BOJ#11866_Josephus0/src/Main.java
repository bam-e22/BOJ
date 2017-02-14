import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BOJ#11866
 * https://www.acmicpc.net/problem/11866
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int N;
        int M;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Integer> list = new LinkedList<Integer>();

        for (int i = 1; i <= N; i++) {

            list.add(i);
        }

        System.out.print("<");

        int idx = 0;
        while (!list.isEmpty()) {

            for (int i = 0; i < M - 1; i++) {

                idx = (idx + 1) % list.size();
            }

            if (list.size() == 1) {

                System.out.print(list.get(idx) + ">");
            } else {

                System.out.print(list.get(idx) + ", ");
            }

            list.remove(idx);
        }
    }
}
