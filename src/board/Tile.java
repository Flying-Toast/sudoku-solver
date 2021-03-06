package board;

public class Tile {
	private boolean[] possibleValues;
	private boolean[] impossibleValues;
	private final int boardIndex;
	private int value = 0;
	private final int BOARD_SIZE;

	public boolean[] getPossibleValues() {
		return (possibleValues);
	}

	public boolean[] getImpossibleValues() {
		return (impossibleValues);
	}

	public void markImpossibleValue(int valueToMark) {//marks the passed value as impossible, and also updates possibleValues, and checks if value is known
		impossibleValues[valueToMark - 1] = true;
		possibleValues[valueToMark - 1] = false;
		this.checkIfValueIsKnown();
	}

	public void setKnownValue(int value) {
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (i + 1 != value) {
				markImpossibleValue(i + 1);
			}
		}
		if (this.value == 0) {
			this.value = value;
		}
	}

	private void checkIfValueIsKnown() {
		if (value != 0) {
			return;
		}

		int occurrences = 0;
		int knownValue = 0;

		for (int i = 0; i < possibleValues.length; i++) {
			if (possibleValues[i] == true) {
				knownValue = i + 1;
				occurrences++;
			}
		}

		if (occurrences == 1) {
			value = knownValue;
		}
	}

	public int getValue() {
		return (value);
	}

	public int getBoardIndex() {
		return (boardIndex);
	}

	public Tile(final int boardSize, int value, int boardIndex) {//pass 0 as value for empty tile.
		this.possibleValues = new boolean[boardSize];
		this.impossibleValues = new boolean[boardSize];
		this.value = value;
		this.boardIndex = boardIndex;
		this.BOARD_SIZE = boardSize;

		if (value == 0) {
			for (int i = 0; i < boardSize; i++) {
				impossibleValues[i] = false;
				possibleValues[i] = true;
			}
		} else {
			for (int i = 0; i < boardSize; i++) {
				if (i + 1 == value) {
					impossibleValues[i] = false;
					possibleValues[i] = true;
				} else {
					impossibleValues[i] = true;
					possibleValues[i] = false;
				}
			}
		}

	}
}
