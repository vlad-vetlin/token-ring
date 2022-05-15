package queue;

import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<T> implements Queue<T> {
    private java.util.Queue<T> values = new LinkedList();

    private Lock lock = new ReentrantLock();

    private final Condition notEmpty;

    public BlockingQueue() {
        notEmpty = lock.newCondition();
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void add(T value) {
        lock.lock();
        try {
            values.add(value);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T poll() {
        try {
            lock.lockInterruptibly();
            while (values.size() == 0)
                notEmpty.await();
            return values.poll();
        } catch (InterruptedException exception) {
            return null;
        } finally {
            lock.unlock();
        }
    }
}
