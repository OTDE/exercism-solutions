import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

class LargestSeriesProductCalculator {

    final private Supplier<IntStream> digits;

    LargestSeriesProductCalculator(String inputNumber) throws IllegalArgumentException {
        throwIfInvalidInput(inputNumber);
        digits = () -> inputNumber
                        .chars()
                        .map(Character::getNumericValue);
    }

    private static boolean containsNonDigits(String inputNumber) {
        return inputNumber.chars().anyMatch(ch -> !Character.isDigit(ch));
    }

    long calculateLargestProductForSeriesLength(int numberOfDigits) throws IllegalArgumentException {
        throwIfOutOfBounds(numberOfDigits);
        return LongStream
                .iterate(0, i -> i + 1)
                .limit(getSizeOfDigitStream() - numberOfDigits + 1)
                .map(offset -> productOfRange(offset, numberOfDigits))
                .max()
                .orElse(1L);
    }

    private void throwIfOutOfBounds(int numberOfDigits) {
        if (getSizeOfDigitStream() < numberOfDigits) {
            throw new IllegalArgumentException("Series length must be less than or equal to the length of the string to search.");
        }
        if (numberOfDigits < 0) {
            throw new IllegalArgumentException("Series length must be non-negative.");
        }
    }

    private static void throwIfInvalidInput(String inputNumber) {
        if (containsNonDigits(inputNumber)) {
            throw new IllegalArgumentException("String to search may only contain digits.");
        }
    }

    private long getSizeOfDigitStream() {
        return digits
                .get()
                .count();
    }

    private long productOfRange(long offset, int seriesSize) {
        return digits
                .get()
                .skip(offset)
                .limit(seriesSize)
                .asLongStream()
                .reduce(1, (a, b) -> a * b);
    }
}
