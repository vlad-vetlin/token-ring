package queue;

import java.util.LinkedList;

public class SimpleQueue <T> implements Queue<T> {
    private java.util.Queue<T> values = new LinkedList<>();

    @Override
    public synchronized int size() {
        return values.size();
    }

    @Override
    public synchronized boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public synchronized void add(T value) {
        values.add(value);
    }

    @Override
    public synchronized T poll() {
        return values.poll();
    }
}
