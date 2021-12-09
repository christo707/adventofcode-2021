package com.challenges.day9;

import com.utilities.Reader;

import java.util.ArrayList;
import java.util.List;

public class Part1 {

    public static void main(String[] args) {
        List<String> inputAsStringList = Reader.getInputAsStringList("src/resources/day9/input1");
        int[][] inputMatrix = getInputMatrix(inputAsStringList);
        List<Integer> lowPoints = getLowPoints(inputMatrix);
        int risk = lowPoints.stream()
            .map(point -> point + 1)
            .mapToInt(Integer::valueOf)
            .sum();
        System.out.println("Risk: " + risk);

    }

    private static List<Integer> getLowPoints(int[][] inputMatrix) {
        List<Integer> lowPoints = new ArrayList<>();
        for(int i = 0; i < inputMatrix.length; i++) {
            for (int j = 0; j < inputMatrix[i].length; j++) {
                if(i > 0 && inputMatrix[i-1][j] <= inputMatrix[i][j]) {
                    continue;
                }
                if(i < inputMatrix.length - 1 && inputMatrix[i+1][j] <= inputMatrix[i][j]) {
                    continue;
                }
                if(j > 0 && inputMatrix[i][j-1] <= inputMatrix[i][j]) {
                    continue;
                }
                if(j < inputMatrix[i].length - 1 && inputMatrix[i][j+1] <= inputMatrix[i][j]) {
                    continue;
                }
                lowPoints.add(inputMatrix[i][j]);
            }
        }
        return lowPoints;
    }

    private static int[][] getInputMatrix(List<String> inputAsStringList) {
        int[][] inputMatrix =  new int[inputAsStringList.size()][inputAsStringList.get(0).length()];
        for(int i = 0; i < inputAsStringList.size(); i++) {
            for(int j = 0; j < inputAsStringList.get(i).length(); j++) {
                inputMatrix[i][j] = Integer.parseInt(String.valueOf(inputAsStringList.get(i).charAt(j)));
            }
        }
        return inputMatrix;
    }

}
