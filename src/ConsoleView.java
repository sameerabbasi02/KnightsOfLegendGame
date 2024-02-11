import java.util.List;
import java.util.Scanner;

public class ConsoleView implements GameView {
    private final Scanner in = new Scanner(System.in);

    ConsoleView() {
    }

    public String displayMainMenu() {
        String mainMenu = "";
        System.out.println("\nWhat would you like to do?");
        mainMenu = in.nextLine();
        return mainMenu;
    }

    public boolean checkContinue() {
        String userCheckContinue = "";
        System.out.println("Would you like to continue your quest (y/n)?");
        userCheckContinue = in.nextLine();
        if (userCheckContinue.toLowerCase().contains("y")) {
            return true;
        }
        else {
            return false;
        }
    }

    public void endGame() {
        System.out.println("Thanks for playing :)");
    }

    public void knightNotFound() {
        System.out.println("Knight not found!");
    }

    public void listKnights(List<Knight> knights) {
        if (knights == null) {
            System.out.println("No knights to list");
        }
        else {
            for (Knight theknight : knights) {
                System.out.println(theknight.getId() + ": " + theknight.getName());
            }
        }
    }

    public void printBattleText(MOB dead) {
        System.out.println(dead + " was defeated!");
    }

    public void printBattleText(List<MOB> monsters, List<Knight> activeKnights) {
        System.out.println("\nOur heroes come across the following monsters. Prepare for battle!");

        System.out.println("\nKnights");
        for (int i = 0; i < activeKnights.size(); i++) {
            System.out.println(activeKnights.get(i));
        }

        System.out.println("\nFoes");
        for (int j = 0; j < monsters.size(); j++) {
            System.out.println(monsters.get(j));
        }
    }

    @Override
    public void printBattleText(List<MOB> monsters) {

    }

    public void printDefeated() {
        System.out.println("All active knights have been defeated!\n");
    }

    public void printFortunes(List<Knight> activeKnights) {
        System.out.println("For this quest, our knights drew the following fortunes!");

        for (int i = 0; i < activeKnights.size(); i++) {
            System.out.println(activeKnights.get(i) + " drew");
            System.out.println(activeKnights.get(i).getActiveFortune());
        }
    }

    public void setActiveFailed() {
        System.out.println("Unable to set active knight. Only four can be active at a time\n");
    }

    public void showKnight(Knight knight) {
        System.out.println(knight.toString());
    }

    public void printHelp() {
        System.out.println("            ls or list all  - listing the knights\n" +
                "            list active  - list the active knights knights only\n" +
                "            show name or id - show the knight details card\n" +
                "            set active name or id - set knight as active (note: only 4 knights can be active) \n" +
                "            remove active name or id - remove a knight from active status (heals knight)\n" +
                "            explore or adventure or quest - find random monsters to fight\n" +
                "            save filename - save the game to the file name (default: saveData.csv)\n" +
                "            exit or goodbye - to leave the game\n" +
                "\n" +
                " Game rules: You can have four active knights. As long as they are active, they won't heal, \n" +
                " but they can gain XP by going on adventures.\n" +
                " When you make a knight inactive, they will heal. How many monsters can you defeat \n" +
                " before, you have to heal?  ");
    }

    public void splashScreen() {
        System.out.println("---------------------------------------------------\n" +
                "|                                                 |\n" +
                "|                Knights Of Legend                |\n" +
                "|                                                 |\n" +
                "---------------------------------------------------\n" +
                "            Type help for command list");
    }
}
