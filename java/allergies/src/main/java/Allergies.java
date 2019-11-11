
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Allergies {
    private List<Allergen> allergens;

    public Allergies(int allergenScore) {
        allergens = IntStream
                .range(0, Allergen.values().length)
                .filter(offset -> isAllergenScoreBitSetAtOffset(allergenScore, offset))
                .mapToObj(Allergies::toAllergen)
                .collect(Collectors.toList());
    }

    private static boolean isAllergenScoreBitSetAtOffset(int allergenScore, int offset) {
        return ((allergenScore >> offset) & 1) == 1;
    }

    private static Allergen toAllergen(int index) {
        return Allergen.values()[index];
    }

    boolean isAllergicTo(Allergen allergen) {
        return allergens.contains(allergen);
    }

    List<Allergen> getList() {
        return allergens;
    }

}
