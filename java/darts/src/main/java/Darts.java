import java.util.*;
import java.util.function.*;

class Darts {

    private static final Map<Integer, Integer> DARTBOARD_SCORES = Map.ofEntries(
      Map.entry(1, 5),
      Map.entry(5, 4),
      Map.entry(10, 1)
    );

    private int score;

    Darts(double x, double y) {
        score = calculateScore(x, y);
    }

    private static int calculateScore(double x, double y) {
        double radius = toRadius.applyAsDouble(x, y);
        return getScoreFromRadius.applyAsInt(radius);
    }

    private static DoubleBinaryOperator toRadius =
            (x, y) -> Math.sqrt(x * x + y * y);

    private static DoubleToIntFunction getScoreFromRadius =
            (radius) -> DARTBOARD_SCORES
                    .entrySet()
                    .stream()
                    .filter(entry -> radius <= entry.getKey())
                    .mapToInt(Map.Entry::getValue)
                    .sum();


    int score() {
        return score;
    }

}
