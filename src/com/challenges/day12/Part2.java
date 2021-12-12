package com.challenges.day12;

import com.utilities.Reader;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {

    private static final String START = "start";
    private static final String END = "end";

    public static void main(String[] args) {
        List<String> inputAsStringList = Reader.getInputAsStringList("src/resources/day12/input1");
        Map<String, Set<String>> inputAsMap = getInputAsMap(inputAsStringList);
        System.out.println("Input: " + inputAsMap);
        long totalPaths = findPos(START, inputAsMap,  Map.of(START, 2));
        System.out.println("Total Paths: " + totalPaths);

    }

    private static Map<String, Set<String>> getInputAsMap(List<String> inputAsStringList) {
        return inputAsStringList.stream()
            .map(e -> e.split("-"))
            .flatMap(e -> Stream.of(new String[]{e[0], e[1]}, new String[]{e[1], e[0]}))
            .collect(Collectors.groupingBy(e -> e[0],
                Collectors.mapping(e -> e[1],
                    Collectors.toSet())));
    }

    private static long findPos(String s, Map<String, Set<String>> inputMap,  Map<String, Integer> visited) {
        if(s.equals(END)) {
            return 1;
        }
        long paths = 0;
        Set<String> reachable = inputMap.get(s);
        for(String l : reachable){
            if((!visited.containsKey(l) || visited.get(l) < 2)
                && visited.values().stream().filter(e -> e==2).count() <= 2) {
                Map<String, Integer> newSet = new HashMap<>(visited);
                if (!l.toUpperCase().equals(l)) {
                    if (newSet.containsKey(l)) {
                        newSet.put(l, newSet.get(l) + 1);
                    } else {
                        newSet.put(l, 1);
                    }
                }
                paths += findPos(l, inputMap, newSet);
            }
        }
        return paths;
    }

}
