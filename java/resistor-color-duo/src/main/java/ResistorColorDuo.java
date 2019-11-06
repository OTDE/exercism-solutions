import java.util.Arrays;
import java.util.stream.Collectors;

class ResistorColorDuo {

    private enum Colors {
        black,
        brown,
        red,
        orange,
        yellow,
        green,
        blue,
        violet,
        grey,
        white
    }

    int value(String[] colors) {
        return Integer.parseInt(
                Arrays.stream(colors)
                        .limit(2)
                        .map(color -> Integer.toString(Colors.valueOf(color).ordinal()))
                        .collect(Collectors.joining())
        );
    }

}
