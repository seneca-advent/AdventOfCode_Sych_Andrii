package Task01;

import General.Tuple;

import java.util.HashMap;

class LineNumber {
    private int n;

    LineNumber(String line) {
        for(int i = 0; i < line.length(); ++i) {
            char c = line.charAt(i);
            if(Character.isDigit(c)) {
                n += Character.getNumericValue(c) * 10;
                break;
            }
        }
        for(int i = line.length() - 1; i >= 0; --i) {
            char c = line.charAt(i);
            if(Character.isDigit(c)) {
                n += Character.getNumericValue(c);
                break;
            }
        }
    }

    int getNumberFromLine() {
        return n;
    }
}