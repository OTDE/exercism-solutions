import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Anagram {

    private final String word;
    private final Map<Character, Long> frequencies;

    public Anagram(String word) {
        this.word = word;
        frequencies = getFrequencyMap(word);
    }

    public List<String> match(List<String> words) {
        return words.stream()
                .filter((word) -> !word.equalsIgnoreCase(this.word))
                .filter((word) -> this.frequencies.equals(getFrequencyMap(word)))
                .collect(Collectors.toList());
    }

    private static Map<Character, Long> getFrequencyMap(String word) {
        return word.toUpperCase().chars()
                .mapToObj(Anagram::toCharacter)
                .collect(Collectors.groupingBy(
                        Function.identity(), Collectors.counting()));
    }

    private static char toCharacter(int charValue) {
        return (char) charValue;
    }

}