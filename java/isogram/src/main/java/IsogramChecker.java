import java.util.function.Supplier;
import java.util.stream.Stream;

class IsogramChecker {

    boolean isIsogram(String phrase) {
        Supplier<Stream<Character>> charStream =
                () -> phrase.toUpperCase()
                    .chars()
                    .mapToObj(IsogramChecker::toCharacter)
                    .filter(Character::isLetter);
        return charStream.get().distinct().count() == charStream.get().count();
    }

    private static char toCharacter(int charValue) {
        return (char) charValue;
    }

}
