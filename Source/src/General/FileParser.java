package General;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileParser {
    private final File filePath;
    private final static File ROOT = new File("./Source/src");
    public FileParser(String filename) {
        filePath = new File(ROOT, filename);
    }

    public ArrayList<String> getLines() {
        try
        {
            Scanner myReader = new Scanner(filePath);
            ArrayList<String> lines = new ArrayList<>();

            while (myReader.hasNextLine()) {
                lines.add(myReader.nextLine());
            }
            myReader.close();
            return lines;
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
