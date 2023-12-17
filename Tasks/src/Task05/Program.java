package Task05;

import General.FileParser;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Program {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser("./Task05/input.txt");
        ArrayList<String> lines = fileParser.getLines();

        Seeds seeds = new Seeds(lines.get(0));
        MapsParser mapsParser = new MapsParser();
        List<Map> maps = mapsParser.parseMaps(lines);
        long lowestLocationID = Long.MAX_VALUE;

        for (Seeds.SeedGroup seedGroup :
                seeds.getSeedGroups()) {

            long from = seedGroup.getFrom();
            long range = seedGroup.getRange();
            long sourceID;

            for (long i = from; i < range + from; i++) {
                sourceID = i;
                for (Map map : maps) {
                    sourceID = map.getDestinationID(sourceID);
                }

                if(lowestLocationID > sourceID) {
                    lowestLocationID = sourceID;
                }
            }
        }

        System.out.println(lowestLocationID);
    }
}
