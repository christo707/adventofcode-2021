package com.challenges.day11;

import com.utilities.Reader;

import java.util.*;
import java.util.stream.Collectors;

public class Part2 {

    private static final int WIDTH = 10;
    private static final int STEPS = 1000;

    public static void main(String[] args) {
        List<String> inputAsStringList = Reader.getInputAsStringList("src/resources/day11/input1");
        int[][] inputMatrix = Reader.getInputMatrix(inputAsStringList);
        int firstSyncFlash = findFirstSyncFlash(inputMatrix);
        System.out.println("First Sync Flash: " + firstSyncFlash);
    }

    private static int findFirstSyncFlash(int[][] inputMatrix) {
        for (int i = 1; i <= STEPS; i++) {
            Map<Integer, List<Integer>> state = new HashMap<>();
            Stack<Integer> flashStack = new Stack<>();
            fillInitialState(state, inputMatrix);
            List<Integer> flashStep = state.get(0);
            for (int j = 1; j < WIDTH; j++) {
                List<Integer> tmp = state.get(j);
                state.put(j, flashStep);
                flashStep = tmp;
            }
            refillInputMatrix(state, inputMatrix);
            flashStack.addAll(flashStep);
            while(!flashStack.isEmpty()) {
                int flashSeq = flashStack.pop();
                List<Integer> affected = getAffectedSequences(flashSeq, flashStep);
                for (Integer aff: affected) {
                    List<Integer> affPos = getPosFromSequence(aff);
                    if (inputMatrix[affPos.get(0)][affPos.get(1)]  < 9) {
                        inputMatrix[affPos.get(0)][affPos.get(1)]++;
                    } else {
                        flashStack.add(aff);
                        flashStep.add(aff);
                    }
                }
            }
            if (flashStep.size() == WIDTH * WIDTH) {
                return i;
            }
            state.put(0, flashStep);
            for(Integer flashSeq: flashStep) {
                List<Integer> pos = getPosFromSequence(flashSeq);
                inputMatrix[pos.get(0)][pos.get(1)] = 0 ;
            }
        }
        return 0;
    }

    private static void refillInputMatrix(Map<Integer, List<Integer>> state, int[][] inputMatrix) {
        for (int i = 0; i < WIDTH; i++) {
            List<Integer> sequences = state.get(i);
            for(Integer sequence: sequences) {
                List<Integer> pos = getPosFromSequence(sequence);
                inputMatrix[pos.get(0)][pos.get(1)] = i ;
            }
        }
    }

    private static List<Integer> getAffectedSequences(int flashSeq, List<Integer> flashStep) {
        List<Integer> pos = getPosFromSequence(flashSeq);
        return getAdjacentPlaces(pos.get(0), pos.get(1))
            .stream()
            .filter(adjacent -> isValidPosition(adjacent.get(0), adjacent.get(1))
                && !flashStep.contains(getSequenceFromPos(adjacent.get(0), adjacent.get(1)))
            )
            .map(adjacent -> getSequenceFromPos(adjacent.get(0), adjacent.get(1)))
            .collect(Collectors.toList());
    }

    private static List<List<Integer>> getAdjacentPlaces(int i, int j) {
        List<List<Integer>> adjacents = new ArrayList<>();
        adjacents.add(Arrays.asList(i - 1, j));
        adjacents.add(Arrays.asList(i + 1, j));
        adjacents.add(Arrays.asList(i, j - 1));
        adjacents.add(Arrays.asList(i, j + 1));
        adjacents.add(Arrays.asList(i - 1, j - 1));
        adjacents.add(Arrays.asList(i - 1, j + 1));
        adjacents.add(Arrays.asList(i + 1, j - 1));
        adjacents.add(Arrays.asList(i + 1, j + 1));
        return adjacents;
    }

    private static boolean isValidPosition(int i, int j) {
        return (i >= 0 && j >= 0 && i < WIDTH && j < WIDTH);
    }

    private static void fillInitialState(Map<Integer, List<Integer>> state, int[][] inputMatrix) {
        for (int i = 0; i < WIDTH; i++) {
            state.put(i, new ArrayList<>());
        }
        for (int i = 0; i < inputMatrix.length; i++) {
            for (int j = 0; j < inputMatrix[i].length; j++) {
                int sequence = getSequenceFromPos(i, j);
                int charge = inputMatrix[i][j];
                List<Integer> numbers = state.get(charge);
                numbers.add(sequence);
                state.put(charge, numbers);
            }
        }
    }

    private static int getSequenceFromPos(int i, int j) {
        return ((WIDTH * i) + (j));
    }

    private static List<Integer> getPosFromSequence(int sequence) {
        int i = sequence / WIDTH;
        int j = (sequence % WIDTH);
        return Arrays.asList(i, j);
    }

}
