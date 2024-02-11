public class GameController {
    private final GameData data;
    private final GameView view;
    private final CombatEngine engine;

    GameController(GameData data, GameView view, CombatEngine engine) {
        this.data = data;
        this.engine = engine;
        this.view = view;
    }

    protected boolean processCommand(String command) {
        if (command.toLowerCase().contains("exit") || command.toLowerCase().contains("bye")) {
            return false;
        }
        else if (command.toLowerCase().contains("ls") || command.toLowerCase().contains("list all")) {
            view.listKnights(data.getKnights());
        }
        else if (command.toLowerCase().equals("list active")) {
            for (int i = 0; i < data.getActiveKnights().size(); i++) {
                System.out.println(data.getActiveKnights().get(i));
            }
        }
        else if (command.toLowerCase().startsWith("show")) {
            if (data.findKnight(command.substring(4), data.knights) != null) {
                view.showKnight(data.findKnight(command.substring(4), data.knights));
            }
            else {
                view.knightNotFound();
            }
        }
        else if (command.toLowerCase().contains("set active")) {
            if (data.getKnight(command.substring(11)) != null) {
                if (data.setActive(data.getKnight(command.substring(11))) == false) {
                    view.setActiveFailed();
                }
            }
            else view.knightNotFound();
        }
        else if (command.toLowerCase().contains("remove")) {
            if (data.getActive(command.substring(6)) != null) {
                data.removeActive(data.getActive(command.substring(7)));
            }
            else {
                view.knightNotFound();
            }
        }
        else if (command.toLowerCase().contains("save")) {
            if (command.toLowerCase().trim().equals("save")) {
                data.save("saveData.csv");
            }
            else {
                data.save(command.substring(5));
            }
        }
        else if (command.toLowerCase().contains("explore") || command.toLowerCase().contains("adventure") || command.toLowerCase().contains("quest")) {
            engine.initialize();
            engine.runCombat();
            engine.clear();
        }
        else {
            view.printHelp();
        }
        return true;
    }

    public void start() {
        view.splashScreen();
        while (processCommand(view.displayMainMenu()) == true) {
        }
        view.endGame();
    }
}