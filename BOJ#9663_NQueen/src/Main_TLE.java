import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * BOJ#9663 N-Queen
 * https://www.acmicpc.net/problem/9663
 */

public class Main_TLE {

	public static void main(String[] args) throws IOException {

		int N;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		ChessBoard chessboard = new ChessBoard(N);
		chessboard.findSolution();

		System.out.println(chessboard.getNumOfSoltion());
	}

}

class ChessBoard {
	private Solution solution;
	private int size;
	private int numOfSolution;

	public ChessBoard(int n) {

		this.solution = new Solution();
		this.size = n;
		this.numOfSolution = 0;
	}

	private void backTracking(int row) {

		if (row >= size) {

			numOfSolution++;
			return;
		}

		for (int col = 0; col < size; col++) {

			if (row == 0 || solution.isSafePlace(row, col)) {
				Queen queen = new Queen(row, col);
				solution.addQueen(queen);
				backTracking(row + 1);
				solution.removeQueen(queen);
			}

		}

	}

	public void findSolution() {

		backTracking(0);
	}

	public int getNumOfSoltion() {

		return numOfSolution;
	}

}

class Solution {

	// private ArrayList<Integer> row = new ArrayList<Integer>();
	private ArrayList<Integer> col = new ArrayList<Integer>();
	private ArrayList<Integer> diagLeft = new ArrayList<Integer>();
	private ArrayList<Integer> diagRight = new ArrayList<Integer>();


	public Solution() {

	}

	public boolean isSafePlace(int x, int y) {

		
/*		if (row.contains(x)) {
		  
		  return false; 
		}
		 */
		if (col.contains(y)) {

			return false;
		}
		if (diagLeft.contains(x - y)) {

			return false;
		}
		if (diagRight.contains(x + y)) {

			return false;
		}

		return true;
	}

	public void addQueen(Queen queen) {

		//row.add(new Integer(queen.getX()));
		col.add(new Integer(queen.getY()));
		diagLeft.add(new Integer(queen.getX() - queen.getY()));
		diagRight.add(new Integer(queen.getX() + queen.getY()));
		
	}

	public void removeQueen(Queen queen) {

		//row.remove(new Integer(queen.getX()));
		col.remove(new Integer(queen.getY()));
		diagLeft.remove(new Integer(queen.getX() - queen.getY()));
		diagRight.remove(new Integer(queen.getX() + queen.getY()));
	}

}

class Queen {

	private int x, y;

	public Queen(int x, int y) {

		this.setX(x);
		this.setY(y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}