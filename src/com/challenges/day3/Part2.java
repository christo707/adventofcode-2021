package com.challenges.day3;

import com.utilities.Reader;

import java.util.ArrayList;
import java.util.List;

public class Part2 {

    public static void main(String[] args) {

        List<String> input = Reader.getInputAsStringList("src/resources/day3/input1");
        List<String> inputForOxygen = new ArrayList<>(input);
        List<String> inputForCO2 = new ArrayList<>(input);
        int size = input.get(0).length();
        for(int i = 0; i < size; i++) {
            int mostSignificantBitOxygen = findMostSignificantBit(inputForOxygen, i, false);
            filterInputs(inputForOxygen, i, cleanSigBitForOxygen(mostSignificantBitOxygen));
            if (inputForOxygen.size() == 1) {
                break;
            }
        }
        for(int i = 0; i < size; i++) {
            int mostSignificantBitCO2 = findMostSignificantBit(inputForCO2, i, true);
            filterInputs(inputForCO2, i, cleanSigBitForCO2(mostSignificantBitCO2));
            if (inputForCO2.size() == 1) {
                break;
            }
        }

        System.out.println("Result: " + ((Integer.parseInt(inputForOxygen.get(0),2)) *
             (Integer.parseInt(inputForCO2.get(0),2))));

    }

    private static void filterInputs(final List<String> input, final int index, final int cleanSigBit) {
        input.removeIf(str -> Character.getNumericValue(str.charAt(index)) != cleanSigBit);
    }

    private static int cleanSigBitForOxygen(final int mostSignificantBit) {
        if (mostSignificantBit == -1) {
            return 1;
        } else {
            return mostSignificantBit;
        }
    }

    private static int cleanSigBitForCO2(final int mostSignificantBit) {
        if (mostSignificantBit == -1) {
            return 0;
        } else {
            return mostSignificantBit;
        }
    }

    /**
     * Return most common bit. -1 if equal.
     * @param input {@link List}
     * @param index {@link int}
     * @param inverted {@link boolean}
     * @return {@link int}
     */
    private static int findMostSignificantBit(final List<String> input, final int index, final boolean inverted) {
        int bits = 0;
        for (String s : input) {
            char bit = s.charAt(index);
            if(Character.getNumericValue(bit) == 1) {
                bits++;
            } else if(Character.getNumericValue(bit) == 0) {
                bits--;
            }
        }
        if (inverted) {
            return bits > 0 ? 0 : (bits < 0) ? 1 : -1;
        }
        return bits > 0 ? 1 : (bits < 0) ? 0 : -1;
    }

}
