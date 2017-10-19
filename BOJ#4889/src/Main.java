import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int idx = 0;

        while (true) {

            idx++;

            char[] input = br.readLine().toCharArray();
            if (input[0] == '-') break;

            System.out.println(idx + ". " + changeVaildString(input));
        }
    }

    static int changeVaildString(char[] strArr) {

        int length = strArr.length;

        int changeCnt = 0;
        int leftCnt = 0;
        int rightCnt = 0;

        for (int i = 0; i < length; i++) {

            if (strArr[i] == '{') {

                leftCnt++;

                if (leftCnt > length / 2) {

                    strArr[i] = '}';

                    leftCnt--;
                    rightCnt++;
                    changeCnt++;
                }

            } else if (strArr[i] == '}') {

                rightCnt++;

                if (rightCnt > leftCnt) {

                    strArr[i] = '{';

                    rightCnt--;
                    leftCnt++;
                    changeCnt++;
                }
            }
        }

        // System.out.println(new String(strArr));

        return changeCnt;
    }
}

