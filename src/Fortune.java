public class Fortune implements Attributes {
    private int hpBonus;
    private int armor;
    private String name;
    private DiceType type;
    private int hitModifier;

    public Fortune(String name, int hpBonus, int armor, int hitModifier) {
        this(name, hpBonus, armor, hitModifier, null);
    }

    public Fortune(String name, int hpBonus, int armor, int hitModifier, DiceType type) {
        this.armor = armor;
        this.hpBonus = hpBonus;
        this.type = type;
        this.hitModifier = hitModifier;
        this.name = name;

    }
    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public int getMaxHP() {
        return hpBonus;
    }

    @Override
    public DiceType getDamageDie() {
        return type;
    }

    @Override
    public int getHitModifier() {
        return hitModifier;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        String myString = "";
        if (type == null) {
            myString = "-";
        }
        else {
            myString = type.toString();
        }
        return "+======================+\n" +
                String.format("|%-22s|%n", name) +
                String.format("|HP Bonus: %+12d|%n", hpBonus) +
                String.format("|AC Bonus: %+12d|%n", armor) +
                String.format("|Hit Bonus: %+11d|%n", hitModifier) +
                String.format("|Damage Adj: %10s|%n", myString) +
                "+======================+";
    }

}