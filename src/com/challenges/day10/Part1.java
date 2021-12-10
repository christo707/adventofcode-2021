package com.challenges.day10;

import com.utilities.Reader;

import java.util.List;
import java.util.Stack;

public class Part1 {

    public static void main(String[] args) {
        List<String> inputAsStringList = Reader.getInputAsStringList("src/resources/day10/input1");
        String openingBrackets = "<{[(";
        String closingBrackets = ">}])";
        int sum = 0;
        for (String in: inputAsStringList) {
            Stack<Character> enclosers = new Stack<>();
            for(int i = 0; i < in.length(); i++) {
                char bracket = in.charAt(i);
                if (openingBrackets.indexOf(bracket) != -1) {
                   enclosers.add(bracket);
                } else if (closingBrackets.indexOf(bracket) != -1) {
                    Character reqOpeningChar = openingBrackets.charAt(closingBrackets.indexOf(bracket));
                    Character currOpeningBracket = enclosers.pop();
                    if (currOpeningBracket != reqOpeningChar) {
                        sum += getPoint(bracket);
                        break;
                    }
                }
            }
        }

        System.out.println("SUM: " + sum);
    }

    private static int getPoint(Character bracket) {
        switch (bracket) {
            case ')' : return 3;
            case ']' : return 57;
            case '}' : return 1197;
            case '>' : return 25137;
            default  : return 0;
        }
    }


}
