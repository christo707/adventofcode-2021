package com.challenges.day10;

import com.utilities.Reader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Part2 {

    private static final String openingBrackets = "<{[(";
    private static final String closingBrackets = ">}])";

    public static void main(String[] args) {
        List<String> inputAsStringList = Reader.getInputAsStringList("src/resources/day10/input1");
        List<Long> autoCompletePoints = new ArrayList<>();
        for (String in: inputAsStringList) {
            Stack<Character> enclosers = new Stack<>();
            boolean invalidFlag = false;
            for(int i = 0; i < in.length(); i++) {
                char bracket = in.charAt(i);
                if (openingBrackets.indexOf(bracket) != -1) {
                    enclosers.add(bracket);
                } else if (closingBrackets.indexOf(bracket) != -1) {
                    Character reqOpeningChar = openingBrackets.charAt(closingBrackets.indexOf(bracket));
                    Character currOpeningBracket = enclosers.pop();
                    if (currOpeningBracket != reqOpeningChar) {
                        invalidFlag = true;
                        break;
                    }
                }
            }
            if (!invalidFlag && !enclosers.empty()) {
                autoCompletePoints.add(getAutoCompletePoint(enclosers));
            }
        }

        System.out.println("Middle Point: " + getMiddlePoint(autoCompletePoints));
    }

    private static long getMiddlePoint(List<Long> autoCompletePoints) {
        Collections.sort(autoCompletePoints);
        int middle = (autoCompletePoints.size() - 1) / 2;
        return autoCompletePoints.get(middle);
    }

    private static long getAutoCompletePoint(Stack<Character> enclosers) {
        long points = 0;
        while(!enclosers.empty()) {
            char reqClosingBracket = getClosingBracket(enclosers.pop());
            points *= 5;
            points += getPoint(reqClosingBracket);
        }
        return points;
    }

    private static char getClosingBracket(char openingBracket) {
        return closingBrackets.charAt(openingBrackets.indexOf(openingBracket));
    }

    private static int getPoint(Character bracket) {
        switch (bracket) {
            case ')' : return 1;
            case ']' : return 2;
            case '}' : return 3;
            case '>' : return 4;
            default  : return 0;
        }
    }


}
