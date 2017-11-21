import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int ans = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        combination(0, 1);
        combination(0, 2);

        System.out.println(ans);
    }

    static void combination(int depth, int num) {

        if (depth == N - 1) {

            if (num != 0 && num % 3 == 0) {

                ans++;
            }

            return;
        }

        for (int i = 0; i < 3; i++) {

            num *= 10;
            num += i;

            combination(depth + 1, num);

            num -= i;
            num /= 10;
        }
    }
}
