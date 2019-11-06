public class QueenAttackCalculator {
    private Queen black;
    private Queen white;

    public QueenAttackCalculator(Queen black, Queen white) throws IllegalArgumentException {
        this.black = black;
        this.white = white;
        throwIfEitherQueenIsNull();
        throwIfQueensOccupySameSpace();
    }

    private void throwIfEitherQueenIsNull() throws IllegalArgumentException {
        if (white == null || black == null) {
            throw new IllegalArgumentException("You must supply valid positions for both Queens.");
        }
    }

    private void throwIfQueensOccupySameSpace() {
        if (queensShareRow() && queensShareColumn()) {
            throw new IllegalArgumentException("Queens cannot occupy the same position.");
        }
    }

    private boolean queensShareRow() {
        return black.getRow() == white.getRow();
    }

    private boolean queensShareColumn() {
        return black.getColumn() == white.getColumn();
    }

    private boolean queensShareDiagonal() {
        return Math.abs(black.getColumn() - white.getColumn()) == Math.abs(black.getRow() - white.getRow());
    }

    public boolean canQueensAttackOneAnother() {
        return queensShareRow() || queensShareColumn() || queensShareDiagonal();
    }
}

