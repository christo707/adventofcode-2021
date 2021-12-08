package com.challenges.day8;

import com.utilities.Reader;
import java.util.List;

public class Part1 {

    public static void main(String[] args) {
        List<String> inputList = Reader.getInputAsStringList("src/resources/day8/input1");
        int required = 0;
        for(String input: inputList) {
            String[] output = input.split(" ");
            for(int i = 11; i < 15; i++) {
                String out = output[i];
                if(out.length() == 2
                    || out.length() == 3
                    || out.length() == 4
                    || out.length() == 7) {
                    required++;
                }
            }
        }
        System.out.println("Result: " + required);
    }

}
