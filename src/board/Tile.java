package board;

class Tile {
	private boolean[] possibleValues;
	private boolean[] impossibleValues;
	private final int minValue = 1;
	private final int maxValue;
	private int value = 0;
	private final int boardSize;

	public boolean[] getPossibleValues() {
		return (possibleValues);
	}

	public boolean[] getImpossibleValues() {
		return (impossibleValues);
	}

	public int getValue() {
		return (value);
	}

	public Tile(final int boardSize, int value) {//pass 0 as value for empty tile.
		this.boardSize = boardSize;
		this.possibleValues = new boolean[boardSize];
		this.impossibleValues = new boolean[boardSize];
		this.maxValue = boardSize;
		this.value = value;

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
