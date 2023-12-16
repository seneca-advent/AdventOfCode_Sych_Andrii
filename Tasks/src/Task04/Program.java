package Task04;

import General.FileParser;

import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser("./Task04/input.txt");
        ArrayList<String> lines = fileParser.getLines();

        int count = 0;
        for (String line :
                lines) {
            Card card = new Card(line);
            count += countOriginalAndCopies(lines, card);
        }
        System.out.println(count);
    }

    private static int countOriginalAndCopies(List<String> lines, Card card) {
        int count = 1;
        List<Integer> copyIds = card.getCopyCardIDs();
        for (int id :
                copyIds) {
            count += countOriginalAndCopies(lines, new Card(lines.get(id - 1)));
        }
        return count;
    }
}
