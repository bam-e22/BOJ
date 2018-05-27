import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        if (!checkArithmetic(a, b, c)) {

            checkArithmeticReverse(a, b, c);
        }
    }

    static boolean checkArithmetic(int a, int b, int ans) {

        if (a + b == ans) {

            System.out.println(a + "+" + b + "=" + ans);
            return true;
        } else if (a - b == ans) {

            System.out.println(a + "-" + b + "=" + ans);
            return true;
        } else if (a * b == ans) {

            System.out.println(a + "*" + b + "=" + ans);
            return true;
        } else if (a / b == ans) {

            System.out.println(a + "/" + b + "=" + ans);
            return true;
        }

        return false;
    }

    static boolean checkArithmeticReverse(int ans, int a, int b) {

        if (ans == a + b) {

            System.out.println(ans + "=" + a + "+" + b);
            return true;
        } else if (ans == a - b) {

            System.out.println(ans + "=" + a + "-" + b);
            return true;
        } else if (ans == a * b) {

            System.out.println(ans + "=" + a + "*" + b);
            return true;
        } else if (ans == a / b) {

            System.out.println(ans + "=" + a + "/" + b);
            return true;
        }

        return false;
    }
}
