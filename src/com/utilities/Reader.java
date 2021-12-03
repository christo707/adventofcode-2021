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

}
