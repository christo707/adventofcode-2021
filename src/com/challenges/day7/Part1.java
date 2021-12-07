package com.challenges.day7;

import com.utilities.Reader;

import java.util.Collections;
import java.util.List;

public class Part1 {

    public static void main(String[] args) {
        List<Integer> input = Reader.getInputAsIntegerListFromSingleString("src/resources/day7/input1", ",");
        int median = findMedian(input, input.size());
        int fuelConsumed = 0;
        for(Integer in: input) {
            fuelConsumed += Math.abs(in - median);
        }
        System.out.println("Fuel: " + fuelConsumed);
    }

    public static int findMedian(List<Integer> input, int n)
    {
        Collections.sort(input);

        if (n % 2 != 0)
            return input.get(n / 2);

        return (input.get((n - 1) / 2) + input.get(n / 2)) / 2;
    }

}
