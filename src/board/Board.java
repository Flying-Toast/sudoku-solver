package board;

public class Board {

	public static final int SIZE = 9;
	private int[][] tiles;

	public Board(int[] startingState) {
		/*
		 * startingState is one dimensional array with all tile states, from left to
		 * right, top to bottom. empty tiles are 0
		 */
		this.tiles = new int[SIZE][SIZE];

		for (int i = 0; i < (int) Math.pow(SIZE, 2); i += SIZE) {// convert one dimensional input array to 2D tiles array
			for (int j = 0; j < SIZE; j++) {
				tiles[i / SIZE][j] = startingState[i + j];
			}
		}

	}

}
