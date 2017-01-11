package datastructures.trees;

import java.util.ArrayList;
import java.util.List;

public class Heap<T extends Comparable> {

    private final ArrayList<T> array = new ArrayList<>();

    public Heap() {
    }

    public Heap(List<T> arr) {
        array.addAll(arr);
        buildHeap();
    }

    public void insert(T elem) {
        array.add(elem);
        int pos = array.size() - 1;
        if (pos > 0) {
            int parent = getParent(pos);
            while (array.get(pos).compareTo(array.get(parent)) >= 0) {
                swap(pos, parent);
                if (parent == 0) {
                    break;
                }
                pos = parent;
                parent = getParent(parent);
            }
        }
    }

    public T remove(int pos) {
        rangeCheck(pos);
        swap(pos, array.size() - 1);
        T remove = array.remove(array.size() - 1);
        if (array.size() > 0) {
            shiftDown(pos);
        }
        return remove;
    }

    public T removeMax() {
        swap(0, array.size() - 1);
        T remove = array.remove(array.size() - 1);
        if (array.size() > 0) {
            shiftDown(0);
        }
        return remove;
    }

    public int size() {
        return array.size();
    }

    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString() {
        return array.toString();
    }

    private int getParent(int pos) {
        if (pos == 0) {
            throw new RuntimeException("This is the root element");
        }
        return (pos - 1) / 2;
    }

    private boolean hasRightChild(int pos) {
        return 2 * pos + 2 < array.size();
    }

    private boolean hasLeftChild(int pos) {
        return 2 * pos + 1 < array.size();
    }

    private int rightChild(int pos) {
        rangeCheck(pos);
        int child = 2 * pos + 2;
        rangeCheck(child);
        return child;
    }

    private int leftChild(int pos) {
        rangeCheck(pos);
        int child = 2 * pos + 1;
        rangeCheck(child);
        return child;
    }

    private boolean isLeaf(int pos) {
        rangeCheck(pos);
        return pos >= array.size() / 2 && pos < array.size();
    }

    private void rangeCheck(int pos) {
        if (pos < 0 && pos >= array.size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void shiftDown(int pos) {
        while (!isLeaf(pos)) {
            T curr = array.get(pos);
            int maxI = -1, minI = -1;

            maxI = leftChild(pos);
            if (hasRightChild(pos)) {
                int rightPos = rightChild(pos);
                int leftPos = maxI;
                if (array.get(leftPos).compareTo(array.get(rightPos)) > 0) {
                    maxI = leftPos;
                    minI = rightPos;
                } else {
                    minI = leftPos;
                    maxI = rightPos;
                }
            }

            if (curr.compareTo(array.get(maxI)) <= 0) {
                swap(pos, maxI);
                pos = maxI;
            } else if (minI != -1 && curr.compareTo(array.get(minI)) <= 0) {
                swap(pos, minI);
                pos = minI;
            } else {
                break;
            }
        }
    }

    private void buildHeap() {
        for (int i = array.size() / 2; i >= 0; i--) {
            shiftDown(i);
        }
    }

    private void swap(int i, int j) {
        T jVal = array.set(j, array.get(i));
        array.set(i, jVal);
    }
}
