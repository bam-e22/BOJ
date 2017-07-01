import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * BOJ#1339 단어 수학
 * https://www.acmicpc.net/problem/1339
 */

// 풀이 #1. 수학으로 풀기
public class Main {

    public static void main(String[] args) throws IOException {

        int N; // 단어의 개수
        char[] alphabet; // 입력받은 단어의 총 알파벳 배열
        Integer[] weight; // 알파벳들의 가중치(합)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        alphabet = new char[10];
        weight = new Integer[10];

        Arrays.fill(alphabet, '0');
        Arrays.fill(weight, 0);

        // 단어를 하나씩 입력 받는다
        for (int i = 0; i < N; i++) {

            String numStr = br.readLine();

            int w = 1;
            for (int j = numStr.length() - 1; j >= 0; j--) {

                char numChar = numStr.charAt(j);

                for (int k = 0; k < 10; k++) {

                    if (alphabet[k] == numChar) {

                        weight[k] += w;
                        break;
                    } else if (alphabet[k] == '0') {

                        alphabet[k] = numChar;
                        weight[k] = w;
                        break;
                    }
                }

                w *= 10;
            }
        }

        Arrays.sort(weight);

        int sum = 0;
        for (int i = 9; i >= 0; i--) {

            sum += i * weight[i];
        }

        System.out.println(sum);
    }
}
