import java.util.List;
import java.util.stream.*;

public class Series {

    private String sequence;

    Series(String sequence) {
        this.sequence = sequence;
    }

    List<String> slices(int sliceLength) throws IllegalArgumentException {
        throwIfInvalidSliceLength(sliceLength);
        return IntStream
                .range(0, sequence.length() - sliceLength + 1)
                .mapToObj((offset) -> toSlicedSequence(sliceLength, offset))
                .collect(Collectors.toList());
    }

    private String toSlicedSequence(int sliceLength, int offset) {
        return IntStream
                .range(offset, offset + sliceLength)
                .mapToObj((index) -> Character.toString(sequence.charAt(index)))
                .collect(Collectors.joining());
    }

    private void throwIfInvalidSliceLength(int sliceLength) {
        if (sliceLength > sequence.length()) {
            throw new IllegalArgumentException("Slice size is too big.");
        } else if (sliceLength < 1) {
            throw new IllegalArgumentException("Slice size is too small.");
        }
    }
}
