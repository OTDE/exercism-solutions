import java.util.stream.Collectors;
import java.util.stream.IntStream;

class TwelveDays {

    private static class Verse {}

    private static class GiftVerse extends Verse {

        private static final String[] GIFTS = new String[] {
                "Partridge in a Pear Tree",
                "Turtle Doves",
                "French Hens",
                "Calling Birds",
                "Gold Rings",
                "Geese-a-Laying",
                "Swans-a-Swimming",
                "Maids-a-Milking",
                "Ladies Dancing",
                "Lords-a-Leaping",
                "Pipers Piping",
                "Drummers Drumming"
        };

        private static final String[] DAYS = new String[] {
                "a",
                "two",
                "three",
                "four",
                "five",
                "six",
                "seven",
                "eight",
                "nine",
                "ten",
                "eleven",
                "twelve",
        };

        private static String getVerse(int day) {
            return " " + DAYS[day - 1] + " " + GIFTS[day - 1];
        }
    }

    private static class OpeningVerse extends Verse {

        private static final String[] ORDINALS = new String[] {
                "first",
                "second",
                "third",
                "fourth",
                "fifth",
                "sixth",
                "seventh",
                "eighth",
                "ninth",
                "tenth",
                "eleventh",
                "twelfth",
        };

        protected static String getVerse(int day) {
            return "On the " + ORDINALS[day - 1] + " day of Christmas my true love gave to me:";
        }
    }

    private static String getVerses(int startVerse, int endVerse) {
        return OpeningVerse.getVerse(endVerse).concat(
                endVerse == 1 ? GiftVerse.getVerse(1) + ".\n" :
                        IntStream
                                .iterate(endVerse, day -> day - 1)
                                .limit(endVerse - startVerse)
                                .mapToObj(GiftVerse::getVerse)
                                .collect(Collectors.joining(",", "",
                                        ", and" + GiftVerse.getVerse(1) + ".\n"))
        );
    }

    String verse(int verseNumber) {
        return getVerses(1, verseNumber);
    }

    String verses(int startVerse, int endVerse) {
        return IntStream
                .rangeClosed(startVerse, endVerse)
                .mapToObj(this::verse)
                .collect(Collectors.joining("\n"));
    }
    
    String sing() {
         return verses(1, 12);
    }
}
