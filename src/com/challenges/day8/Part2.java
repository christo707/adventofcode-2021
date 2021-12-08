package com.challenges.day8;

import com.utilities.Reader;

import java.util.*;

public class Part2 {

    public static void main(String[] args) {
        List<String> inputList = Reader.getInputAsStringList("src/resources/day8/input1");
        int sum = 0;
        for(String in: inputList) {
            Map<Character, Character> mapOfChars = new HashMap<>();
            Map<String, Integer> mapOfActualSegmentToIntegers = new HashMap<>();
            Map<Integer, String> mapOfSegmentAndInteger = new HashMap<>();
            fillUniqueString(in, mapOfSegmentAndInteger);
            mapOfChars.put('a', findCharA(mapOfSegmentAndInteger.get(1), mapOfSegmentAndInteger.get(7)));
            String bd = findBAndD(mapOfSegmentAndInteger.get(1), mapOfSegmentAndInteger.get(4));
            String eg = findEAndG(mapOfChars.get('a'), mapOfSegmentAndInteger.get(4), mapOfSegmentAndInteger.get(8));
            List<String> segmentOfSixCharas = findAllSegmentOf6Char(in);
            fillSix(mapOfSegmentAndInteger, segmentOfSixCharas, bd, eg);
            segmentOfSixCharas.remove(mapOfSegmentAndInteger.get(6));
            fillZeroAndNine(mapOfSegmentAndInteger, segmentOfSixCharas, eg);
            mapOfChars.put('c', findCharC(mapOfSegmentAndInteger.get(8), mapOfSegmentAndInteger.get(6)));
            mapOfChars.put('f', findCharF(mapOfChars, mapOfSegmentAndInteger));
            fillCharEAndG(mapOfChars, mapOfSegmentAndInteger.get(8), mapOfSegmentAndInteger.get(9), eg);
            fillCharB(mapOfChars, mapOfSegmentAndInteger.get(0));
            fillCharD(mapOfChars, mapOfSegmentAndInteger.get(4));
            fillActualSegmentAndIntegerMap(mapOfActualSegmentToIntegers, mapOfChars);
            sum += getOutputNumber(in, mapOfActualSegmentToIntegers);

//            System.out.println("A= " + mapOfChars.get('a'));
//            System.out.println("B= " + mapOfChars.get('b'));
//            System.out.println("C= " + mapOfChars.get('c'));
//            System.out.println("D= " + mapOfChars.get('d'));
//            System.out.println("E= " + mapOfChars.get('e'));
//            System.out.println("F= " + mapOfChars.get('f'));
//            System.out.println("G= " + mapOfChars.get('g'));
        }
        System.out.println("SUM = " + sum);
    }

    private static void fillActualSegmentAndIntegerMap(Map<String, Integer> mapOfActualSegmentToIntegers,
        Map<Character, Character> mapOfChars) {
        String actualZero = mapOfChars.get('a').toString() + mapOfChars.get('b') + mapOfChars.get('c') + mapOfChars.get('e')
            + mapOfChars.get('f') + mapOfChars.get('g');
        String actualOne = mapOfChars.get('c').toString() + mapOfChars.get('f');
        String actualTwo = mapOfChars.get('a').toString() + mapOfChars.get('c') + mapOfChars.get('d') + mapOfChars.get('e')
            + mapOfChars.get('g');
        String actualThree = mapOfChars.get('a').toString() + mapOfChars.get('c') + mapOfChars.get('d') + mapOfChars.get('f')
            + mapOfChars.get('g');
        String actualFour = mapOfChars.get('b').toString() + mapOfChars.get('c') + mapOfChars.get('d') + mapOfChars.get('f');
        String actualFive = mapOfChars.get('a').toString() + mapOfChars.get('b') + mapOfChars.get('d') + mapOfChars.get('f')
            + mapOfChars.get('g');
        String actualSix = mapOfChars.get('a').toString() + mapOfChars.get('b') + mapOfChars.get('d') + mapOfChars.get('e')
            + mapOfChars.get('f') + mapOfChars.get('g');
        String actualSeven = mapOfChars.get('a').toString() + mapOfChars.get('c') + mapOfChars.get('f');
        String actualEight = mapOfChars.get('a').toString() + mapOfChars.get('b') + mapOfChars.get('c') + mapOfChars.get('d')
            + mapOfChars.get('e') + mapOfChars.get('f') + mapOfChars.get('g');
        String actualNine = mapOfChars.get('a').toString() + mapOfChars.get('b') + mapOfChars.get('c') + mapOfChars.get('d')
             + mapOfChars.get('f') + mapOfChars.get('g');
        mapOfActualSegmentToIntegers.put(sortString(actualZero), 0);
        mapOfActualSegmentToIntegers.put(sortString(actualOne), 1);
        mapOfActualSegmentToIntegers.put(sortString(actualTwo), 2);
        mapOfActualSegmentToIntegers.put(sortString(actualThree), 3);
        mapOfActualSegmentToIntegers.put(sortString(actualFour), 4);
        mapOfActualSegmentToIntegers.put(sortString(actualFive), 5);
        mapOfActualSegmentToIntegers.put(sortString(actualSix), 6);
        mapOfActualSegmentToIntegers.put(sortString(actualSeven), 7);
        mapOfActualSegmentToIntegers.put(sortString(actualEight), 8);
        mapOfActualSegmentToIntegers.put(sortString(actualNine), 9);

    }

    private static int getOutputNumber(String input, Map<String, Integer> mapOfActualSegmentToIntegers) {
        String[] output = input.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = 11; i < 15; i++) {
            String out = sortString(output[i]);
            sb.append(mapOfActualSegmentToIntegers.get(out));
        }
        return Integer.parseInt(sb.toString());
    }

    private static void fillCharD(Map<Character, Character> mapOfChars, String four) {
        for(int i = 0; i < four.length(); i++) {
            if(!mapOfChars.containsValue(four.charAt(i))) {
                mapOfChars.put('d', four.charAt(i));
                break;
            }
        }
    }

    private static void fillCharB(Map<Character, Character> mapOfChars, String zero) {
        for(int i = 0; i < zero.length(); i++) {
            if(!mapOfChars.containsValue(zero.charAt(i))) {
                mapOfChars.put('b', zero.charAt(i));
                break;
            }
        }
    }

    private static void fillCharEAndG(Map<Character, Character> mapOfChars, String eight, String nine, String eg) {
        for(int i = 0; i < 7; i++) {
            if(nine.indexOf(eight.charAt(i)) == -1) {
                mapOfChars.put('e', eight.charAt(i));
                break;
            }
        }
        if(eg.charAt(0) == mapOfChars.get('e')) {
            mapOfChars.put('g', eg.charAt(1));
        } else {
            mapOfChars.put('g', eg.charAt(0));
        }
    }

    private static char findCharF(Map<Character, Character> mapOfChars, Map<Integer, String> mapOfSegmentAndInteger) {
        return mapOfSegmentAndInteger.get(1).charAt(0) == mapOfChars.get('c') ?
            mapOfSegmentAndInteger.get(1).charAt(1) :
            mapOfSegmentAndInteger.get(1).charAt(0);
    }

    private static void fillZeroAndNine(Map<Integer, String> mapOfSegmentAndInteger,
        List<String> segmentOfSixCharas, String eg) {
        String str1 = segmentOfSixCharas.get(0);
        if(str1.indexOf(eg.charAt(0)) != -1 && str1.indexOf(eg.charAt(1)) != -1) {
            mapOfSegmentAndInteger.put(9, segmentOfSixCharas.get(1));
            mapOfSegmentAndInteger.put(0, str1);
        } else {
            mapOfSegmentAndInteger.put(9, str1);
            mapOfSegmentAndInteger.put(0, segmentOfSixCharas.get(1));
        }
    }

    private static char findCharC(String eight, String six) {
        for(int i = 0; i < 7; i++) {
            if(six.indexOf(eight.charAt(i)) == -1) {
                return eight.charAt(i);
            }
        }
        return 'c';
    }

    private static void fillSix(Map<Integer, String> mapOfSegmentAndInteger, List<String> segmentOfSixCharas,
        String bd, String eg) {
        for(String str: segmentOfSixCharas) {
            String sortedStr = sortString(str);
            if(sortedStr.indexOf(bd.charAt(0)) != -1
                && sortedStr.indexOf(bd.charAt(1)) != -1
                && sortedStr.indexOf(eg.charAt(0)) != -1
                && sortedStr.indexOf(eg.charAt(1)) != -1) {
                mapOfSegmentAndInteger.put(6, sortedStr);
                return;
            }
        }
    }

    private static List<String> findAllSegmentOf6Char(String input) {
        List<String> sixCharSegList = new ArrayList<>();
        String[] output = input.split(" ");
        for(int i = 0; i < 10; i++) {
            String out = sortString(output[i]);
            if(out.length() == 6) {
                sixCharSegList.add(out);
            }
        }
        return sixCharSegList;
    }

    private static String findEAndG(char a, String four, String eight) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 7; i++) {
            if(four.indexOf(eight.charAt(i)) == -1 && eight.charAt(i) != a) {
                sb.append(eight.charAt(i));
            }
        }
        return sortString(sb.toString());
    }

    private static String findBAndD(String one, String four) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 4; i++) {
            if(one.indexOf(four.charAt(i)) == -1) {
                sb.append(four.charAt(i));
            }
        }
        return sortString(sb.toString());
    }

    private static char findCharA(String one, String seven) {
        for(int i = 0; i < 3; i++) {
            if(one.indexOf(seven.charAt(i)) == -1) {
                return seven.charAt(i);
            }
        }
        return 'a';
    }

    private static void fillUniqueString(String input, Map<Integer, String> mapOfSegmentAndInteger) {
            String[] output = input.split(" ");
            for(int i = 0; i < 10; i++) {
                String out = sortString(output[i]);
                if(out.length() == 2) {
                    mapOfSegmentAndInteger.put(1, out);
                } else if(out.length() == 3) {
                    mapOfSegmentAndInteger.put(7, out);
                } else if(out.length() == 4) {
                    mapOfSegmentAndInteger.put(4, out);
                } else if(out.length() == 7) {
                    mapOfSegmentAndInteger.put(8, out);
                }
            }
    }

    private static String sortString(String str) {
        char []arr = str.toCharArray();
        Arrays.sort(arr);
        return String.valueOf(arr);
    }

}
