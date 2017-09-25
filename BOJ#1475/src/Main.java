import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] input = br.readLine().toCharArray();
        int[] cntArr = new int[10];
        int maxCnt = 0;

        for (char c : input) cntArr[c - '0']++;

        for (int i = 0; i < 10; i++) {

            if (i == 6 || i == 9) continue;

            maxCnt = maxCnt < cntArr[i] ? cntArr[i] : maxCnt;
        }

        int cnt = (int) Math.ceil(((double)cntArr[6] + cntArr[9])/2.0);

        maxCnt = maxCnt < cnt ? cnt : maxCnt;
        System.out.println(maxCnt);
    }
}
