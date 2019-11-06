import java.util.*;
import java.util.stream.*;

class HandshakeCalculator {

    List<Signal> calculateHandshake(int number) {
        return IntStream
                .range(0, Signal.values().length)
                .boxed()
                .sorted(getSortingOrder(number))
                .filter(offset -> numberContainsBitAtOffset(number, offset))
                .map(HandshakeCalculator::getSignalFromOffset)
                .collect(Collectors.toList());
    }

    private static Comparator<Integer> getSortingOrder(int number) {
        return shouldReverse(number) ? Collections.reverseOrder() : Comparator.naturalOrder();
    }

    private static boolean shouldReverse(int number) {
        return numberContainsBitAtOffset(number, Signal.values().length);
    }

    private static boolean numberContainsBitAtOffset(int number, int offset) {
        return (number >> offset & 1) == 1;
    }

    private static Signal getSignalFromOffset(int offset) {
        return Signal.values()[offset];
    }

}
