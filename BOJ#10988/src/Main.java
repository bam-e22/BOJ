import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str = br.readLine().toCharArray();
        int length = str.length;

        for (int i = 0; i < length; i++) {

            if (str[i] != str[length - 1 - i]) {

                System.out.println(0);
                return;
            }

            if (i >= length - 1 -i) break;
        }

        System.out.println(1);
    }
}
