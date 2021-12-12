package com.challenges.day12;

import com.utilities.Reader;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {

    private static final String START = "start";
    private static final String END = "end";

    public static void main(String[] args) {
        List<String> inputAsStringList = Reader.getInputAsStringList("src/resources/day12/input1");
        Map<String, Set<String>> inputAsMap = getInputAsMap(inputAsStringList);
        long totalPaths = findPos(START, inputAsMap, Set.of(START));
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

    private static long findPos(String s, Map<String, Set<String>> inputMap, Set<String> visited) {
        if(s.equals(END)) {
            return 1;
        }
        long paths = 0;
        Set<String> reachable = inputMap.get(s);
        for(String l : reachable){
            if(!visited.contains(l)) {
                Set<String> newSet = new HashSet<>(visited);
                if (!l.toUpperCase().equals(l)) {
                    newSet.add(l);
                }
                paths += findPos(l, inputMap, newSet);
            }
        }
        return paths;
    }

}
