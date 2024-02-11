public class MOB implements Attributes {
    protected DiceType damageDie;
    protected int damage;
    protected int hitModifier;
    protected int armor;
    protected int maxHP;
    public int hp;
    public final String name;

    public MOB(String name, int hp, int armor, int hitModifier, DiceType damageDie) {
        maxHP = hp;
        this.name = name;
        this.armor = armor;
        this.hitModifier = hitModifier;
        this.damageDie = damageDie;
        this.hp = hp;
    }

    public void addDamage(int damage) {
        this.damage += damage;
    }

    public MOB copy() {
        MOB mobObj = new MOB(name, maxHP, hitModifier, armor, damageDie);
        return mobObj;
    }

    public int getDamage() {
        return damage;
    }

    public int getHP() {
        hp = maxHP - damage;
        return hp;
    }

    public String getName() {
        return name;
    }

    public void resetDamage() {
        damage = 0;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public int getMaxHP() {
        return maxHP;
    }

    @Override
    public DiceType getDamageDie() {
        return damageDie;
    }

    @Override
    public int getHitModifier() {
        return hitModifier;
    }

    public String toString() {
        return "+============================+\n" +
                String.format("| %-27s|%n", getName()) +
                "|                            |\n" +
                String.format("|         Health: %-10d |%n", getHP())  +
                String.format("|  Power: %-6s  Armor: %-4d|%n", getDamageDie().toString(), getArmor()) +
                "|                            |\n" +
                "+============================+";
    }

}