package Task02;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Game {
    private int ID;
    private ArrayList<Cubes> rounds;

    Game(String line) {
        ID = parseID(line);
        rounds = parseRounds(line);
    }
    int getID() {
        return ID;
    }
    ArrayList<Cubes> getRounds() {
        return rounds;
    }

    private ArrayList<Cubes> parseRounds(String line) {
        var rounds = line.split(";");
        var roundsList = new ArrayList<Cubes>();
        for (var round :
                rounds) {
            roundsList.add(new Cubes(parseCubes(round, "green"), parseCubes(round, "blue"), parseCubes(round, "red")));
        }
        return roundsList;
    }

    private int parseCubes(String line, String cubeColor) {
        int count = 0;
        Pattern pattern = Pattern.compile("\\d+ " + cubeColor);
        var matcher = pattern.matcher(line);
        while (matcher.find())
            count +=Integer.parseInt(matcher.group().replaceAll("[\\D]", ""));
        return count;
    }

    private int parseID(String line) {
        Pattern idPattern = Pattern.compile("\\d+");
        var matcher = idPattern.matcher(line);
        matcher.find();
        return Integer.parseInt(matcher.group());
    }
}
