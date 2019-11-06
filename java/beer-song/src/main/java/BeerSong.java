import java.util.stream.*;

public class BeerSong {

    private static String getFirstLine(int beerCount) {
        return String.join(", ", getOnTheWallFragment(beerCount), getBeerCountFragment(beerCount).toLowerCase());
    }

    private static String getSecondLine(int beerCount) {
        return String.join(", ", getPassingFragment(beerCount), getOnTheWallFragment(beerCount == 0 ?  99 : beerCount - 1).toLowerCase());
    }

    private static String getOnTheWallFragment(int beerCount) {
        return String.join(" ", getBeerCountFragment(beerCount), "on the wall");
    }

    private static String getBeerCountFragment(int beerCount) {
        return String.format("%s bottle%s of beer", beerCountAsString(beerCount), getBottlePlural(beerCount));
    }

    private static String beerCountAsString(int beerCount) {
        return beerCount == 0 ? "No more" : Integer.toString(beerCount);
    }

    private static String getBottlePlural(int beerCount) {
        return beerCount == 1 ? "" : "s";
    }

    private static String getPassingFragment(int beerCount) {
        return beerCount == 0 ? "Go to the store and buy some more" : getTakeDownFragment(beerCount);
    }

    private static String getTakeDownFragment(int beerCount) {
        return String.format("Take %s down and pass it around", beerCount == 1 ? "it" : "one");
    }

    private static String getVerse(int verse) {
        return String.join(".\n", getFirstLine(verse), getSecondLine(verse)).concat(".\n");
    }

    String sing(int startingVerse, int verseCount) {
        return IntStream
                .iterate(startingVerse, i -> i - 1)
                .limit(verseCount)
                .mapToObj(BeerSong::getVerse)
                .collect(Collectors.joining("\n", "", "\n"));
    }

    String singSong() {
        return sing(99, 100);
    }
}