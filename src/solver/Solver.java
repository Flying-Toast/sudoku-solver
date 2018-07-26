package solver;

import board.Board;
import board.Tile;

public class Solver {
	private Board board;

	public Solver(Board board) {
		this.board = board;
	}

	public void solve() {
		while (!board.isSolved()) {
			markAllTileGroups();
			checkAllGroupsForValues();
		}
		System.out.println("Solved!");
		board.printState();
	}

	private void markRowByTileIndex(int tileIndex) {//marks all tiles in given tile's row as impossible for the value of the given tile
		int tileValue = board.getTileByBoardIndex(tileIndex).getValue();

		if (tileValue == 0) {
			return;
		}

		Tile[] row = board.getRowByTileBoardIndex(tileIndex);

		for (int i = 0; i < row.length; i++) {
			if (row[i].getBoardIndex() == tileIndex) {
				continue;
			}

			row[i].markImpossibleValue(tileValue);
		}
	}

	private void markColumnByTileIndex(int tileIndex) {
		int tileValue = board.getTileByBoardIndex(tileIndex).getValue();

		if (tileValue == 0) {
			return;
		}

		Tile[] column = board.getColumnByTileBoardIndex(tileIndex);

		for (int i = 0; i < column.length; i++) {
			if (column[i].getBoardIndex() == tileIndex) {
				continue;
			}

			column[i].markImpossibleValue(tileValue);
		}
	}

	private void markBoxByTileIndex(int tileIndex) {
		int tileValue = board.getTileByBoardIndex(tileIndex).getValue();

		if (tileValue == 0) {
			return;
		}

		Tile[] box = board.getBoxByTileBoardIndex(tileIndex);

		for (int i = 0; i < box.length; i++) {
			if (box[i].getBoardIndex() == tileIndex) {
				continue;
			}

			box[i].markImpossibleValue(tileValue);
		}
	}

	private void markGroupsByTileIndex(int tileIndex) {//marks all of the tiles that are in the same row, column, and box as the tile at tileIndex
		markRowByTileIndex(tileIndex);
		markColumnByTileIndex(tileIndex);
		markBoxByTileIndex(tileIndex);
	}

	private void markAllTileGroups() {//marks all impossible values for all tiles in the board
		Tile[] boardState = board.getState();

		for (int i = 0; i < boardState.length; i++) {
			markGroupsByTileIndex(boardState[i].getBoardIndex());
		}
	}

	private boolean doesGroupNeedValue(Tile[] group, int value) {
		for (int i = 0; i < group.length; i++) {
			if (group[i].getValue() == value) {
				return (false);
			}
		}

		return (true);
	}

	private void checkAllGroupsForValues() { //checks all boxes, rows, and columns need any value, and if they do, and there is only one spot that value can go, assigns that value
		for (int columnIndex = 0; columnIndex < board.getSize(); columnIndex++) {//check columns
			Tile[] column = board.getColumn(columnIndex);
			for (int value = 1; value <= board.getSize(); value++) {
				if (doesGroupNeedValue(column, value)) {
					int numberOfPossibleLocations = 0;//the number of possible places that the value could go in the group
					int indexOfLocation = -1; //the index of the location that the value has to go. if there are multiple possible places, this value will be overwritten, but this doesnt matter because this value will only be read if there is only one possible location
					for (int i = 0; i < column.length; i++) {
						if (column[i].getPossibleValues()[value - 1] == true) {
							indexOfLocation = i;
							numberOfPossibleLocations++;
						}
					}
					if (numberOfPossibleLocations == 1) {
						column[indexOfLocation].setKnownValue(value);
						markAllTileGroups();
						board.printState();
					}
				}
			}
		}

		for (int rowIndex = 0; rowIndex < board.getSize(); rowIndex++) {//check rows
			Tile[] row = board.getRow(rowIndex);
			for (int value = 1; value <= board.getSize(); value++) {
				if (doesGroupNeedValue(row, value)) {
					int numberOfPossibleLocations = 0;//the number of possible places that the value could go in the group
					int indexOfLocation = -1; //the index of the location that the value has to go. if there are multiple possible places, this value will be overwritten, but this doesnt matter because this value will only be read if there is only one possible location
					for (int i = 0; i < row.length; i++) {
						if (row[i].getPossibleValues()[value - 1] == true) {
							indexOfLocation = i;
							numberOfPossibleLocations++;
						}
					}
					if (numberOfPossibleLocations == 1) {
						row[indexOfLocation].setKnownValue(value);
						markAllTileGroups();
						board.printState();
					}
				}
			}
		}

		for (int boxIndex = 0; boxIndex < board.getSize(); boxIndex++) {//check boxes
			Tile[] box = board.getBox(boxIndex);
			for (int value = 1; value <= board.getSize(); value++) {
				if (doesGroupNeedValue(box, value)) {
					int numberOfPossibleLocations = 0;//the number of possible places that the value could go in the group
					int indexOfLocation = -1; //the index of the location that the value has to go. if there are multiple possible places, this value will be overwritten, but this doesnt matter because this value will only be read if there is only one possible location
					for (int i = 0; i < box.length; i++) {
						if (box[i].getPossibleValues()[value - 1] == true) {
							indexOfLocation = i;
							numberOfPossibleLocations++;
						}
					}
					if (numberOfPossibleLocations == 1) {
						box[indexOfLocation].setKnownValue(value);
						markAllTileGroups();
						board.printState();
					}
				}
			}
		}
	}
}
