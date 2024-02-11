import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CombatEngine {
    private final DiceSet dice = new DiceSet();
    private final GameView view;
    private final Random rnd = new Random();
    private final GameData data;

    CombatEngine(GameData data, GameView view) {
        this.data = data;
        this.view = view;
    }

    private int doBattle(List<MOB> attackers, List<MOB> defenders) {
        return 0;
    }

    public void initialize() {
        for (int i  = 0; i < data.getActiveKnights().size(); i++) {
            Fortune knightsFortune = data.getRandomFortune();
            data.getActiveKnights().get(i).setActiveFortune(knightsFortune);
        }
        view.printFortunes(data.activeKnights);
    }

    public void clear() {
        for (int i = 0; i < data.getKnights().size(); i++) {
            data.getKnights().get(i).setActiveFortune(null);
        }
    }

    public void runCombat()
    {
        List<MOB> encounter = data.getRandomMonsters();
        view.printBattleText(encounter, data.getActiveKnights());
        boolean run = true;
        DiceSet dice = new DiceSet();
        while(run == true)
        {

            for(Knight knight : data.getActiveKnights())
            {

                if(encounter.size() == 0)
                {
                    run = false;
                    boolean Continue = view.checkContinue();
                    if(Continue == true) runCombat();
                    else view.displayMainMenu();
                    break;
                }

                MOB target = encounter.get(rnd.nextInt(encounter.size()));
                if((dice.roll(DiceType.D20)) + knight.getHitModifier() >= target.getArmor())
                {
                    System.out.print(knight.getName() + " attacks " + target.getName());
                    int damage = dice.roll(knight.damageDie);
                    target.addDamage(damage);
                    System.out.println(", dealt " + damage + " damage");
                }
                else System.out.println(knight.getName() + " missed");
                if(target.getHP() <= 0)
                {
                    view.printBattleText(target);
                    for(Knight xKnight : data.getActiveKnights())                {
                        xKnight.addXP(1);
                    }
                    encounter.remove(target);
                }
            }

            if(encounter.size() == 0)
            {
                run = false;
                boolean Continue = view.checkContinue();
                if(Continue == true) runCombat();
                else
                {
                    view.displayMainMenu();
                    break;
                }
            }

            else if(run == true)
            {
                for(MOB mob : encounter)
                {
                    if(data.getActiveKnights().size() == 0)
                    {
                        //view.printDefeated();
                        //run = false;
                        break;
                    }

                    Knight target = data.getActiveKnights().get(rnd.nextInt(data.getActiveKnights().size()));
                    if(dice.roll(DiceType.D20) + mob.getHitModifier() >= target.getArmor())
                    {
                        System.out.print(mob.getName() + " attacks " + target.getName());
                        int damage = dice.roll(mob.getDamageDie());
                        target.addDamage(damage);
                        System.out.println(", dealt " + damage + " damage");
                    }

                    else System.out.println(mob.getName() + " missed");
                    if(target.getHP() <= 0)
                    {
                        target.setActiveFortune(null);
                        view.printBattleText(target);
                        data.removeActive(target);
                    }
                }
            }
            if(data.getActiveKnights().size() == 0)
            {
                view.printDefeated();
                run = false;
            }
        }
    }

}