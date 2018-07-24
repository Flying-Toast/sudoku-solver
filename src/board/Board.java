package board;

public class Board {

	private final int SIZE;
	private Tile[] state;

	public final int getSize() {
		return (SIZE);
	}

	private Tile[] getRow(int row) {//returns the specified row
		Tile[][] rows = new Tile[SIZE][SIZE];
		for (int i = 0; i < (int) Math.pow(SIZE, 2); i += SIZE) {// convert one dimensional state array to 2D rows array
			for (int j = 0; j < SIZE; j++) {
				rows[i / SIZE][j] = state[i + j];
			}
		}
		return (rows[row]);
	}

	private Tile[] getColumn(int column) {//returns the specified column
		Tile[][] columns = new Tile[SIZE][SIZE];
		for (int j = 0; j < SIZE; j++) {// convert one dimensional state array to 2D columns array
			for (int i = 0; i < (int) Math.pow(SIZE, 2); i += SIZE) {
				columns[j][i / SIZE] = state[i + j];
			}
		}
		return (columns[column]);
	}

	private Tile[] getBox(int box) {//box is a 3x3 grid on the board, box index ("box" parameter) is from left to right, top to bottom
		Tile[][] boxes = new Tile[SIZE][(int) Math.sqrt(SIZE)];
		//TODO: get box
		return (boxes[box]);
	}

	public Tile[] getRowByTileBoardIndex() {
		return();//TODO: implement
	}

	public Tile[] getColumnByTileBoardIndex() {
		return();//TODO: implement
	}

	public Tile[] getBoxByTileBoardIndex() {
		return();//TODO: implement
	}

	public Board(int size, int[] startingState) {
		//startingState is one dimensional array with all tile states, from left to right, top to bottom. empty tiles are 0
		this.SIZE = size;
		this.state = new Tile[SIZE * SIZE];

		for (int i = 0; i < startingState.length; i++) {//create tiles from ints in startingState
			state[i] = new Tile(SIZE, startingState[i], i);
		}

		System.out.println(getBox(1));//remove this line

	}

}
