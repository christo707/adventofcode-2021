package com.challenges.day1;

import com.utilities.Reader;

import java.util.*;

public class Part1 {

    public static void main(String[] args) {

        List<Integer> input = Reader.getInputAsIntegerList("src/resources/day1/input1");
        int increasing = 0;
        for(int i = 0; i < input.size() - 1; i++) {
            if (input.get(i+1) > input.get(i)) {
                increasing++;
            }
        }
        System.out.println("Total Increasing: " + increasing);
    }

}
