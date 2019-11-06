import java.util.stream.*;

class IsbnVerifier {

    boolean isValid(String stringToVerify) {
        String reducedIsbn =
                stringToVerify
                        .chars()
                        .filter(IsbnVerifier::isNotHyphen)
                        .mapToObj(Character::toString)
                        .collect(Collectors.joining());
        return containsTenDigits(reducedIsbn) && containsValidDigits(reducedIsbn) && sumOfDigitsIsDivisibleByTen(reducedIsbn);
    }

    private static boolean isNotHyphen(int charValue) {
        return charValue != '-';
    }

    private static boolean containsTenDigits(String isbn) {
        return isbn.length() == 10;
    }

    private static boolean containsValidDigits(String isbn) {
        return firstNineDigitsAreNumbers(isbn) && lastDigitIsValidIsbn(isbn);
    }

    private static boolean firstNineDigitsAreNumbers(String isbn) {
        return isbn.chars()
                .limit(isbn.length() - 1)
                .allMatch(Character::isDigit);
    }

    private static boolean lastDigitIsValidIsbn(String isbn) {
        return isValidIsbnDigit(isbn.charAt(isbn.length() - 1));
    }

    private static boolean isValidIsbnDigit(int charValue) {
        return Character.isDigit(charValue) || charValue == 'X';
    }

    private static boolean sumOfDigitsIsDivisibleByTen(String isbn) {
        return IntStream
                .iterate(10, i -> i - 1)
                .limit(10)
                .map(index -> index * getNumberFromCharacter(isbn.charAt(10 - index)))
                .sum() % 11 == 0;
    }

    private static int getNumberFromCharacter(char c) {
        return c == 'X' ? 10 : Character.getNumericValue(c);
    }

}
