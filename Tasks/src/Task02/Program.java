package Task02;

import General.FileParser;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser("./Task02/input.txt");
        ArrayList<String> lines = fileParser.getLines();
        int idSum = 0;
        var maxCubesPerGame = new Cubes(13, 14, 12);
        for (var line :
                lines) {
            Game game = new Game(line);
            var rounds = game.getRounds();
            var invalidRoundPresent = rounds.stream().filter((r) -> {
                return r.getBlueCubes() > maxCubesPerGame.getBlueCubes() ||
                        r.getGreenCubes() > maxCubesPerGame.getGreenCubes() ||
                        r.getRedCubes() > maxCubesPerGame.getRedCubes();
            });
            if (invalidRoundPresent.count() == 0) {
                idSum += game.getID();
            }
        }
        System.out.println(idSum);
    }
}
