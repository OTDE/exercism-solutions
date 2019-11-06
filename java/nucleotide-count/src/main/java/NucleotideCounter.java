import java.util.*;
import java.util.function.*;

public class NucleotideCounter {

    private Map<Character, Integer> nucleotideCounter = new HashMap<>();

    private static final Set<Character> VALID_NUCLEOTIDES = Set.of('A', 'C', 'G', 'T');

    private static final IntPredicate IS_NOT_VALID_NUCLEOTIDE =
            (charValue) -> !VALID_NUCLEOTIDES.contains((char) charValue);

    public NucleotideCounter(String sequence) throws IllegalArgumentException {
        throwIfSequenceContainsNonNucleotideCharacters(sequence);
        initializeNucleotideCounter();
        final IntConsumer addNucleotideToCount =
                (nucleotide) -> nucleotideCounter.merge((char) nucleotide, 1, Integer::sum);
        sequence.chars().forEach(addNucleotideToCount);
    }

    private static void throwIfSequenceContainsNonNucleotideCharacters(String sequence) {
        if (isNotNucleotideSequence(sequence)) {
            throw new IllegalArgumentException("Sequence contains invalid nucleotide.");
        }
    }

    private static boolean isNotNucleotideSequence(String sequence) {
        return sequence.chars().anyMatch(IS_NOT_VALID_NUCLEOTIDE);
    }

    private void initializeNucleotideCounter() {
        for (char nucleotide : VALID_NUCLEOTIDES) {
            nucleotideCounter.put(nucleotide, 0);
        }
    }

    Map<Character, Integer> nucleotideCounts() {
        return nucleotideCounter;
    }
}
