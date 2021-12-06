package com.challenges.day6;

import com.utilities.Reader;

import java.util.List;

public class Part1And2 {

    public static void main(String[] args) {
        int days = 256;
        int newFishTimer = 8;
        int fishTimer = 6;
        long[] state = new long[newFishTimer+1];
        List<Integer> fishies = Reader.getInputAsIntegerListFromSingleString("src/resources/day6/input1", ",");
        for(Integer fish: fishies) {
            state[fish] += 1;
        }
        long totalFishes = fishies.size();
        for(int i = 0; i < days; i++) {
            long tmp, lastFishes = 0;
            for(int j = newFishTimer+1; j > 0; j--) {
                tmp = state[j-1];
                state[j-1] = lastFishes;
                lastFishes = tmp;
            }
            totalFishes += lastFishes;
            state[fishTimer]+= lastFishes;
            state[newFishTimer] = lastFishes;
        }
        System.out.println("Fishes: " + totalFishes);
    }


}
