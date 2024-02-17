package ru.job4j.collection;

import java.util.Comparator;

public class DepartmentsDescComparator implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] leftStr = left.split("/");
        String[] rightStr = right.split("/");
        int size = Math.min(rightStr.length, leftStr.length);
        int result;
        for (int index = 0; index < size; index++) {
            if (index == 0) {
                result = rightStr[index].compareTo(leftStr[index]);
            } else {
                result = leftStr[index].compareTo(rightStr[index]);
            }
            if (result != 0) {
                return result;
            }
        }
        return Integer.compare(leftStr.length, rightStr.length);
    }
}