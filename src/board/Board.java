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
		Tile[][] rows = new Tile[SIZE][SIZE];//TODO: only build row that is needed, not entire board.
		for (int i = 0; i < (int) Math.pow(SIZE, 2); i += SIZE) {// convert one dimensional state array to 2D rows array
			for (int j = 0; j < SIZE; j++) {
				rows[i / SIZE][j] = state[i + j];
			}
		}
		return (rows[rowIndex]);
	}

	private Tile[] getColumn(int columnIndex) {//returns the specified column (zero based columnIndex)
		Tile[][] columns = new Tile[SIZE][SIZE];//TODO: only build column that is needed, not entire board.
		for (int j = 0; j < SIZE; j++) {// convert one dimensional state array to 2D columns array
			for (int i = 0; i < (int) Math.pow(SIZE, 2); i += SIZE) {
				columns[j][i / SIZE] = state[i + j];
			}
		}
		return (columns[columnIndex]);
	}

	public Tile[] getRowByTileBoardIndex(int tileIndex) {//returns the row that contains the tile of the given boardIndex (i.e TileInstance.getBoardIndex())
		Tile[] row = new Tile[SIZE];
		row = getRow((tileIndex - (tileIndex % SIZE)) / SIZE);
		return (row);
	}

	public Tile[] getColumnByTileBoardIndex(int tileIndex) {
		Tile[] column = new Tile[SIZE];
		column = getColumn(tileIndex % SIZE);
		return (column);
	}

	public Tile[] getBoxByTileBoardIndex(int tileIndex) {//box is a 3x3 grid (on a standard 9x9) board, box indices are from left to right, top to bottom, where each box grid has one index
		Tile[] box = new Tile[SIZE];
		int indexOfLeftmostTileInBox = (tileIndex - (tileIndex % BOX_SIZE)); //indexOfLeftmostTileInBox: the boardIndex of the leftmost tile in the box that is in the same row as boxIndex
		int rowOfTileIndex = (tileIndex - ((tileIndex % SIZE))) / SIZE;//the row index of the tile located at tileIndex
		int indexOfFirstTileInBox = indexOfLeftmostTileInBox - (SIZE * (rowOfTileIndex % BOX_SIZE));//the boardIndex of the first tile in the box at boxIndex

		int localTileIndex = 0;
		for (int row = 0; row < BOX_SIZE; row++) {
			for (int tileInRow = 0; tileInRow < BOX_SIZE; tileInRow++) {
				int s = indexOfFirstTileInBox + tileInRow + (row * SIZE);
				int b = localTileIndex;
				box[b] = state[s];
				localTileIndex++;//localTileIndex: zero based index of the tile in the box that is currently being inserted, with the zero index being the top left tile in the box
			}
		}

		return (box);
	}

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
