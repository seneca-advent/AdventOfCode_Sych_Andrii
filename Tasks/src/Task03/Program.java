package Task03;

import General.FileParser;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser("./Task03/input.txt");
        ArrayList<String> lines = fileParser.getLines();

        Schema schema = new Schema(lines);
        System.out.println(schema.extractGearsRatioSum());
    }
}
