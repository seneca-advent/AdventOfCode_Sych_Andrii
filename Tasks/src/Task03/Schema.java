package Task03;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Schema {
    private final List<String> lines;
    private static final Pattern SYMBOL_REGEX = Pattern.compile("[^.\\d]");
    private static final Pattern GEAR_REGEX = Pattern.compile("\\*");
    private static final Pattern NUMBER_REGEX = Pattern.compile("\\d+");

    Schema(List<String> lines) {
        this.lines = lines;
    }

    int extractNumbersSum() {
        int sum = 0;

        for (int i = 0; i < lines.size(); ++i) {
            final String line = lines.get(i);
            final var lineMatcher = getNumberMatcherForLine(line);
            while(lineMatcher.find()) {
                if(doesLineHaveSymbol(line, lineMatcher) ||
                   doVerticalLinesHaveSymbol(i - 1, i + 1, lineMatcher)) {
                    sum += Integer.parseInt(lineMatcher.group());
                }
            }
        }
        return sum;
    }

    int extractGearsRatioSum() {
        int sum = 0;
        for (int i = 0; i < lines.size(); ++i) {
            final String line = lines.get(i);
            final var gearMatcher = getGearMatcherForLine(line);
            while(gearMatcher.find()) {
                var lineNumbers = getLineNumbers(line, gearMatcher);
                var topNumbers = getVerticalNumbers(i - 1, i + 1, gearMatcher);
                var allNumbersStream = Stream.concat(lineNumbers.stream(), topNumbers.stream()).filter(n -> n != -1);
                var allNumbersList = allNumbersStream.collect(Collectors.toList());
                System.out.println(allNumbersList);
                if (allNumbersList.size() == 2) {
                    sum += allNumbersList.get(0) * allNumbersList.get(1);
                }
            }
        }
        return sum;
    }

    private List<Integer> getVerticalNumbers(int topLineIndex, int bottomLineIndex, Matcher gearMatcher) {
        List<Integer> foundNumbers = new ArrayList<>();
        if(topLineIndex != -1) {
            foundNumbers = Stream.concat(foundNumbers.stream(), getVerticalLineNumbers(lines.get(topLineIndex), gearMatcher).stream()).toList();
        }
        if(bottomLineIndex != lines.size()) {
            foundNumbers = Stream.concat(foundNumbers.stream(), getVerticalLineNumbers(lines.get(bottomLineIndex), gearMatcher).stream()).toList();
        }
        return foundNumbers;
    }

    private List<Integer> getVerticalLineNumbers(String topLine, Matcher gearMatcher) {
        List<Integer> numbers = new ArrayList<>();
        int gearIndex = gearMatcher.start();

        Matcher numberMatcher = getNumberMatcherForLine(topLine);
        while(numberMatcher.find()) {
            int numberStart = numberMatcher.start();
            int numberEnd = numberMatcher.end() - 1;
            final boolean numberIsDiagonal = numberStart - 1 == gearIndex || numberEnd + 1 == gearIndex;
            final boolean numberBeginsOrEnds = numberEnd == gearIndex || numberStart == gearIndex;
            final boolean gearIndexIsInBetween = numberStart < gearIndex && gearIndex < numberEnd;

            if(numberIsDiagonal || numberBeginsOrEnds || gearIndexIsInBetween) {
                numbers.add(Integer.parseInt(numberMatcher.group()));
            }
        }
        return numbers;
    }

    private List<Integer> getLineNumbers(String line, Matcher gearMatcher) {
        List<Integer> foundNumbers = new ArrayList<>();
        foundNumbers.add(getRightNumber(line, gearMatcher));
        foundNumbers.add(getLeftNumber(line, gearMatcher));
        return foundNumbers;
    }

    private int getLeftNumber(String line, Matcher gearMatcher) {
        int leftIndex = gearMatcher.start() - 1;
        if(leftIndex == -1 || !Character.isDigit(line.charAt(leftIndex)))
            return -1;

        Matcher numberMatcher = NUMBER_REGEX.matcher(line.substring(0, leftIndex + 1));
        String s = null;

        while (numberMatcher.find()) {
            s = numberMatcher.group();
        }

        if(s == null)
            return -1;

        return Integer.parseInt(s);
    }

    private int getRightNumber(String line, Matcher gearMatcher) {
        int rightIndex = gearMatcher.end();
        Matcher numberMatcher;
        if(rightIndex == line.length() || !Character.isDigit(line.charAt(rightIndex)) || !(numberMatcher = NUMBER_REGEX.matcher(line.substring(rightIndex))).find())
            return -1;
        return Integer.parseInt(numberMatcher.group());
    }

    private boolean doesLineHaveSymbol(String line, Matcher lineMatcher) {
        return rightHasSymbol(line, lineMatcher) || leftHasSymbol(line, lineMatcher);
    }

    private boolean doVerticalLinesHaveSymbol(int topLineIndex, int bottomLineIndex, Matcher lineMatcher) {
        return (topLineIndex != -1 && verticalLineHasSymbol(lines.get(topLineIndex), lineMatcher)) ||
                (bottomLineIndex != lines.size() && verticalLineHasSymbol(lines.get(bottomLineIndex), lineMatcher));
    }

    private boolean verticalLineHasSymbol(String topLine, Matcher lineNumber) {
        int numberEnd = lineNumber.end();
        int numberStart = lineNumber.start();
        int searchStart = numberStart == 0 ? numberStart : numberStart - 1;
        int searchEnd = numberEnd == topLine.length() ? numberEnd : numberEnd + 1;
        return SYMBOL_REGEX.matcher(topLine.substring(searchStart, searchEnd)).find();
    }

    private boolean rightHasSymbol(String line, Matcher lineNumber) {
        int rightIndex = lineNumber.end();
        return rightIndex != line.length() && SYMBOL_REGEX.matcher(line.substring(rightIndex, rightIndex + 1)).matches();
    }

    private boolean leftHasSymbol(String line, Matcher lineNumber) {
        int leftIndex = lineNumber.start() - 1;
        return leftIndex != -1 && SYMBOL_REGEX.matcher(line.substring(leftIndex, leftIndex + 1)).matches();
    }

    private Matcher getNumberMatcherForLine(String line) {
        return NUMBER_REGEX.matcher(line);
    }

    private Matcher getGearMatcherForLine(String line) {
        return GEAR_REGEX.matcher(line);
    }
}
