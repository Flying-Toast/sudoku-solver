package solver;

import board.Board;

public class Solve {

	public static void main(String[] args) {
		Board board = new Board(9, new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 5, 9, 0, 4, 7, 8, 0, 3, 7, 9, 0, 8, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 1, 0, 0, 9, 0, 0, 0, 0, 2, 5, 0, 0, 0, 0, 0, 8, 0, 4, 0, 0, 6, 7, 0, 1, 4, 8, 0, 0, 0, 0, 0, 0, 3, 6, 0, 0, 2, 4, 0, 0 });// temporary board for testing. TODO: add way of inputing board at runtime through GUI

		Solver solver = new Solver(board);
		solver.solve();
	}

}