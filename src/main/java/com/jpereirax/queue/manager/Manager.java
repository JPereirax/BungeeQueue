package com.jpereirax.queue.manager;

import java.util.LinkedList;
import java.util.List;

public abstract class Manager<T> {

    protected List<T> elements = new LinkedList<>();

    abstract public <K> T getByKey(K k);

    abstract public <V> List<T> getByValue(V v);

    public void add(T t) {
        elements.add(t);
    }

    public void remove(T t){
        elements.remove(t);
    }

    public boolean exists(T t) {
        return elements.contains(t);
    }

    public int getPosition(T t) {
        int position = elements.indexOf(t);

        if (position != -1) {
            return position;
        } else {
            throw new IndexOutOfBoundsException("Element not found.");
        }
    }

}