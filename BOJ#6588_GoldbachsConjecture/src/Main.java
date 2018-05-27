import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ#6588 골드바흐의 추측
 * https://www.acmicpc.net/problem/6588
 */
public class Main {

    static final String WRONG_MSG = "Goldbach's conjecture is wrong.";

    public static void main(String[] args) throws IOException {

        int n = 1;
        int[] primeNumArr = new int[1_000_001];
        int index;

        for (int i = 0; i < 1000001; i++) {

            primeNumArr[i] = 1;
        }
        primeNumArr[0] = 0;
        primeNumArr[1] = 0;
        index = 1;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (n != 0) {

            n = Integer.parseInt(br.readLine());

            // find prime number
            if (n > index) {

                index = findPrimeNum(primeNumArr, n, index);
            }

            // n보다 같거나 작은 소수 b를 구하고, n-b = a, a가 소수인지 판별
            int b = n + 1;
            while (b > 0) {

                if (primeNumArr[--b] == 1) {

                    break;
                }
            }

            System.out.println(b);
        }


    }

    static int findPrimeNum(int[] primeNumArr, int n, int index) {

        for (int i = index; i <= n; i++) {

            for (int j = 2; i * j <= n; j++) {

                primeNumArr[i * j] = 0;
            }
        }

        return index;
    }


}
