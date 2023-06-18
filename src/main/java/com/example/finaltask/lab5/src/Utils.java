package com.example.finaltask.lab5.src;

import java.nio.CharBuffer;
import java.util.*;
import java.util.stream.Collectors;

class Utils {
    public static double average(List<Integer> list){
        return list.stream().mapToInt(Integer::intValue).average()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static List<String> toUpperCaseAndAddPreffix(List<String> list) {
        return list.stream().map(s -> "_new_" + s.toUpperCase()).collect(Collectors.toList());
    }

    public static List<Integer> toSquaredUnique(List<Integer> list) {
        return list.stream()
                .filter(i -> Collections.frequency(list, i) <= 1)
                .mapToInt(e -> e.intValue() * e.intValue())
                .boxed()
                .collect(Collectors.toList());
    }

    public static List<String> filterAndSortByStartingLetter(Collection<String> strings, char startingLetter) {
        return strings.stream()
                .filter(s -> s.charAt(0) == startingLetter)
                .sorted()
                .collect(Collectors.toList());
    }

    public static String getLastElementOrThrow(List<String> list) {
        return list.stream().reduce((total, second) -> second)
                .orElseThrow(() -> new IllegalArgumentException("List is empty"));
    }

    public static int getEvenOr0(int[] list) {
        return Arrays.stream(list).filter(i -> i % 2 == 0).reduce(0, Integer::sum);
    }

    public static Map<Character, List<String>> toStringMap(List<String> list) {
        return list.stream()
                .collect(Collectors.groupingBy(s -> s.charAt(0), Collectors.mapping(s -> s.substring(1), Collectors.toList())));
    }
}