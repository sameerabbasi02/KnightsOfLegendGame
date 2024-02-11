public class Knight extends MOB {
    protected final int id;
    private Fortune currentFortune;
    protected int xp;

    public Knight(int id, String name, int hp, int armor, int hitModifier, DiceType damageDie, int xp) {
        super(name, hp, armor, hitModifier, damageDie);
        this.id = id;
        this.xp = xp;
    }

    public void addXP(int xp) {
        this.xp += xp;
    }

    public Fortune getActiveFortune() {
        return currentFortune;
    }

    public int getArmor() {
        if (currentFortune != null) {
            return currentFortune.getArmor() + super.getArmor();
        }
        else {
            return super.getArmor();
        }
    }

    public DiceType getDamageDie() {
        if (currentFortune != null) {
            return currentFortune.getDamageDie();
        }
        else {
            return super.getDamageDie();
        }
    }

    public int getHitModifier() {
        if (currentFortune != null) {
            return currentFortune.getHitModifier()+ super.getHitModifier();
        }
        else {
            return super.getHitModifier();
        }
    }

    public Integer getId() {
        return Integer.valueOf(id);
    }

    public int getMaxHP() {
        if (currentFortune != null) {
            return currentFortune.getMaxHP() + super.getMaxHP();
        }
        else {
            return super.getMaxHP();
        }
    }

    public int getXP() {
        return xp;
    }

    public void setActiveFortune(Fortune activeFortune) {
        this.currentFortune = activeFortune;
    }

    public String toCSV() {
        return name + "," + maxHP + "," + armor + "," + hitModifier + "," + damageDie + "," + xp;
    }

    public String toString() {
        return "+============================+\n" +
                String.format("| %-27s|%n", getName()) +
                String.format("| id: %-23d|%n", getId()) +
                "|                            |\n" +
                String.format("| Health: %-6d  XP: %-7d|%n", getHP(), getXP()) +
                String.format("|  Power: %-5d   Armor: %-4d|%n", getHitModifier(), getArmor()) +
                "|                            |\n" +
                "+============================+";
    }

}