package com.challenges.day1;

import com.utilities.Reader;

import java.util.List;

public class Part2 {

    public static void main(String[] args) {

        List<Integer> input = Reader.getInputAsIntegerList("src/resources/day1/input1");
        int increasing = 0;
        for(int i = 2; i < input.size() - 1; i++) {
            int sumA = input.get(i-2) + input.get(i-1) + input.get(i);
            int sumB = input.get(i-1) + input.get(i) + input.get(i+1);
            if (sumB > sumA) {
                increasing++;
            }
        }
        System.out.println("Total Increasing: " + increasing);
    }

}
