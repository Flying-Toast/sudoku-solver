package solver;

import board.Board;

public class Solve {

	public static void main(String[] args) {
		Board board = new Board(9, new int[] { 0, 0, 0, 0, 0, 8, 0, 0, 4, 0, 8, 4, 0, 1, 6, 0, 0, 0, 0, 0, 0, 5, 0, 0, 1, 0, 0, 1, 0, 3, 8, 0, 0, 9, 0, 0, 6, 0, 8, 0, 0, 0, 4, 0, 3, 0, 0, 2, 0, 0, 9, 5, 0, 1, 0, 0, 7, 0, 0, 2, 0, 0, 0, 0, 0, 0, 7, 8, 0, 2, 6, 0, 2, 0, 0, 3, 0, 0, 0, 0, 0 });// temporary board for testing. TODO: add way of inputing board at runtime through GUI
		board.printState();

		Solver solver = new Solver(board);
		solver.solve();
	}

}