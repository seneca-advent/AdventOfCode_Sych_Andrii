package Task02;

import General.FileParser;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser("./Task02/input.txt");
        ArrayList<String> lines = fileParser.getLines();
        int sum = 0;
        for (var line :
                lines) {
            Game game = new Game(line);
            var rounds = game.getRounds();
            var maxRed = rounds.stream().map(Cubes::getRedCubes).max(Integer::compare).get();
            var maxBlue = rounds.stream().map(Cubes::getBlueCubes).max(Integer::compare).get();
            var maxGreen = rounds.stream().map(Cubes::getGreenCubes).max(Integer::compare).get();
            sum += maxGreen * maxBlue * maxRed;
        }
        System.out.println(sum);
    }
}
