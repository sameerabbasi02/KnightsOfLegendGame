import jdk.jshell.execution.LoaderDelegate;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class CSVGameData extends GameData{

    CSVGameData(String gameData, String saveData) {
        loadGameData(gameData);
        loadSaveData(saveData);
    }

    private Scanner readFile(String fileName) {
        try {
            Scanner scnr4ReadFile = new Scanner(new File(fileName));
            return scnr4ReadFile;
        }
        catch (Exception ex){
            System.err.println(ex);
            System.exit(1);
        }
        return null;
    }

    void loadGameData(String gamedata)
    {
        Scanner scnr4LoadGameData = readFile(gamedata);

        while(scnr4LoadGameData.hasNextLine()) {
            Scanner scnr4LoadGameData2 = new Scanner(scnr4LoadGameData.nextLine());
            scnr4LoadGameData2.useDelimiter(",");
            if (scnr4LoadGameData2.findInLine("MOB") != null) {
                MOB myMob = new MOB(scnr4LoadGameData2.next().trim(),
                        scnr4LoadGameData2.nextInt(),
                        scnr4LoadGameData2.nextInt(),
                        scnr4LoadGameData2.nextInt(),
                        DiceType.valueOf(scnr4LoadGameData2.next()));
                monsters.add(myMob);
            }
            else if (scnr4LoadGameData2.findInLine("FORTUNE") != null) {
                String loadGameFortune = scnr4LoadGameData2.next().trim();
                int loadGameHP = scnr4LoadGameData2.nextInt();
                int loadGameArmor = scnr4LoadGameData2.nextInt();
                int loadGameHitModifier = scnr4LoadGameData2.nextInt();
                String coolLine = scnr4LoadGameData2.next();

                if (coolLine.contains("D") != true) {
                    Fortune loadGameFortune3 = new Fortune(loadGameFortune, loadGameHP, loadGameArmor, loadGameHitModifier);
                    fortunes.add(loadGameFortune3);
                }
                else {
                    DiceType loadGameDice = DiceType.valueOf(coolLine);
                    Fortune loadGameFortune2 = new Fortune(loadGameFortune, loadGameHP, loadGameArmor, loadGameHitModifier, loadGameDice);
                    fortunes.add(loadGameFortune2);
                }
            }
        }
        return;
    }

    private void loadSaveData(String saveData) {
        int counter = 0;
        Scanner saveFile = readFile(saveData);

        if (saveFile == null) {
            return;
        }

        while (saveFile.hasNextLine()) {
            Scanner theLine = new Scanner(saveFile.nextLine());
            theLine.useDelimiter(",");
            Knight theKnight = new Knight(++counter, theLine.next().trim(), theLine.nextInt(), theLine.nextInt(), theLine.nextInt(), DiceType.valueOf(theLine.next()), theLine.nextInt());
            knights.add(theKnight);
        }
    }

    public void save(String filename) {
        try {
            FileWriter saveFileWriter = new FileWriter(filename);
            for (int i = 0; i < knights.size(); i++) {
                saveFileWriter.write(knights.get(i).toCSV() + "\n");
            }
            saveFileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseGameDataLine(Scanner line) {
    }
}