import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Proverb {

    private String recitation;

    Proverb(String[] words) {
        recitation = words.length == 0 ? ""
                : words.length == 1 ? getFinalProverbLine(words[0])
                : getFullProverb(words);
    }

    private static String getFullProverb(String[] words) {
        return IntStream
                .iterate(0, i -> i + 1)
                .limit(Math.max(words.length - 1, 0))
                .mapToObj(index -> getProverbLineFromIndex(words, index))
                .collect(Collectors.joining("\n", "", "\n"))
                .concat(getFinalProverbLine(words[0]));
    }

    private static String getProverbLineFromIndex(String[] words, int index) {
        return "For want of a " + words[index] + " the " + words[index + 1] + " was lost.";
    }
    private static String getFinalProverbLine(String word) {
        return "And all for the want of a " + word + ".";
    }

    String recite() {
        return recitation;
    }

}
