package datastructures.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkBasedList<T> implements IList<T> {

    private static class Node<T> {

        private T elem;
        private Node<T> next;

        public Node(T elem, Node<T> next) {
            this.elem = elem;
            this.next = next;
        }

        public T getElem() {
            return elem;
        }

        public void setElem(T elem) {
            this.elem = elem;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    @Override
    public void add(T elem) {
        addElement(-1, elem);
    }

    @Override
    public void add(int index, T elem) {
        rangeCheck(index);
        addElement(index, elem);
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        return removeAt(index);
    }

    @Override
    public T set(int index, T elem) {
        rangeCheck(index);
        Node<T> curr = getNthNode(index);
        T temp = curr.getElem();
        curr.setElem(elem);
        return temp;
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        if (index == 0) {
            return head.getElem();
        } else if (index == size - 1) {
            return tail.getElem();
        } else {
            return getNthNode(index).getElem();
        }
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

        Node<T> cursor = head;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public T next() {
            T elem;
            if (hasNext()) {
                elem = cursor.getElem();
            } else {
                throw new NoSuchElementException();
            }
            cursor = cursor.getNext();
            return elem;
        }
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    }

    private void addElement(int index, T elem) {
        Node<T> node = new Node<T>(elem, null);
        if (index == 0) {
            node.setNext(head);
            head = node;
        } else if (index == size) {
            tail.setNext(node);
            tail = node;
        } else {
            Node<T> curr = getNthNode(index - 1);
            node.setNext(curr.getNext());
            curr.setNext(node);
        }
        if (size == 0) {
            tail = head;
        }
        size++;
    }

    private T removeAt(int index) {
        Node<T> temp = null;
        if (index == 0) {
            temp = head;
            head = head.getNext();
        } else if (index == size - 1) {
            temp = tail;
            tail = getNthNode(index - 1);
            tail.setNext(null);
        } else {
            Node<T> curr = getNthNode(index - 1);
            temp = curr.getNext();
            curr.setNext(temp.getNext());
        }
        temp.setNext(null);
        size--;
        return temp.getElem();
    }

    private Node<T> getNthNode(int index) {
        int i = 0;
        Node<T> curr = head;
        while (curr != null) {
            if (i == index) {
                return curr;
            }
            curr = curr.getNext();
            i++;
        }
        return null;
    }
}
