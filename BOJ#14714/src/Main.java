import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[] mul = {-1, 1};
    static final int A_CHECK = -1;
    static final int NONE = 0;
    static final int B_CHECK = 1;

    static final boolean DEBUG = false;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int dA = Integer.parseInt(st.nextToken());
        int dB = Integer.parseInt(st.nextToken());

        /*
        if (N % dA == 0 && N % dB == 0 && dA != 1 && dB != 1 && (Math.min(Math.abs(A - B), N - Math.abs(A - B)) < Math.max(dA, dB))) {

            System.out.println("Evil Galazy");
            return;
        }
        */

        int[] check = new int[N + 1];
        boolean isCycleA = false;
        boolean isCycleB = false;

        Queue<Integer> qA = new LinkedList<>();
        Queue<Integer> qB = new LinkedList<>();

        qA.add(A);
        qB.add(B);

        check[A] = A_CHECK;
        check[B] = B_CHECK;

        int cnt = 0;

        while (!qA.isEmpty() && !qB.isEmpty()) {

            cnt++;
            int aSize = qA.size();
            int bSize = qB.size();

            // qA
            for (int i = 0; i < aSize; i++) {

                int u = qA.poll();
                if (DEBUG) System.out.println("A :: " + u);

                for (int j = 0; j < 2; j++) {

                    int next = u + mul[j] * dA;
                    next = next < 1 ? N + next : next;
                    next = next > N ? 1 + (next - N - 1) : next;

                    if (DEBUG) System.out.println("  >> " + next);

                    if (check[next] == B_CHECK) {

                        System.out.println(cnt);
                        return;
                    } else if (check[next] == NONE) {

                        check[next] = A_CHECK;
                        qA.add(next);
                    } else {

                        isCycleA = true;
                    }
                }

                check[u] = NONE;
            }

            cnt++;
            // qB
            for (int i = 0; i < bSize; i++) {

                int u = qB.poll();
                if (DEBUG) System.out.println("B :: " + u);

                for (int j = 0; j < 2; j++) {

                    int next = u + mul[j] * dB;
                    next = next < 1 ? N + next : next;
                    next = next > N ? 1 + (next - N - 1) : next;

                    if (DEBUG) System.out.println("  >> " + next);

                    if (check[next] == A_CHECK) {

                        System.out.println(cnt);
                        return;
                    } else if (check[next] == NONE) {

                        check[next] = B_CHECK;
                        qB.add(next);
                    } else {

                        isCycleB = true;
                    }
                }

                // 종료 조건
                if (isCycleA && isCycleB) {

                    System.out.println("Evil Galazy");
                    return;
                }

                check[u] = NONE;
            }

        }

        System.out.println(cnt);
    } // ~main
}

/*

6 5 1 1 2
>> 3

6 6 1 2 2
>> Evil Galazy

4 1 2 1 2
>> 1

4 1 2 2 2
>> Evil Galazy

*/