public class Main {
    private static String saveData = "knights.csv";
    private static String gameData = "gamedata.csv";

    public static void main(String[] args) {
        processArgs(args);
        GameData data = new CSVGameData(gameData, saveData);
        GameView view  = new ConsoleView();
        CombatEngine engine = new CombatEngine(data, view);
        GameController controller = new GameController(data, view, engine);
        controller.start();
    }
    private static void processArgs(String[] args) {
        for (String processArgsString : args) {
            if (processArgsString.toLowerCase().startsWith("--data")) {
                gameData = processArgsString.substring(processArgsString.indexOf("=" + 1));
            }
            else {
                saveData = processArgsString;
                gameData = "gamedata.csv";
            }
        }
    }
}