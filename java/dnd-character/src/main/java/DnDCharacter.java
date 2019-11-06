import java.util.Random;

public class DnDCharacter {

    private static final Random RNG = new Random();

    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    private int hitpoints;

    public DnDCharacter() {
        strength = ability();
        dexterity = ability();
        constitution = ability();
        intelligence = ability();
        wisdom = ability();
        charisma = ability();

        hitpoints = 10 + modifier(constitution);
    }

    int ability() {
        return RNG
                .ints(4, 1, 7)
                .sorted()
                .skip(1)
                .sum();
    }

    int modifier(int input) {
        return (input / 2) - 5;
    }

    int getStrength() {
        return strength;
    }

    int getDexterity() {
        return dexterity;
    }

    int getConstitution() {
        return constitution;
    }

    int getIntelligence() {
        return intelligence;
    }

    int getWisdom() {
        return wisdom;
    }

    int getCharisma() {
        return charisma;
    }

    int getHitpoints() {
        return hitpoints;
    }

}
