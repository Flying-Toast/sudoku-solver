package board;

public class Board {

	private final int SIZE;

	private int[][] rows;
	private int[][] columns;

	public final int getSize() {
		return (SIZE);
	}

	public int[] getRow(int row) {//returns the specified row
		return (rows[row]);
	}

	public int[] getColumn(int column) {//returns the specified column
		return (columns[column]);
	}

	public Board(int size, int[] startingState) {
		//startingState is one dimensional array with all tile states, from left to right, top to bottom. empty tiles are 0
		this.SIZE = size;
		this.rows = new int[SIZE][SIZE];
		this.columns = new int[SIZE][SIZE];

		for (int i = 0; i < (int) Math.pow(SIZE, 2); i += SIZE) {// convert one dimensional input array to 2D rows array
			for (int j = 0; j < SIZE; j++) {
				rows[i / SIZE][j] = startingState[i + j];
			}
		}

		for (int j = 0; j < SIZE; j++) {// convert one dimensional input array to 2D columns array
			for (int i = 0; i < (int) Math.pow(SIZE, 2); i += SIZE) {
				columns[j][i / SIZE] = startingState[i + j];
			}
		}

	}

}
