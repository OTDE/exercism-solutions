import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class House {

    private static class Verse {
        private static final String THE = "the";
        private final String subject;

        Verse(String subject) {
            this.subject = subject;
        }

        public String toLine() {
            return String.join(" ", THE, subject);
        }

        public String toIntroduction() {
            return String.join(" ", THE, subject);
        }
    }

    private static class ActionVerse extends Verse {
        private final String action;

        ActionVerse(String action, String subject) {
            super(subject);
            this.action = action;
        }

        public String toLine() {
            return String.join(" ", action, super.toLine());
        }

        @Override
        public String toIntroduction() {
            return super.toIntroduction();
        }
    }

    private static class DescriptiveVerse extends ActionVerse {
        private final String descriptor;

        DescriptiveVerse(String action, String subject, String descriptor) {
            super(action, subject);
            this.descriptor = descriptor;
        }

        @Override
        public String toLine() {
            return String.join(" ", super.toLine(), descriptor);
        }

        @Override
        public String toIntroduction() {
            return String.join(" ", super.toIntroduction(), descriptor);
        }
    }

    private static final String THIS_IS = "This is ";
    private static final String THAT = " that ";
    private static final String PERIOD = ".";
    private static final String NEXT_VERSE = "\n";

    private static final Map<Integer, Verse> VERSES = Map.ofEntries(
            Map.entry(1, new DescriptiveVerse("lay in", "house", "that Jack built")),
            Map.entry(2, new ActionVerse("ate", "malt")),
            Map.entry(3, new ActionVerse("killed", "rat")),
            Map.entry(4, new ActionVerse("worried", "cat")),
            Map.entry(5, new ActionVerse("tossed", "dog")),
            Map.entry(6, new DescriptiveVerse("milked", "cow", "with the crumpled horn")),
            Map.entry(7, new DescriptiveVerse("kissed", "maiden", "all forlorn")),
            Map.entry(8, new DescriptiveVerse("married", "man", "all tattered and torn")),
            Map.entry(9, new DescriptiveVerse("woke", "priest", "all shaven and shorn")),
            Map.entry(10, new DescriptiveVerse("kept", "rooster", "that crowed in the morn")),
            Map.entry(11, new DescriptiveVerse("belonged to", "farmer", "sowing his corn")),
            Map.entry(12, new Verse("horse and the hound and the horn"))
    );

    String verse(int verse) {
        return IntStream
                .iterate(verse, index -> index - 1)
                .limit(verse)
                .mapToObj(
                        (index) -> index == verse ? getIntro(index) : getVerseLine(index))
                .collect(Collectors.joining(
                        THAT,
                        THIS_IS,
                        PERIOD
                ));
    }

    String verses(int startVerse, int endVerse) {
        return IntStream
                .iterate(startVerse, index -> index + 1)
                .limit(endVerse - startVerse + 1)
                .mapToObj(this::verse)
                .collect(Collectors.joining(
                        NEXT_VERSE
                ));
    }

    String sing() {
        return verses(1, 12);
    }

    private static String getVerseLine(int index) {
        return VERSES.get(index).toLine();
    }

    private static String getIntro(int index) {
        return VERSES.get(index).toIntroduction();
    }

}