package ru.job4j.iterator;

import java.util.*;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {
    private List<T> data;

    private int index;
    public CyclicIterator(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (!data.isEmpty() && index == data.size()) {
            index = 0;
        }
        return !data.isEmpty();
    }

    @Override
    public T next() {
        if (data.isEmpty()) {
            throw new NoSuchElementException();
        }
        return data.get(index++);
    }
}
