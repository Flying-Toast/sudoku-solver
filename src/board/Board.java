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

	public Tile[] getState() {
		return (state);
	}

	public Tile[] getRow(int rowIndex) {//returns the specified row (zero based rowIndex)
		Tile[] row = new Tile[SIZE];

		for (int i = 0; i < SIZE; i++) {
			row[i] = getState()[(rowIndex * SIZE) + i];
		}

		return (row);
	}

	public Tile[] getColumn(int columnIndex) {//returns the specified column (zero based columnIndex)
		Tile[] column = new Tile[SIZE];

		for (int i = 0; i < SIZE; i++) {
			column[i] = getState()[columnIndex + (SIZE * i)];
		}

		return (column);
	}

	public Tile[] getBox(int boxIndex) {
		int fullBoxRows = (boxIndex - (boxIndex % BOX_SIZE)) / BOX_SIZE;
		int tilesForFullBoxRows = (BOX_SIZE * SIZE) * fullBoxRows;
		int boxNumberInRow = (boxIndex % BOX_SIZE) + 1;
		int tilesForBoxNumberInRow = BOX_SIZE * boxNumberInRow;
		int tileIndexInBox = (tilesForFullBoxRows + tilesForBoxNumberInRow) - 1;

		return (getBoxByTileBoardIndex(tileIndexInBox));
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
				box[localTileIndex] = state[indexOfFirstTileInBox + tileInRow + (row * SIZE)];
				localTileIndex++;//localTileIndex: zero based index of the tile in the box that is currently being inserted, with the zero index being the top left tile in the box
			}
		}

		return (box);
	}

	public Tile getTileByBoardIndex(int tileIndex) {
		return (state[tileIndex]);
	}

	public boolean isSolved() {
		for (int i = 0; i < state.length; i++) {//return false if any tile has value 0
			if (state[i].getValue() == 0) {
				return (false);
			}
		}

		return (true);
	}

	public void printState() {
		for (int i = 0; i < SIZE; i++) {
			Tile[] row = getRow(i);
			for (int j = 0; j < SIZE; j++) {
				System.out.print(row[j].getValue());
				System.out.print(' ');
			}
			System.out.println();
		}
		System.out.println();
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
