import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] card = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {

            card[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        // Solve
        Arrays.sort(card);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {

            int num = Integer.parseInt(st.nextToken());

            //int ret = Arrays.binarySearch(card, num);
            boolean ret = binarySearch(card, num);

            bw.write(ret? "1 " : "0 ");
        }

        bw.flush();

        br.close();
        bw.close();
    }

    static boolean binarySearch(int[] card, int target) {

        int left = 0;
        int right = card.length - 1;
        int mid;

        while(left <= right) {

            mid = (left + right) / 2;

            if (card[mid] < target) {

                left = mid + 1;
            } else if (card[mid] > target) {

                right = mid - 1;
            } else {

                return true;
            }
        }

        return false;
    }
}
