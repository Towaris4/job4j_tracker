package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] oneNumber = left.split(". ");
        String[] twoNumber = right.split(". ");
        return Integer.compare(Integer.parseInt(oneNumber[0]),
                Integer.parseInt(twoNumber[0]));
    }
}