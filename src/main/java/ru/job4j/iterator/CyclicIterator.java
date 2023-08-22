package ru.job4j.iterator;

import java.util.*;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {
    private List<T> data;

    private Iterator<T> mIterator;

    public CyclicIterator(List<T> data) {
        this.data = data;
        mIterator = data.iterator();
    }

    @Override
    public boolean hasNext() {
        if (!mIterator.hasNext()) {
            mIterator = data.iterator();
        }
        return mIterator.hasNext();
    }

    @Override
    public T next() {
        if (!mIterator.hasNext()) {
            mIterator = data.iterator();
        }
        return mIterator.next();
    }
}
