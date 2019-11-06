import java.util.*;
import java.util.stream.*;

public class FoodChain {

    private static class Verse {
        final String animal;
        private String rhyme;
        private String addendum;

        Verse(String animal) {
            this.animal = animal;
        }

        Verse withRhyme(String rhyme) {
            this.rhyme = rhyme;
            return this;
        }

        Verse withAddendum(String addendum) {
            this.addendum = addendum;
            return this;
        }

        String getAddendum() {
            return addendum == null ? "" : addendum;
        }

        String getRhyme() {
            return rhyme == null ? "" : rhyme + "\n";
        }
    }

    private static final List<Verse> VERSES =
            Arrays.asList(
              new Verse("fly"),
              new Verse("spider").withRhyme("It wriggled and jiggled and tickled inside her.")
                .withAddendum(" that wriggled and jiggled and tickled inside her"),
              new Verse("bird").withRhyme("How absurd to swallow a bird!"),
              new Verse("cat").withRhyme("Imagine that, to swallow a cat!"),
              new Verse("dog").withRhyme("What a hog, to swallow a dog!"),
              new Verse("goat").withRhyme("Just opened her throat and swallowed a goat!") ,
              new Verse("cow").withRhyme("I don't know how she swallowed a cow!"),
              new Verse("horse")
            );
    private final static String FIRST_LINE =
            "I know an old lady who swallowed a";

    public String verse(int verse) {
        return verse == 8
                ? getOpeningCouplet(VERSES.get(verse - 1)).concat("She's dead, of course!")
                : IntStream
                    .iterate(verse - 1, i -> i - 1)
                    .limit(verse)
                    .mapToObj(
                            index -> index == 0 ? "" : getSingleDescendingLine(VERSES.get(index), VERSES.get(index - 1)))
                    .collect(Collectors.joining("\n", getOpeningCouplet(VERSES.get(verse - 1)), ""))
                    .concat("I don't know why she swallowed the fly. Perhaps she'll die.");
    }

    public String verses(int startVerse, int endVerse) {
        return IntStream
                .rangeClosed(startVerse, endVerse)
                .mapToObj(this::verse)
                .collect(Collectors.joining("\n\n"));
    }

    private static String getOpeningCouplet(Verse verse) {
        return String.join("\n", getOpeningLine(verse), getOpeningRhyme(verse));
    }

    private static String getOpeningLine(Verse verse) {
        return String.format("%s %s.", FIRST_LINE, verse.animal);
    }

    private static String getOpeningRhyme(Verse verse) {
        return verse.getRhyme();
    }

    private static String getSingleDescendingLine(Verse first, Verse second) {
        return String.format("She swallowed the %s to catch the %s.",
                first.animal, second.animal + second.getAddendum());
    }
}