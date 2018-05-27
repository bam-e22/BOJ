import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        while(st.hasMoreTokens()) {

            String next = st.nextToken();

            if (next.equals("src=\"/i/files/gf.png\"><a")) {

                System.out.println("0");
            } else if (next.equals("src=\"/i/files/gm.png\"><a")) {

                System.out.println("1");
            }
        }

    }
}