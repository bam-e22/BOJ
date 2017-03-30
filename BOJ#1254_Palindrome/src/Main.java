import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ1254 팰린드롬 만들기
 * https://www.acmicpc.net/problem/1254
 */

public class Main {

    public static void main(String[] args) throws IOException {

        char[] S, R;
        int len;
        int cnt = 1001;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        S = input.toCharArray();
        R = new StringBuffer(input).reverse().toString().toCharArray();

        len = S.length;

        for (int i = 0; i < len; i++) {

            int tempCnt = 0;
            boolean flag = true;

            for (int j = 0; j < len - i; j++) {

                if (S[i + j] == R[j]) {

                    tempCnt++;
                } else {

                    flag = false;
                    break;
                }
            }

            if (tempCnt > 0 && flag) {

                tempCnt = len - tempCnt;

                cnt = cnt > tempCnt ? tempCnt : cnt;
            }
        }

        System.out.println(len + cnt);
    }
}
