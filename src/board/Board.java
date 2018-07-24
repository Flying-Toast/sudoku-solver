package board;

public class Board {

	private final int SIZE;

	private Tile[][] rows;
	private Tile[][] columns;
	private Tile[][][] boxes;

	public final int getSize() {
		return (SIZE);
	}

	public Tile[] getRow(int row) {//returns the specified row
		return (rows[row]);
	}

	public Tile[] getColumn(int column) {//returns the specified column
		return (columns[column]);
	}

	public Board(int size, int[] startingState) {
		//startingState is one dimensional array with all tile states, from left to right, top to bottom. empty tiles are 0
		this.SIZE = size;
		this.rows = new Tile[SIZE][SIZE];
		this.columns = new Tile[SIZE][SIZE];
		this.boxes = new Tile[SIZE][(int) Math.sqrt(SIZE)][(int) Math.sqrt(SIZE)];

		Tile[] startingTileStates = new Tile[SIZE * SIZE];

		for (int i = 0; i < startingState.length; i++) {//create tiles from ints in startingState
			startingTileStates[i] = new Tile(SIZE, startingState[i]);
		}

		for (int i = 0; i < (int) Math.pow(SIZE, 2); i += SIZE) {// convert one dimensional input array to 2D rows array
			for (int j = 0; j < SIZE; j++) {
				rows[i / SIZE][j] = startingTileStates[i + j];
			}
		}

		for (int j = 0; j < SIZE; j++) {// convert one dimensional input array to 2D columns array
			for (int i = 0; i < (int) Math.pow(SIZE, 2); i += SIZE) {
				columns[j][i / SIZE] = startingTileStates[i + j];
			}
		}

	}

}
