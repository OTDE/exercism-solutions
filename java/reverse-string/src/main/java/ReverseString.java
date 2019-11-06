import java.util.ArrayDeque;
import java.util.stream.Collector;

class ReverseString {

    String reverse(String inputString) {
        return String.join("", (Iterable<String>) inputString.chars()
                .mapToObj(Character::toString)
                .collect(Collector.of(
                        ArrayDeque::new,
                        ArrayDeque<String>::addFirst,
                        (oldDeque, newDeque) -> {
                            newDeque.addAll(oldDeque);
                            return newDeque;
                        })));
    }
  
}