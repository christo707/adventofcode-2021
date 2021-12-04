package com.challenges.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Part2 {

    public static void main(String[] args) {
        String filename = "src/resources/day4/input1";
        List<Integer> numbers = new ArrayList<>();
        List<int[][]> boards = new ArrayList<>();
        getInput(filename, numbers, boards);

        System.out.println(process(numbers, boards));

    }

    private static int process(final List<Integer> numbers, List<int[][]> boards) {
        Set<int[][]> winners = new HashSet<>();
        for (Integer num : numbers) {
            Integer board = processBoards(boards, winners, num);
            if (board != null) {
                return board;
            }
        }
        return -1;
    }

    private static Integer processBoards(List<int[][]> boards, final Set<int[][]> winners, Integer num) {
        for (int[][] board : boards) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == num) {
                        board[i][j] = -1;
                        boolean isWinner = checkIfWinner(board, i, j);
                        if (isWinner) {
                            if (winners.size() == boards.size() - 1 && !winners.contains(board)) {
                                return getResult(board, num);
                            } else {
                                winners.add(board);
                            }

                        }
                    }
                }
            }
        }
        return null;
    }

    private static int getResult(final int[][] board, final Integer num) {
        int sum = 0;
        for (int[] ints : board) {
            for (int anInt : ints) {
                if (anInt != -1) {
                    sum += anInt;
                }
            }
        }
        return sum * num;
    }

    private static boolean checkIfWinner(final int[][] board, final int row, final int col) {
        boolean rowWinner = true;
        boolean colWinner = true;
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] != -1) {
                rowWinner = false;
            }
            if (board[i][col] != -1) {
                colWinner = false;
            }
        }
        return rowWinner || colWinner;
    }

    private static void getInput(final String filename, List<Integer> numbers, final List<int[][]> boards) {
        Scanner s = null;
        try {
            s = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (s != null) {
            if (s.hasNextLine()) {
                numbers.addAll(Arrays.stream(s.nextLine().split(","))
                    .map(Integer::parseInt).collect(Collectors.toList()));
            }

            while (s.hasNextLine()) {
                if (!s.hasNextLine()) {
                    break;
                } else {
                    s.nextLine();
                }
                int[][] matrix = null;
                if (s.hasNext()) {
                    String[] row = s.nextLine().split(" ");
                    List<Integer> row1 =
                        Arrays.stream(row).filter(n -> n != null
                            && n.matches("\\d+")).map(Integer::parseInt)
                            .collect(Collectors.toList());
                    matrix = new int[row1.size()][row1.size()];
                    for (int i = 0; i < row1.size(); i++) {
                        matrix[i] = new int[row1.size()];
                        matrix[0][i] = row1.get(i);
                    }
                }
                for (int j = 1; j < matrix.length; j++) {
                    String[] row2String = s.nextLine().split(" ");
                    List<Integer> row2 = Arrays.stream(row2String)
                        .filter(n -> n != null && n.matches("\\d+"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                    for (int i = 0; i < row2.size(); i++) {
                        matrix[j][i] = row2.get(i);
                    }
                }
                boards.add(matrix);
            }
        }
        s.close();
    }

}
