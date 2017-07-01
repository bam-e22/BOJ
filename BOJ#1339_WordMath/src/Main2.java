import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

/**
 * BOJ#1339 단어 수학
 * https://www.acmicpc.net/problem/1339
 */

// 풀이 #2. Bactraking
public class Main2 {

    static String[] numStr = new String[10];
    static int maxValue = 0;
    static int N; // 단어의 개수

    public static void main(String[] args) throws IOException {

        HashMap<Character, Integer> map = new HashMap<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // 단어를 하나씩 입력 받는다
        for (int i = 0; i < N; i++) {

            numStr[i] = br.readLine();

            for (int j = 0; j < numStr[i].length(); j++) {

                char numChar = numStr[i].charAt(j);

                map.put(numChar, 0);
            }
        }

        int[] num = new int[map.size()];
        for (int i = 0; i < num.length; i++) {

            num[i] = 9 - i;
        }

        permutation(num, num.length, num.length, 0, map);
        System.out.println(maxValue);
    }

    static void permutation(int[] num, int k, int n, int depth, HashMap<Character, Integer> map) {

        if (depth == k) {

            // 값 대입
            Iterator<Character> it = map.keySet().iterator();
            while (it.hasNext()) {

                map.replace(it.next(), num[--k]);
            }
            // 테스트, Max 값 추출
            checkMaxValue(map);

            //System.out.println(map);

            return;
        }

        for (int i = depth; i < n; i++) {

            swap(num, depth, i);
            permutation(num, k, n, depth + 1, map);
            swap(num, depth, i);
        }
    }

    static void swap(int[] num, int i, int j) {

        int temp = num[i];

        num[i] = num[j];
        num[j] = temp;
    }

    static void checkMaxValue(HashMap<Character, Integer> map) {

        int sum = 0;

        for (int i = 0; i < N; i++) {

            String str = numStr[i];

            int exponent = 1;

            for (int j = str.length() - 1; j >= 0; j--) {

                char alphabet = str.charAt(j);

                sum += map.get(alphabet) * exponent;

                exponent *= 10;
            }
        }

        maxValue = maxValue < sum ? sum : maxValue;
    }
}
