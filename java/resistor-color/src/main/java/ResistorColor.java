import java.util.Arrays;

class ResistorColor {

    private enum Colors {
        BLACK,
        BROWN,
        RED,
        ORANGE,
        YELLOW,
        GREEN,
        BLUE,
        VIOLET,
        GREY,
        WHITE;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    int colorCode(String color) {
        return Colors.valueOf(color.toUpperCase()).ordinal();
    }

    String[] colors() {
        return Arrays.stream(Colors.values())
                .map(Colors::toString)
                .toArray(String[]::new);
    }
}
