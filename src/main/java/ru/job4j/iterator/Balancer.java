package ru.job4j.iterator;

import java.util.*;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        while (source.hasNext()) {
            for (ArrayList nod : nodes) {
                nod.add(source.next());
                if (!source.hasNext()) {
                    break;
                }
            }
        }
    }
}
