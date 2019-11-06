import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/*
Assumptions this solution makes:
- Input is a series of english letters and whitespace.
- Words to be translated are separated by some number of spaces.
- The translation places exactly one space between each translated word.
 */
public class PigLatinTranslator {

    private final static Pattern SWAP_DELIMITER =
            Pattern.compile("((?<=qu)[\\w])|(?<!q)u|(xr|yt)|([aeio])|((?<=[\\p{Lower}&&[^aeiou]])y)", Pattern.CASE_INSENSITIVE);

    public String translate(String word) {
        return Arrays.stream(word.split("\\s+"))
                .map(PigLatinTranslator::shuffle)
                .map(PigLatinTranslator::appendAy)
                .collect(Collectors.joining(" "));
    }

    private static String shuffle(String word) {
        final Matcher matcher = SWAP_DELIMITER.matcher(word);
        if (matcher.find()) {
            if (matcher.start() != 0) {
                String prefix = word.substring(0, matcher.start());
                String suffix = word.substring(matcher.start());
                return swapSections(prefix, suffix);
            }
        }
        return word;
    }

    private static String swapSections(String prefix, String suffix) {
        return suffix + prefix;
    }

    private static String appendAy(String word) {
        return word + "ay";
    }

}
