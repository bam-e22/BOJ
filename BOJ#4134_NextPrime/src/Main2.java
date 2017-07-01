import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {

	/*
	 * 소수 정리에 따르면 
	 * 1. N이 충분히 큰 수라면 N 근처에 존재하는 소수들 사이의 평균 간격은 대략적으로 lnN이다
	 * 2. 어떤 큰 수 N에 가까운 정수 하나를 무작위로 고를때, 그 정수가 소수일 확률은 1/lnN에 근사
	 * 3. 소수의 분포는 더 큰 수로 갈수록 적어진다
	 */

	// "n이 소수인지 판별하려는 수 일때, n의 양의 제곱근 보다 작은 소수로 나누어 떨어지지 않으면 n은 소수이다."
	// 따라서, 4*10^9 이상의 수에 대해 소수임을 판별하려면 sqrt(4*10^9) = 63,245 보다 작은 소수들로 나누자.
	// 소수 정리에 따라 4*10^9 근처의 소수의 간격은 ln(4*10^9)이므로
	// 즉 4*10^9이 소수가 아니라 해도 4*10^9 + ln(4*10^9) 이내에는 소수가 존재한다.
	// 넉넉잡아 63,300 크기의 배열을 선언하자.
	// static final int ARR_SIZE = 63300;
	// static int[] primeNum = new int[ARR_SIZE];

	public static void main(String[] args) throws IOException {

		final int ARR_SIZE = 63300;
		int[] primeNum = new int[ARR_SIZE];

		int T;
		long n;
		boolean isPrime = false;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < ARR_SIZE; i++) {
			primeNum[i] = 1;
		}
		// 소수 배열 구하기 - 에라스토테네스의 체
		// 1이면 소수이고, 0이면 소수가 아니다.
		primeNum[0] = 0;
		primeNum[1] = 0;

		for (int i = 2; i < ARR_SIZE; i++) {
			for (int j = 2; i * j < ARR_SIZE; j++) {

				primeNum[i * j] = 0;
			}
		}

		// 입력 받은 수 n에 대하여 n보다 크거나 같은 소수 중 가장 작은 소수를 구해보자.
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {

			n = Long.parseLong(br.readLine());

			if (n <= 1) {

				System.out.println(2);
			} else {


				for (long i = n;; i++) {

					int m = (int) Math.sqrt(i);
					isPrime = true;

					for (int j = 1; j <= m; j++) {

						if (primeNum[j] == 1) {

							if (i % j == 0) {

								isPrime = false;
								break;
							}
						}
					}

					if (isPrime) {
						System.out.println(i);
						break;
					}
				}
			}

		}
	}
}
