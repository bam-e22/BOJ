import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ#2581 소수
 * https://www.acmicpc.net/problem/2581
 */

public class Main {

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int M = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        boolean[] primeNumArr = new boolean[N + 1];

        // 초기화
        for (int i = 0; i <= N; i++) {

            primeNumArr[i] = true;
        }
        primeNumArr[1] = false;

        // 에라스토테네스의 체
        for (int i = 2; i <= N; i++) {

            for (int j = i; i * j <= N; j++) {

                primeNumArr[i * j] = false;
            }
        }

        int sum = 0;
        int firstPrimeNum = 0;
        boolean ret = false;
        for (int i = M; i <= N; i++) {

            if (primeNumArr[i]) {

                if (!ret) {

                    firstPrimeNum = i;
                    ret = true;
                }
                sum += i;
            }
        }

        if (!ret) {

            System.out.println(-1);
        } else {

            System.out.println(sum + "\n" + firstPrimeNum);
        }
    }
}
