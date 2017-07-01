import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ#9663 N-Queen
 * https://www.acmicpc.net/problem/9663
 */

public class Main {

	public static void main(String[] args) throws IOException {

		int N;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		NQueen queen = new NQueen(N);
		queen.findSolution();
		System.out.println(queen.getNumOfSolution());
	}
}

class NQueen {

	private int[] column;
	private int size;
	private int numOfSolution;

	public NQueen(int N) {

		this.size = N;
		this.column = new int[N];
		this.numOfSolution = 0;

		clearColumn();
	}

	private void backTracking(int row) {

		if (row >= size) {

			numOfSolution++;
			return;
		}

		for (int col = 0; col < size; col++) {

			if (isSafePlace(row, col)) {

				column[row] = col;
				backTracking(row + 1);
				column[row] = -1;
			}
		}

	}

	private boolean isSafePlace(int targetRow, int targetCol) {

		if (targetRow < 1) {

			return true;
		}
		for (int row = 0; row < targetRow; row++) {

			if (column[row] == targetCol || targetRow - row == Math.abs(targetCol - column[row])) {

				return false;
			}
		}

		return true;
	}

	public void findSolution() {

		backTracking(0);
	}

	private void clearColumn() {

		for (int i = 0; i < size; i++) {

			column[i] = -1;
		}
	}

	public int getNumOfSolution() {

		return numOfSolution;
	}

}
