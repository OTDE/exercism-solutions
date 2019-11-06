import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ProteinTranslator {

    private static class RnaSequence {
        private String letters;

        private RnaSequence(String letters) {
            this.letters = letters;
        }

        static RnaSequence fromLetters(String letters) {
            switch (letters) {
                case "UAA":
                case "UAG":
                case "UGA":
                    return new StopSequence(letters);
                default:
                    return new RnaSequence(letters);
            }
        }

        protected boolean isNotStop() {
            return true;
        }

    }

    private static class StopSequence extends RnaSequence {
        StopSequence(String letters) {
            super(letters);
        }

        @Override
        protected boolean isNotStop() {
            return false;
        }
    }

    private static class Protein {
        private String name;

        private Protein(String name) {
            this.name = name;
        }

        private static Protein fromSequence(RnaSequence sequence) {
                return new Protein(getProteinNameFromSequence(sequence));
        }

        private static String getProteinNameFromSequence(RnaSequence sequence) {
            switch (sequence.letters) {
                case "AUG":
                    return "Methionine";
                case "UUU":
                case "UUC":
                    return "Phenylalanine";
                case "UUA":
                case "UUG":
                    return "Leucine";
                case "UCU":
                case "UCC":
                case "UCA":
                case "UCG":
                    return "Serine";
                case "UAU":
                case "UAC":
                    return "Tyrosine";
                case "UGU":
                case "UGC":
                    return "Cysteine";
                case "UGG":
                    return "Tryptophan";
                default:
                    return "Not a protein. Scope of exercise doesn't warrant exception handling.";
            }
        }
    }

    List<String> translate(String rnaSequence) {
        return IntStream
                .iterate(0, index -> index + 3)
                .limit((int) Math.ceil(rnaSequence.length() / 3.0))
                .mapToObj(index -> getSubsequence(rnaSequence, index))
                .map(RnaSequence::fromLetters)
                .takeWhile(RnaSequence::isNotStop)
                .map(Protein::fromSequence)
                .map(protein -> protein.name)
                .collect(Collectors.toList());
    }

    private static String getSubsequence(String sequence, int index) {
        return sequence.substring(index, Math.min(index + 3, sequence.length()));
    }

}