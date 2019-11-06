import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class DiamondPrinter {

    private static final char START = 'A';

    List<String> printToList(char a) {
        return IntStream.concat(
                IntStream
                        .iterate(START, i -> i + 1)
                        .limit(getCharacterCount(a)),
                IntStream
                        .iterate(a - 1, i -> i - 1)
                        .limit(getDifference(a)))
                .mapToObj(
                        letter -> DiamondPrinter.toDiamondRow((char) letter, getDifference(a)))
                .collect(Collectors.toList());
    }

    private static String toDiamondRow(char letter, int count) {
        return new ArrayList<>(
                Collections.nCopies(getCopiesFromLetter(letter), letter))
                    .stream()
                    .map(character -> Character.toString(character))
                    .collect(
                            Collectors.joining(
                                    getInsidePaddingForLetter(letter),
                                    getOutsidePaddingForLetterWithCount(letter, count),
                                    getOutsidePaddingForLetterWithCount(letter, count)
                            ));
    }

    private static String getInsidePaddingForLetter(char letter) {
        return getSpaces(Math.max(2 * getDifference(letter) - 1, 0));
    }

    private static String getOutsidePaddingForLetterWithCount(char letter, int count) {
        return getSpaces(count - getDifference(letter));
    }

    private static String getSpaces(int count) {
        return count == 0 ? "" : " ".repeat(count);
    }

    private static int getDifference(int end) {
        return end - START;
    }

    private static int getCharacterCount(int end) {
        return getDifference(end) + 1;
    }

    private static int getCopiesFromLetter(char letter) {
        return letter == START ? 1 : 2;
    }

}
