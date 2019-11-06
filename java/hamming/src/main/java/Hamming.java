class Hamming {

    private String leftStrand, rightStrand;
    private int hammingDistance;

    Hamming(String leftStrand, String rightStrand) throws IllegalArgumentException {
        if (leftStrand.isEmpty() || rightStrand.isEmpty()) {
            if (!rightStrand.isEmpty()) {
                throw new IllegalArgumentException("left strand must not be empty.");
            } else if (!leftStrand.isEmpty()) {
                throw new IllegalArgumentException("right strand must not be empty.");
            }
        }
        if (leftStrand.length() != rightStrand.length()) {
            throw new IllegalArgumentException("leftStrand and rightStrand must be of equal length.");
        }
        this.leftStrand = leftStrand;
        this.rightStrand = rightStrand;
        this.hammingDistance = this.calculateHammingDistance();
    }

    int getHammingDistance() {
        return this.hammingDistance;
    }

    private int calculateHammingDistance() {
        int differenceCount = 0;
        for (int i = 0; i < leftStrand.length(); i++) {
            if (leftStrand.charAt(i) != rightStrand.charAt(i)) {
                differenceCount++;
            }
        }
        return differenceCount;
    }

}
