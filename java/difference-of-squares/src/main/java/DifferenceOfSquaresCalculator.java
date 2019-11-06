class DifferenceOfSquaresCalculator {

    int computeSquareOfSumTo(int input) {
        int sum = triangleNumberClosedForm(input);
        return sum * sum;
    }

    private static int triangleNumberClosedForm(int input) {
        return (input * (input + 1)) / 2;
    }

    int computeSumOfSquaresTo(int input) {
        return squarePyramidalNumberClosedForm(input);
    }

    private static int squarePyramidalNumberClosedForm(int input) {
        return (input * (input  + 1) * (2 * input + 1)) / 6;
    }

    int computeDifferenceOfSquares(int input) {
        return computeSquareOfSumTo(input) - computeSumOfSquaresTo(input);
    }

}
