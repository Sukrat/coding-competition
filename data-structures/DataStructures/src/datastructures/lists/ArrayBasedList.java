package datastructures.lists;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayBasedList<T> implements IList<T> {

    private final int MAX_SIZE = Integer.MAX_VALUE - 8;
    private final int GROWTH_CONSTANT = 2;

    private Object[] array;
    private int size = 0;
    private int startIndex = 0;
    private int lastIndex = 0;

    public ArrayBasedList(int size) {
        array = new Object[size];
    }

    public ArrayBasedList() {
        array = new Object[GROWTH_CONSTANT];
    }

    @Override
    public void add(T elem) {
        growIfNeeded();
        addElementAt(-1, elem);
    }

    @Override
    public void add(int index, T elem) {
        rangeCheck(index);
        growIfNeeded();
        addElementAt(index, elem);
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        Object elem = removeElementAt(index);
        return getElement(elem);
    }

    @Override
    public T set(int index, T elem) {
        rangeCheck(index);
        int realIndex = getRealIndex(index);
        Object oldValue = array[realIndex];
        array[realIndex] = elem;
        return getElement(oldValue);
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return getElement(array[getRealIndex(index)]);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Iterator<T> iter = iterator();
        while (iter.hasNext()) {
            builder.append(iter.next().toString()).append(", ");
        }
        if (builder.lastIndexOf(",") > 0) {
            builder.delete(builder.lastIndexOf(","), builder.lastIndexOf(" ") + 1);
        }
        builder.append("]");
        return builder.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    public class Itr implements Iterator<T> {

        int cursor = startIndex;

        @Override
        public boolean hasNext() {
            return cursor != lastIndex;
        }

        @Override
        public T next() {
            Object elem;
            if (hasNext()) {
                elem = array[cursor];
            } else {
                throw new NoSuchElementException();
            }
            cursor = getNextIndex(cursor);
            return getElement(elem);
        }
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    }

    private T getElement(Object elem) {
        return (T) elem;
    }

    private void growIfNeeded() {
        int sizeRequired = size + 1;
        if (sizeRequired < array.length) {
            return;
        } else if (sizeRequired < MAX_SIZE) {
            int newSize = sizeRequired * GROWTH_CONSTANT;
            if (newSize > MAX_SIZE) {
                newSize = MAX_SIZE;
            }
            Object[] temp = new Object[newSize];
            for (int i = startIndex, j = 0; i != lastIndex; i = getNextIndex(i), j++) {
                temp[j] = array[i];
            }
            startIndex = 0;
            lastIndex = size;
            array = temp;
        } else {
            throw new OutOfMemoryError();
        }
    }

    //index: -1 means add at the end of the list
    private void addElementAt(int index, T element) {
        int realIndex = getRealIndex(index);
        if (index == 0) {
            startIndex = getPrevIndex(startIndex);
            array[startIndex] = element;
        } else if (index == -1) {
            array[lastIndex] = element;
            lastIndex = getNextIndex(lastIndex);
        } else {
            shiftRight(realIndex);
            array[realIndex] = element;
            lastIndex = getNextIndex(lastIndex);
        }
        size++;
    }

    private Object removeElementAt(int index) {
        int realIndex = getRealIndex(index);
        Object temp = array[realIndex];
        if (index == 0) {
            array[startIndex] = null;
            startIndex = getNextIndex(startIndex);
        } else if (index == size - 1) {
            lastIndex = getPrevIndex(lastIndex);
            array[lastIndex] = null;
        } else {
            shiftLeft(realIndex + 1);
            lastIndex = getPrevIndex(lastIndex);
            array[lastIndex] = null;
        }
        size--;
        return temp;
    }

    private int getNextIndex(int index) {
        return (index + 1) % array.length;
    }

    private int getPrevIndex(int index) {
        int a = index;
        int b = array.length;
        return ((a - 1 % b) + b) % b;
    }

    private void shiftRight(int start) {
        for (int newIndex = lastIndex, oldIndex = getPrevIndex(lastIndex); newIndex != start; oldIndex = getPrevIndex(
                oldIndex)) {
            array[newIndex] = array[oldIndex];
            newIndex = oldIndex;
        }
    }

    private void shiftLeft(int start) {
        for (int newIndex = getPrevIndex(start), oldIndex = start; oldIndex != lastIndex; oldIndex = getNextIndex(
                oldIndex)) {
            array[newIndex] = array[oldIndex];
            newIndex = oldIndex;
        }
    }

    private int getRealIndex(int index) {
        return (startIndex + index) % array.length;
    }
}
