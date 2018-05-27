//Please don't change class name 'Main'

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        try (Scanner s = new Scanner(System.in)) {
            int n = s.nextInt();
            int d = s.nextInt();
            int k = s.nextInt();
            int j = s.nextInt();

            //여기부터 작성해 주세요
            Queue<Integer> queue = new LinkedList<>();

            if (d == 1) {

                for (int i = 1; i <= n; i++) {

                    queue.add(i);
                }
            } else {

                queue.add(1);
                for (int i = n; i > 1; i--) {

                    queue.add(i);
                }
            }

            boolean start = true;
            int idx = 0;
            int size = 0;

            while (queue.size() != 1) {

                size = queue.size();
                idx = (start ? k : k - 1) % size;

                for (int i = 0; i < idx; i++) {

                    int num = queue.poll();
                    queue.add(num);
                }

                if (start) start = false;

                queue.poll();
                k += j;
            }

            System.out.println(queue.poll());
        }
    }
}
