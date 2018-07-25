package board;

public class Board {

	private final int SIZE;
	private final int BOX_SIZE;
	private Tile[] state;

	public int getSize() {
		return (SIZE);
	}

	public int getBoxSize() {
		return (BOX_SIZE);
	}

	private Tile[] getRow(int rowIndex) {//returns the specified row (zero based rowIndex)
		Tile[][] rows = new Tile[SIZE][SIZE];
		for (int i = 0; i < (int) Math.pow(SIZE, 2); i += SIZE) {// convert one dimensional state array to 2D rows array
			for (int j = 0; j < SIZE; j++) {
				rows[i / SIZE][j] = state[i + j];
			}
		}
		return (rows[rowIndex]);
	}

	private Tile[] getColumn(int columnIndex) {//returns the specified column (zero based columnIndex)
		Tile[][] columns = new Tile[SIZE][SIZE];
		for (int j = 0; j < SIZE; j++) {// convert one dimensional state array to 2D columns array
			for (int i = 0; i < (int) Math.pow(SIZE, 2); i += SIZE) {
				columns[j][i / SIZE] = state[i + j];
			}
		}
		return (columns[columnIndex]);
	}

	//		public Tile[] getBox(int boxIndex) {//box is a 3x3 grid on the board, box indices are from left to right, top to bottom, where each box grid has one index
	//			//TODO: implement
	//		}

	public Tile[] getRowByTileBoardIndex(int tileIndex) {
		Tile[] row = new Tile[SIZE];

		return (row);
	}

	public Tile[] getColumnByTileBoardIndex(int tileIndex) {
		Tile[] column = new Tile[SIZE];
		column = getColumn(tileIndex % SIZE);
		return (column);
	}

	//		public Tile[] getBoxByTileBoardIndex() {
	//			//TODO: implement
	//		}

	public Board(int size, int[] startingState) {
		//startingState is one dimensional array with all tile states, from left to right, top to bottom. empty tiles are 0
		this.SIZE = size;
		this.BOX_SIZE = (int) Math.sqrt(SIZE);
		this.state = new Tile[SIZE * SIZE];

		for (int i = 0; i < startingState.length; i++) {//create tiles from ints in startingState
			state[i] = new Tile(SIZE, startingState[i], i);
		}

	}

}
