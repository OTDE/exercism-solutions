class RaindropConverter {

    String convert(int number) {
        Multiple numberWrapper = new Multiple(number);
        String pling = numberWrapper.isMultipleOf(3) ? "Pling" : "";
        String plang = numberWrapper.isMultipleOf(5) ? "Plang" : "";
        String plong = numberWrapper.isMultipleOf(7) ? "Plong" : "";
        String rainSounds = pling + plang + plong;
        return rainSounds.isEmpty() ? numberWrapper.toString() : rainSounds;
    }

    private static class Multiple {
        private int value;

        Multiple(int value) { this.value = value; }

        boolean isMultipleOf(int factor) { return value % factor == 0; }

        @Override
        public String toString() { return Integer.toString(value); }
    }

}
