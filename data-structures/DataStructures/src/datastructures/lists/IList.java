package datastructures.lists;

public interface IList<T> extends Iterable<T> {

    void add(T elem);

    void add(int index, T elem);

    T remove(int index);

    T set(int index, T elem);

    T get(int index);

    boolean isEmpty();

    int size();
}
