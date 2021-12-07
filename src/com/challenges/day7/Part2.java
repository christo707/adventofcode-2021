package com.challenges.day7;

import com.utilities.Reader;

import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;

public class Part2 {

    public static void main(String[] args) {
        List<Integer> input = Reader.getInputAsIntegerListFromSingleString("src/resources/day7/input1", ",");
        long avg = findAverage(input);
        int fuelConsumed = 0;
        for(Integer in: input) {
            long distance = Math.abs(in - avg);
            fuelConsumed += distance * (distance + 1) / 2;
        }
        System.out.println("Fuel: " + fuelConsumed);
    }

    public static long findAverage(List<Integer> input)
    {
        double average = input
            .stream()
            .mapToDouble(a -> a)
            .sum();

        return (long)(average / input.size());
    }

}
