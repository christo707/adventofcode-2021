package com.challenges.day5;

import com.utilities.Reader;

import java.util.ArrayList;
import java.util.List;

public class Part2 {

    static class Coord {
        int x;
        int y;

        private Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        String filename = "src/resources/day5/input1";
        List<Coord> boundaries = new ArrayList<>();
        List<String> input = Reader.getInputAsStringList(filename);
        List<List<Coord>> processedInput = getProcessedInput(input, boundaries);
        int planeLength = boundaries.get(1).x - boundaries.get(0).x + 1;
        int planeWidth  = boundaries.get(1).y - boundaries.get(0).y + 1;
        int[][] plane = new int[planeWidth][planeLength];
        int result = processPlane(plane, processedInput, boundaries);
        System.out.println(result);
    }

    private static int  processPlane(final int[][] plane, final List<List<Coord>> processedInput,
        final List<Coord> boundaries) {
        int result = 0;
        for(List<Coord> line: processedInput) {
            int normalizedX1 = line.get(0).x - boundaries.get(0).x;
            int normalizedY1 = line.get(0).y - boundaries.get(0).y;
            int normalizedX2 = line.get(1).x - boundaries.get(0).x;
            int normalizedY2 = line.get(1).y - boundaries.get(0).y;
            if (normalizedX1 == normalizedX2) {
                int min = Math.min(normalizedY1, normalizedY2);
                int max = Math.max(normalizedY1, normalizedY2);
                for(int i = min; i <= max; i++) {
                    plane[i][normalizedX1]++;
                    if(plane[i][normalizedX1] == 2) {
                        result++;
                    }
                }
            } else if (normalizedY1 == normalizedY2) {
                int min = Math.min(normalizedX1, normalizedX2);
                int max = Math.max(normalizedX1, normalizedX2);
                for(int i = min; i <= max; i++) {
                    plane[normalizedY1][i]++;
                    if(plane[normalizedY1][i] == 2) {
                        result++;
                    }
                }
            } else if (checkAt45Degree(normalizedX1, normalizedX2, normalizedY1, normalizedY2)) {
                int slope = getSlope(normalizedX1, normalizedX2, normalizedY1, normalizedY2);
                if (normalizedX1 <= normalizedX2) {
                    while (normalizedX1 <= normalizedX2) {
                        plane[normalizedY1][normalizedX1]++;
                        if(plane[normalizedY1][normalizedX1] == 2) {
                            result++;
                        }
                        normalizedX1++;
                        normalizedY1 += slope;
                    }
                } else if (normalizedX1 > normalizedX2) {
                    while (normalizedX1 >= normalizedX2) {
                        plane[normalizedY2][normalizedX2]++;
                        if(plane[normalizedY2][normalizedX2] == 2) {
                            result++;
                        }
                        normalizedX2++;
                        normalizedY2 += slope;
                    }
                }

            }
        }
        return result;
    }

    private static boolean checkAt45Degree(int normalizedX1, int normalizedX2, int normalizedY1, int normalizedY2) {
        return Math.abs((normalizedY2 - normalizedY1) / (normalizedX2 - normalizedX1)) == 1;
    }

    private static int getSlope(int normalizedX1, int normalizedX2, int normalizedY1, int normalizedY2) {
        return (normalizedY2 - normalizedY1) / (normalizedX2 - normalizedX1);
    }

    private static List<List<Coord>> getProcessedInput(final List<String> input, List<Coord> boundaries) {
        List<List<Coord>> processedInput = new ArrayList<>();
        Integer minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        for(String in : input) {
            List<Coord> line = new ArrayList<>();
            String[] inStr = in.split(" ");
            Coord coord1 = getCoordFromStrInput(inStr[0]);
            Coord coord2 = getCoordFromStrInput(inStr[2]);
            int minX1 = Math.min(coord1.x, coord2.x);
            minX = Math.min(minX1, minX);
            int minY1 = Math.min(coord1.y, coord2.y);
            minY = Math.min(minY1, minY);
            int maxX1 = Math.max(coord1.x, coord2.x);
            maxX = Math.max(maxX1, maxX);
            int maxY1 = Math.max(coord1.y, coord2.y);
            maxY = Math.max(maxY1, maxY);
            line.add(coord1);
            line.add(coord2);
            processedInput.add(line);
        }
        boundaries.add(new Coord(minX, minY));
        boundaries.add(new Coord(maxX, maxY));
        return processedInput;
    }

    private static Coord getCoordFromStrInput(String s) {
        String[] coordStr1 = s.split(",");
        return new Coord(Integer.parseInt(coordStr1[0]), Integer.parseInt(coordStr1[1]));
    }
}

