package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Trsv1vn@gmail.com", "Tarasov Ivan Vladimirovich");
        map.put("perfectworld@gmail.com", "Petrov Abdula Andeevich");
        for (String key : map.keySet()) {
            String value = map.get(key);
            System.out.println(key + " = " + value);
        }
    }
}
