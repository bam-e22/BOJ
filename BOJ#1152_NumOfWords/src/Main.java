import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ#1152 단어의 개수
 * https://www.acmicpc.net/problem/1152
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        input = input.trim();

        String[] words = input.split("\\p{Blank}+");

        if (words.length == 1 && words[0].equals("")) {

            System.out.println(0);
        } else {

            System.out.println(words.length);
        }
    }
}
