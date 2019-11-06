import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class RomanNumeral {

    private static final Map<Integer, String> NUMERALS = Map.ofEntries(
            Map.entry(1, "I"),
            Map.entry(4, "IV"),
            Map.entry(5, "V"),
            Map.entry(9, "IX"),
            Map.entry(10, "X"),
            Map.entry(40, "XL"),
            Map.entry(50, "L"),
            Map.entry(90, "XC"),
            Map.entry(100, "C"),
            Map.entry(400, "CD"),
            Map.entry(500, "D"),
            Map.entry(900, "CM"),
            Map.entry(1000, "M")
    );

    private static final Supplier<IntStream> NUMERAL_VALUES =
            () -> NUMERALS
                    .keySet()
                    .stream()
                    .mapToInt(Integer::intValue);

    private final String numerals;

    RomanNumeral(int value) {
        StringBuilder builder = new StringBuilder();
        while (value != 0) {
            int maxInValue = largestNumeralIn(value);
            builder.append(NUMERALS.get(maxInValue));
            value -= maxInValue;
        }
        numerals = builder.toString();
    }

    String getRomanNumeral() {
        return numerals;
    }

    private static int largestNumeralIn(int value) {
        return NUMERAL_VALUES
                .get()
                .filter((numeral) -> numeral <= value)
                .max()
                .orElse(1);
    }
}
