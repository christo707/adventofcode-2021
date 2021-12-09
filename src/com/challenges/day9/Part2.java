package com.challenges.day9;

import com.utilities.Reader;

import java.util.*;

public class Part2 {

    private static final int FLOODFILL_MARKER = -1;

    public static void main(String[] args) {
        List<String> inputAsStringList = Reader.getInputAsStringList("src/resources/day9/input1");
        int[][] inputMatrix = getInputMatrix(inputAsStringList);
        List<Integer> lowPointsBasinSizeList = getLowPointsBasizSizeList(inputMatrix);
        System.out.println("Result: " + lowPointsBasinSizeList.get(0)
            * lowPointsBasinSizeList.get(1)
            * lowPointsBasinSizeList.get(2));

    }

    private static List<Integer> getLowPointsBasizSizeList(int[][] inputMatrix) {
        List<Integer> lowPointsBasinSizeList = new ArrayList<>();
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
                int[][] copyMatrix = Arrays.stream(inputMatrix).map(int[]::clone).toArray(int[][]::new);
                int basinSize = getBasinSize(copyMatrix, i, j, inputMatrix[i][j] - 1);
                lowPointsBasinSizeList.add(basinSize);
            }
        }
        lowPointsBasinSizeList.sort(Collections.reverseOrder());
        return lowPointsBasinSizeList;
    }

    private static int getBasinSize(int[][] matrix, int i, int j, int value) {
        if (isValidPosition(matrix, i, j)) {
            if (matrix[i][j] >= value + 1) {
                int val = matrix[i][j];
                matrix[i][j] = FLOODFILL_MARKER;
                return 1
                    + getBasinSize(matrix, i + 1, j, val)
                    + getBasinSize(matrix, i - 1, j, val)
                    + getBasinSize(matrix, i, j + 1, val)
                    + getBasinSize(matrix, i, j - 1, val);
            }
        }
        return 0;
    }

    private static boolean isValidPosition(int[][] matrix, int i, int j) {
        return (i >= 0 && j >= 0 && i < matrix.length && j < matrix[i].length)
            && matrix[i][j] != FLOODFILL_MARKER
            && matrix[i][j] != 9;
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
