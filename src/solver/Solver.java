package solver;

import board.Board;
import board.Tile;

public class Solver {
	private Board board;

	public Solver(Board board) {
		this.board = board;
	}

	public void solve() {

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

}
