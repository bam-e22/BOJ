import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int theta1 = Integer.parseInt(st.nextToken());
        int theta2 = Integer.parseInt(st.nextToken());

        if ((theta1 * 12) % 360 != theta2) System.out.println("X");
        else System.out.println("O");
    }
}
