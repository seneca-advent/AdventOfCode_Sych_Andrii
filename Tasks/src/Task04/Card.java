package Task04;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Card {
    private final List<Integer> winningNumbers;
    private final List<Integer> yourNumbers;
    private final int id;
    private static final char DELIMITOR = '|';
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

    Card(String line) {
        id = parseID(line);
        winningNumbers = parseWinningNumbers(line);
        yourNumbers = parseYourNumbers(line);
    }

    List<Integer> getCopyCardIDs() {
        List<Integer> copies = new ArrayList<>();
        long winningCards = yourNumbers.stream().filter(winningNumbers::contains).count();
        for (int i = 0; i < winningCards; i++) {
            copies.add(i + 1 + id);
        }
        return copies;
    }

    int getPoints() {
        int res = 0;
        for (var yourNumber :
                yourNumbers) {
            if(winningNumbers.contains(yourNumber)) {
                res = res == 0 ? 1 : res * 2;
            }
        }
        return res;
    }

    private int parseID(String line) {
        Matcher numberMatcher = NUMBER_PATTERN.matcher(line);
        numberMatcher.find();
        return Integer.parseInt(numberMatcher.group());
    }

    private List<Integer> parseYourNumbers(String line) {
        return parseNumbers(line, line.indexOf(':') + 1, line.indexOf(DELIMITOR));
    }

    private List<Integer> parseWinningNumbers(String line) {
        return parseNumbers(line, line.indexOf(DELIMITOR) + 1);
    }

    private List<Integer> parseNumbers(String str, int from, int to) {
        List<Integer> yourNumbers = new ArrayList<>();
        String yourNumbersSubstring = to == -1 ? str.substring(from) : str.substring(from, to);
        Matcher numberMatcher = NUMBER_PATTERN.matcher(yourNumbersSubstring);

        while(numberMatcher.find()) {
            yourNumbers.add(Integer.parseInt(numberMatcher.group()));
        }
        return yourNumbers;
    }

    private List<Integer> parseNumbers(String str, int from) {
        return parseNumbers(str, from, -1);
    }
}
