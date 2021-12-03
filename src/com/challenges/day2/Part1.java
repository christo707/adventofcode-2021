package com.challenges.day2;

import com.utilities.Reader;

import java.util.List;

public class Part1 {

    public static void main(String[] args) {

        List<String> input = Reader.getInputAsStringList("src/resources/day2/input1");
        int distance = 0;
        int depth = 0;
        for (String s : input) {
            String[] in = s.split(" ");
            switch (in[0]) {
                case "forward":
                    distance += Integer.parseInt(in[1]);
                    break;
                case "up":
                    depth -= Integer.parseInt(in[1]);
                    break;
                case "down":
                    depth += Integer.parseInt(in[1]);
                    break;
                default: break;
            }
        }
        System.out.println("Result: " + distance * depth);
    }

}
