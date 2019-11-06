public class Queen {
    private final int row;
    private final int column;

    public Queen(int row, int column) throws IllegalArgumentException {
        Queen.throwIfOutOfBounds(row, column);
        this.row = row;
        this.column = column;
    }

    private static void throwIfOutOfBounds(int row, int column) throws IllegalArgumentException {
        if (row < 0) {
            throw new IllegalArgumentException("Queen position must have positive row.");
        } else if (column < 0) {
            throw new IllegalArgumentException("Queen position must have positive column.");
        } else if (row > 7) {
            throw new IllegalArgumentException("Queen position must have row <= 7.");
        } else if (column > 7) {
            throw new IllegalArgumentException("Queen position must have column <= 7.");
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}