package com.challenges.day3;

import com.utilities.Reader;

import java.util.Arrays;
import java.util.List;

public class Part1 {

    public static void main(String[] args) {

        List<String> input = Reader.getInputAsStringList("src/resources/day3/input1");
        int size = input.get(0).length();
        int[] bits = new int[size];
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        Arrays.fill(bits, 0);
        for (String s : input) {
            char[] arr = s.toCharArray();
            for(int i = 0; i < size; i++) {
                if(Character.getNumericValue(arr[i]) == 1) {
                    bits[i]++;
                } else if(Character.getNumericValue(arr[i]) == 0) {
                    bits[i]--;
                }

            }
        }
        for(int i = 0; i < size; i++) {
            if (bits[i] < 0) {
                gamma.append("0");
                epsilon.append("1");
            } else {
                gamma.append("1");
                epsilon.append("0");
            }
        }
        System.out.println("Result: " + (Integer.parseInt(gamma.toString(),2) * Integer.parseInt(epsilon.toString(),2)));

    }

}
