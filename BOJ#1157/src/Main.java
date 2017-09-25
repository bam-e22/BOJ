import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str = br.readLine().toUpperCase().toCharArray();
        int[] cntArr = new int[26];
        int maxCnt = 0;
        char answer = '?';

        for (char c : str) {

            cntArr[c - 'A']++;

            if (maxCnt < cntArr[c - 'A']) {

                maxCnt = cntArr[c - 'A'];
                answer = c;
            }
        }

        Arrays.sort(cntArr);
        if (cntArr[24] == cntArr[25]) System.out.println("?");
        else System.out.println(answer);
    }
}
