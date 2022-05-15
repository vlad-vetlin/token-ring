package queue;

public interface Queue<T> {
    int size();

    boolean isEmpty();

    void add(T value);

    T poll();
}
