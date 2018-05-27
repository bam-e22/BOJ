//Please don't change class name 'Main'

import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        try (Scanner s = new Scanner(System.in)) {
            int n = s.nextInt();
            int d = s.nextInt();
            int k = s.nextInt();
            int j = s.nextInt();

            //여기부터 작성해 주세요
            int[] arr = new int[n];

            if (d == 1) {

                for (int i = 0; i < n; i++) {

                    arr[i] = i + 1;
                }

            } else if (d == 0) {

                arr[0] = 1;

                for (int i = 1; i < n; i++) {

                    arr[i] = n + 1 - i;
                }
            }

            int startIdx = 0;
            int idx = 0;
            int size = n;

            while (size != 1) {

                idx = startIdx;
                for (int i = 0; i < k; i++) {

                    idx++;
                    idx %= n;

                    if (arr[idx] == -1) {
                        i--;
                    }
                }

                startIdx = idx;
                arr[idx] = -1;
                size--;

                k += j;
            }

            for (int i = 0; i < n; i++) {

                if (arr[i] != -1) {

                    System.out.println(arr[i]);
                    return;
                }
            }
        }
    }
}
