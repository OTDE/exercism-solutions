import java.util.function.IntPredicate;
import java.util.stream.Collectors;

public class PhoneNumber {

    private String number;

    public PhoneNumber(String number) {
        throwIfNumberContainsInvalidCharacters(number);
        String digits = number.chars()
                            .filter(Character::isDigit)
                            .mapToObj(Character::toString)
                            .collect(Collectors.joining());
        throwIfDigitsAreIncorrectlyFormatted(digits);
        this.number = digits.length() == 11 ? digits.substring(1) : digits;
    }

    private static void throwIfNumberContainsInvalidCharacters(String number) {
        if (numberContains(number, Character::isLetter)) {
            throw new IllegalArgumentException("letters not permitted");
        } else if (numberContains(number, PhoneNumber::isInvalidPunctuation)) {
            throw new IllegalArgumentException("punctuations not permitted");
        }
    }

    private static boolean numberContains(String number, IntPredicate characterFilter) {
        return number.chars()
                .anyMatch(characterFilter);
    }

    private static boolean isInvalidPunctuation(int charValue) {
        return !(Character.isDigit(charValue)
                || Character.isWhitespace(charValue)
                || isPhonePunctuation(charValue));
    }

    private static boolean isPhonePunctuation(int charValue) {
        switch (charValue) {
            case '-':
            case '+':
            case '(':
            case ')':
            case '.':
                return true;
            default:
                return false;
        }
    }

    private static void throwIfDigitsAreIncorrectlyFormatted(String digits) throws IllegalArgumentException {
        throwIfDigitLengthIsOutOfBounds(digits);
        throwIfDigitsAreInvalid(digits);
    }

    private static void throwIfDigitLengthIsOutOfBounds(String digits) throws IllegalArgumentException {
        if (digits.length() < 10) {
            throw new IllegalArgumentException("incorrect number of digits");
        } else if (digits.length() > 11) {
            throw new IllegalArgumentException("more than 11 digits");
        }
    }

    private static void throwIfDigitsAreInvalid(String digits) throws IllegalArgumentException {
        if (digits.length() == 11 && !digits.startsWith("1")) {
            throw new IllegalArgumentException("11 digits must start with 1");
        }
        final char areaCode = areaCodeOf(digits);
        if (isZeroOrOne(areaCode)) {
            throw new IllegalArgumentException("area code cannot start with " + getZeroOrOneAsWord(areaCode));
        }
        final char exchangeCode = exchangeCodeOf(digits);
        if (isZeroOrOne(exchangeCode)) {
            throw new IllegalArgumentException("exchange code cannot start with " + getZeroOrOneAsWord(exchangeCode));
        }
    }

    private static char areaCodeOf(String digits) {
        return digitAt(digits, 0);
    }

    private static char exchangeCodeOf(String digits) {
        return digitAt(digits, 3);
    }

    private static char digitAt(String digits, int offset) {
        return digits.length() == 11 ? digits.charAt(offset + 1) : digits.charAt(offset);
    }

    private static boolean isZeroOrOne(char digit) {
        return digit == '0' || digit == '1';
    }

    private static String getZeroOrOneAsWord(char digit) {
        return digit == '0' ? "zero" : "one";
    }

    String getNumber() {
        return number;
    }
}
