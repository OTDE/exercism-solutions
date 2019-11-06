import java.math.BigInteger;

class Grains {

    BigInteger grainsOnSquare(final int square) throws IllegalArgumentException {
        throwIfNotOnBoard(square);
        return BigInteger.TWO.pow(square - 1);
    }

    private static void throwIfNotOnBoard(int square) throws IllegalArgumentException {
        if (square < 1 || square > 64) {
            throw new IllegalArgumentException("square must be between 1 and 64");
        }
    }

    BigInteger grainsOnBoard() {
        return gatherGrain(64);
    }

    private BigInteger gatherGrain(final int square) {
        if (square == 0) {
            return BigInteger.ZERO;
        }
        return grainsOnSquare(square).add(gatherGrain(square - 1));
    }

}
