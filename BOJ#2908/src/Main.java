import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(reverseString(st.nextToken()));
        int b = Integer.parseInt(reverseString(st.nextToken()));


        System.out.println(a < b ? b : a);
    }

    static String reverseString(String text) {

        return (new StringBuilder(text)).reverse().toString();
    }
}