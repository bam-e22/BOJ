import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        Queue<Pair> queue = new LinkedList<>();

        for (int i = 2; i <= Math.sqrt(K); i++) {

            if (K % i == 0 && K / i > 1) {

                queue.add(new Pair(i, K / i, 0));
            }
        }

        if (queue.isEmpty()) {

            System.out.println(0);
            return;
        }

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                Pair u = queue.poll();

                boolean check1 = true;
                boolean check2 = true;

                for (int j = 2; j <= Math.sqrt(u.nodeA); j++) {

                    if (u.nodeA % j == 0 && u.nodeA / j > 1) {

                        queue.add(new Pair(j, u.nodeA / j, u.depth + 1));

                        check1 = false;
                    }
                }

                for (int j = 2; j <= Math.sqrt(u.nodeB); j++) {

                    if (u.nodeB % j == 0 && u.nodeB / j > 1) {

                        queue.add(new Pair(j, u.nodeB / j, u.depth + 1));

                        check2 = false;
                    }
                }

                if (check1 && check2) {

                    System.out.println(u.depth + 1);
                    return;
                }
            }
        }
    }
}

class Pair {

    int nodeA;
    int nodeB;
    int depth;

    Pair(int A, int B, int depth) {

        this.nodeA = A;
        this.nodeB = B;
        this.depth = depth;
    }
}