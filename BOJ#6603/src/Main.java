import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            String inputLine = br.readLine();

            if (inputLine.equals("0")) break;

            StringTokenizer st = new StringTokenizer(inputLine);

            int k = Integer.parseInt(st.nextToken());

            int[] S = new int[k];
            for (int i = 0; i < k; i++) {

                S[i] = Integer.parseInt(st.nextToken());
            }

            printCombination(k, S, 0, 6, "");
            System.out.println();
        }
    }

    static void printCombination(int k, int[] S, int idx, int N, String s) {

        if (N == 0) {

            System.out.println(s);
        } else if (idx >= k) {

            return;
        } else {

            // 뽑는 경우
            printCombination(k, S, idx + 1, N - 1, s + S[idx] + " ");

            // 뽑지 않는 경우
            printCombination(k, S, idx + 1, N, s);

        }
    }
}
