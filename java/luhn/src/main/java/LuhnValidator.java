import java.util.stream.IntStream;

class LuhnValidator {

    boolean isValid(String candidate) {
        if (shouldNotProcess(candidate)) {
            return false;
        }
        return doesChecksumValidate(candidate);
    }

    private static boolean shouldNotProcess(String candidate) {
        return candidate.trim().length() < 2 ||
                candidate.chars()
                    .anyMatch(LuhnValidator::isNeitherDigitNorWhiteSpace);
    }

    private static boolean isNeitherDigitNorWhiteSpace(int ch) {
        return !Character.isDigit(ch) && !Character.isWhitespace(ch);
    }

    private static boolean doesChecksumValidate(String candidate) {
        final int[] digits = digitsOf(candidate);
        return IntStream
                .rangeClosed(1, digits.length)
                .map(index -> index % 2 == 0
                        ? doubleDigitToLimitNine(digits[digits.length - index])
                        : digits[digits.length - index])
                .sum() % 10 == 0;
    }

    private static int[] digitsOf(String candidate) {
        return candidate.chars()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .toArray();
    }

    private static int doubleDigitToLimitNine(int digit) {
        return digit + digit - (digit / 5) * 9;
    }

}
