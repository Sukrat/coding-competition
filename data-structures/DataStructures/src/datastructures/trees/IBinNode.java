package datastructures.trees;

public interface IBinNode<T> {

    T element();

    void setElement(T e);

    IBinNode<T> left();

    void setLeft(IBinNode<T> n);

    boolean hasLeft();

    IBinNode<T> right();

    void setRight(IBinNode<T> n);

    boolean hasRight();

    boolean isLeaf();
}
