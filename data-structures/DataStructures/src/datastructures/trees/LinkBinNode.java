package datastructures.trees;

public class LinkBinNode<T> implements IBinNode<T> {

    private T element = null;
    private IBinNode<T> left = null;
    private IBinNode<T> right = null;

    public LinkBinNode(T e) {
        element = e;
    }

    public LinkBinNode(T e, IBinNode<T> l, IBinNode<T> r) {
        element = e;
        left = l;
        right = r;
    }

    @Override
    public T element() {
        return element;
    }

    @Override
    public void setElement(T e) {
        element = e;
    }

    @Override
    public IBinNode<T> left() {
        return left;
    }

    @Override
    public void setLeft(IBinNode<T> n) {
        left = n;
    }

    @Override
    public boolean hasLeft() {
        return left != null;
    }

    @Override
    public IBinNode<T> right() {
        return right;
    }

    @Override
    public void setRight(IBinNode<T> n) {
        right = n;
    }

    @Override
    public boolean hasRight() {
        return right != null;
    }

    @Override
    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public String toString() {
        return element.toString();
    }

}
