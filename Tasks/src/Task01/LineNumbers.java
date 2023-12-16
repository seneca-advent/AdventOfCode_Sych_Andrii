package Task01;

import General.Tuple;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

class LineNumber {
    private int n;
    private String line;
    private final static HashMap<String, Integer> WORD_NUMBERS = new HashMap<>();

    static {
        WORD_NUMBERS.put("one", 1);
        WORD_NUMBERS.put("two", 2);
        WORD_NUMBERS.put("three", 3);
        WORD_NUMBERS.put("four", 4);
        WORD_NUMBERS.put("five", 5);
        WORD_NUMBERS.put("six", 6);
        WORD_NUMBERS.put("seven", 7);
        WORD_NUMBERS.put("eight", 8);
        WORD_NUMBERS.put("nine", 9);
    }

    LineNumber(String line) {
        this.line = line;
        n += getTensPlace() * 10;
        n += getOnesPlace();
    }

    int getNumberFromLine() {
        return n;
    }

    private int getTensPlace() {
        var digitComponent = getFirstDigitComponent();
        var wordComponent = getFirstWordComponent();

        if(wordComponent == null) {
            return digitComponent.value;
        }

        if(digitComponent == null) {
            return WORD_NUMBERS.get(wordComponent.value);
        }

        return digitComponent.index < wordComponent.index ? digitComponent.value : WORD_NUMBERS.get(wordComponent.value);
    }

    private NumberComponent<Integer> getFirstDigitComponent() {
        NumberComponent<Integer> digitComponent = new NumberComponent<>();
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                digitComponent.index = i;
                digitComponent.value = Integer.parseInt(String.valueOf(line.charAt(i)));
                break;
            }
        }
        return digitComponent;
    }

    private NumberComponent<String> getFirstWordComponent() {
        return WORD_NUMBERS.keySet().stream().map(key -> {
                    var numberComponent = new NumberComponent<String>();
                    numberComponent.value = key;
                    numberComponent.index = line.indexOf(key);
                    return numberComponent;
                }).filter(component -> component.index != -1)
                .min(Comparator.comparingInt(component -> component.index))
                .orElse(null);
    }

    private int getOnesPlace() {
        var digitComponent = getLastDigitComponent();
        var wordComponent = getLastWordComponent();

        if(wordComponent == null) {
            return digitComponent.value;
        }

        if(digitComponent == null) {
            return WORD_NUMBERS.get(wordComponent.value);
        }

        return digitComponent.index > wordComponent.index ? digitComponent.value : WORD_NUMBERS.get(wordComponent.value);
    }

    private NumberComponent<String> getLastWordComponent() {
        return WORD_NUMBERS.keySet().stream().map(key -> {
                    var numberComponent = new NumberComponent<String>();
                    numberComponent.value = key;
                    numberComponent.index = line.lastIndexOf(key);
                    return numberComponent;
                }).filter(component -> component.index != -1)
                .max(Comparator.comparingInt(component -> component.index))
                .orElse(null);
    }

    private NumberComponent<Integer> getLastDigitComponent() {
        NumberComponent<Integer> digitComponent = new NumberComponent<>();
        for (int i = line.length() - 1; i >= 0; i--) {
            if (Character.isDigit(line.charAt(i))) {
                digitComponent.index = i;
                digitComponent.value = Integer.parseInt(String.valueOf(line.charAt(i)));
                break;
            }
        }
        return digitComponent;
    }

    private class NumberComponent<T> {
        public T value;
        public int index;
    }
}