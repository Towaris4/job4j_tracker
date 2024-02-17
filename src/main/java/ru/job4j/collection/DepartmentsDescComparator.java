package ru.job4j.collection;

import java.util.Comparator;

public class DepartmentsDescComparator implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] leftStr = left.split("/");
        String[] rightStr = right.split("/");
        int result = rightStr[0].compareTo(leftStr[0]);
        if (result == 0) {
            result = left.compareTo(right);
        }
        return result;
    }
}