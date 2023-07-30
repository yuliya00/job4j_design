package ru.job4j.collection;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {
    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean end = !contains(value);
        if (end) {
            set.add(value);
        }
        return end;
    }

    @Override
    public boolean contains(T value) {
        boolean end = false;
        for (Object rsl : set) {
            if (Objects.equals(value, rsl)) {
                end = true;
                break;
            }
        }
        return end;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
