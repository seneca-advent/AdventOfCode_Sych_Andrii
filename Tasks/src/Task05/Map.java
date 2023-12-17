package Task05;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Map {
    private final List<MapRule> rules;
    Map(List<MapRule> rules) {
        this.rules = rules;
    }

    long getDestinationID(long sourceID) {
        for (MapRule rule : rules) {
            if (rule.sourceBeginning <= sourceID && rule.sourceBeginning + rule.range > sourceID) {
                return sourceID - rule.sourceBeginning + rule.destinationBeginning;
            }
        }
        return sourceID;
    }
    static class MapRule {
        private final long destinationBeginning;
        private final long sourceBeginning;
        private final long range;
        private MapRule(String line) {
            List<Long> numbers = new ArrayList<>();
            Matcher lineNumbers = Pattern.compile("\\d+").matcher(line);

            while(lineNumbers.find()) {
                numbers.add(Long.parseLong(lineNumbers.group()));
            }

            this.destinationBeginning = numbers.get(0);
            this.sourceBeginning = numbers.get(1);
            this.range = numbers.get(2);
        }

        static MapRule create(String line) {
            Matcher lineNumbers = Pattern.compile("^\\d+\\s+\\d+\\s+\\d+\n?$").matcher(line);
            if(lineNumbers.matches()) {
                return new MapRule(line);
            }
            return null;
        }

        long getDestinationBeginning() {
            return destinationBeginning;
        }
        long getSourceBeginning() {
            return sourceBeginning;
        }
        long getRange() {
            return range;
        }
    }
}
