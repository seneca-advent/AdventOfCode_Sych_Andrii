package Task01;

import General.FileParser;

public class Program {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser("./Task01/inputs.txt");
        var lines = fileParser.getLines();
        int sum = 0;
        for (var line :
                lines) {
            LineNumber lineNumber = new LineNumber(line);
            sum += lineNumber.getNumberFromLine();
        }
        System.out.println(sum);
    }
}
