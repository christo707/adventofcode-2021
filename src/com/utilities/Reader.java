package com.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {

    public static List<Integer>  getInputAsIntegerList(String filename) {
        List<Integer> list = new ArrayList<>();
        Scanner s = null;
        try {
            s = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (s!= null) {
            while (s.hasNext()){
                list.add(Integer.valueOf(s.next()));
            }
            s.close();
        }
        return list;
    }

    public static List<Integer>  getInputAsIntegerListFromSingleString(String filename, String seperator) {
        List<Integer> list = new ArrayList<>();
        Scanner s = null;
        try {
            s = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (s!= null) {
            while (s.hasNextLine()){
                String[] in = s.nextLine().split(seperator);
                for(String no: in) {
                    list.add(Integer.valueOf(no));
                }
            }
            s.close();
        }
        return list;
    }

    public static List<String>  getInputAsStringList(String filename) {
        List<String> list = new ArrayList<>();
        Scanner s = null;
        try {
            s = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (s!= null) {
            while (s.hasNext()){
                list.add(s.nextLine());
            }
            s.close();
        }
        return list;
    }

    public static int[][] getInputMatrix(List<String> inputAsStringList) {
        int[][] inputMatrix =  new int[inputAsStringList.size()][inputAsStringList.get(0).length()];
        for(int i = 0; i < inputAsStringList.size(); i++) {
            for(int j = 0; j < inputAsStringList.get(i).length(); j++) {
                inputMatrix[i][j] = Integer.parseInt(String.valueOf(inputAsStringList.get(i).charAt(j)));
            }
        }
        return inputMatrix;
    }

}
