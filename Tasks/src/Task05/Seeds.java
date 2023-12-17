package Task05;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Seeds {
    private final List<SeedGroup> seedGroups;

    Seeds(String line) {
        seedGroups = new ArrayList<>();
        Matcher numbers = Pattern.compile("\\d+").matcher(line);
        while (numbers.find()) {
            long idStart = Long.parseLong(numbers.group());
            numbers.find();
            long range = Long.parseLong(numbers.group());
            seedGroups.add(new SeedGroup(idStart, range));
        }
    }

    List<SeedGroup> getSeedGroups() {
        return seedGroups;
    }

    class SeedGroup {
        private long from;
        private long range;

        SeedGroup(long from, long range) {
            this.from = from;
            this.range = range;
        }

        long getFrom() {
            return from;
        }

        long getRange() {
            return range;
        }
    }
}
