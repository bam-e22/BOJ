import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ#1041 주사위
 * https://www.acmicpc.net/problem/1041
 */

public class Main {

	public static void main(String[] args) throws IOException {

		Dice dice = new Dice();
		dice.calculateSum();
		System.out.println(dice.getSumOf5());
	}

}

class Dice {
	private static final int A = 0;
	private static final int B = 1;
	private static final int C = 2;
	private static final int D = 3;
	private static final int E = 4;
	private static final int F = 5;
	private static final int SIZE = 6;

	private static int[][] relationship = new int[SIZE][SIZE];
	private int[] value = new int[SIZE];
	private long numOfDice;
	private long minOf1 = Integer.MAX_VALUE;
	private long minOf2 = Integer.MAX_VALUE;
	private long minOf3 = Integer.MAX_VALUE;
	private long maxOf1 = 0;
	private long sumOf5 = 0;

	static {

		relationship[A][B] = 1;
		relationship[A][C] = 1;
		relationship[A][D] = 1;
		relationship[A][E] = 1;

		relationship[B][A] = 1;
		relationship[B][C] = 1;
		relationship[B][D] = 1;
		relationship[B][F] = 1;

		relationship[C][A] = 1;
		relationship[C][B] = 1;
		relationship[C][E] = 1;
		relationship[C][F] = 1;

		relationship[D][A] = 1;
		relationship[D][B] = 1;
		relationship[D][E] = 1;
		relationship[D][F] = 1;

		relationship[E][A] = 1;
		relationship[E][C] = 1;
		relationship[E][D] = 1;
		relationship[E][F] = 1;

		relationship[F][B] = 1;
		relationship[F][C] = 1;
		relationship[F][D] = 1;
		relationship[F][E] = 1;
	}

	public Dice() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		numOfDice = Long.parseLong(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < SIZE; i++) {

			value[i] = Integer.parseInt(st.nextToken());
		}

		getMinOf1();
		getMinOf2();
		getMinOf3();
	}

	private void getMinOf1() {

		for (int i = 0; i < SIZE; i++) {

			if (maxOf1 < value[i]) {

				maxOf1 = value[i];
			}

			if (minOf1 > value[i]) {

				minOf1 = value[i];
			}
		}
	}

	private void getMinOf2() {
		int temp;

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {

				if (relationship[i][j] == 1) {
					temp = value[i] + value[j];

					if (minOf2 > temp) {
						minOf2 = temp;
					}
				}
			}
		}
	}

	private void getMinOf3() {

		int temp = 0;

		for (int i = 0; i < SIZE; i++) {

			temp = Integer.MAX_VALUE;
			for (int j = 0; j < SIZE; j++) {

				if (relationship[i][j] == 1) {
					for (int k = 0; k < SIZE; k++) {

						if (relationship[j][k] == 1 && relationship[i][k] == 1) {
							temp = value[i] + value[j] + value[k];

							if (minOf3 > temp) {
								minOf3 = temp;
							}

						}

					}
				}

			}

		}

	}

	public void calculateSum() {

		if (numOfDice == 1) {

			for (int i = 0; i < SIZE; i++) {

				sumOf5 += value[i];
			}
			sumOf5 -= maxOf1;

		} else {

			sumOf5 = minOf3 * 4 + minOf2 * (4 * (numOfDice - 2) + 4 * (numOfDice - 1))
					+ minOf1 * ((numOfDice - 2) * (numOfDice - 2) + 4 * (numOfDice - 2) * (numOfDice - 1));
		}
	}

	public long getSumOf5() {

		return sumOf5;
	}
}
