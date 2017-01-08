package datastructures;

import datastructures.lists.ArrayBasedList;
import datastructures.lists.IList;

public class DataStructures {

    public static void main(String[] args) {
        IList<Integer> list = new ArrayBasedList<Integer>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        System.out.println(list);
    }
}
