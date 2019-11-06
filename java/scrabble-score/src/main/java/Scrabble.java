class Scrabble {

    final private int score;

    Scrabble(String word) {
        this.score = this.calculateScore(word.toUpperCase());
    }

    private int calculateScore(String word) {
        return word.chars()
                .filter(Scrabble::isValidCharacter)
                .mapToObj(Scrabble::toCharacter)
                .mapToInt(Scrabble::getScoreFromLetter)
                .sum();
    }

    private static boolean isValidCharacter(int charValue) {
        return charValue >= 'A' && charValue <= 'Z';
    }

    private static char toCharacter(int charValue) {
        return (char) charValue;
    }

    private static int getScoreFromLetter(char letter) {
        switch (letter) {
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
            case 'L':
            case 'N':
            case 'R':
            case 'S':
            case 'T':
                return 1;
            case 'D':
            case 'G':
                return 2;
            case 'B':
            case 'C':
            case 'M':
            case 'P':
                return 3;
            case 'F':
            case 'H':
            case 'V':
            case 'W':
            case 'Y':
                return 4;
            case 'K':
                return 5;
            case 'J':
            case 'X':
                return 8;
            case 'Q':
            case 'Z':
                return 10;
            default:
                return 0;
        }
    }

    int getScore() {
        return score;
    }

}
