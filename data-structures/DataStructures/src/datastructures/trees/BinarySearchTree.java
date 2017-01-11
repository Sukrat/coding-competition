package datastructures.trees;

import datastructures.trees.BinarySearchTree.BSTElement;
import java.util.NoSuchElementException;

public class BinarySearchTree<E extends BSTElement> {

    public interface BSTElement<T extends Comparable> {

        T key();
    }

    private IBinNode<BSTElement> root;

    public BinarySearchTree() {
        root = null;
    }

    public void clear() {
        root = null;
    }

    public void insert(BSTElement elem) {
        root = insertHelp(root, elem);
    }

    public void remove(BSTElement elem) {
        root = removeHelp(root, elem);
    }

    public E find(BSTElement elem) {
        IBinNode<BSTElement> findHelp = findHelp(root, elem);
        if (findHelp.element() == null) {
            throw new NoSuchElementException();
        }
        return (E) findHelp.element();
    }

    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public String toString() {
        return Traversal.inOrderTraversal(root).toString();
    }

    private IBinNode<BSTElement> findHelp(IBinNode<BSTElement> node, BSTElement key) {
        if (node == null) {
            return null;
        }
        int compareTo = key.key().compareTo(node.element().key());
        if (compareTo > 0) {
            return findHelp(node.right(), key);
        } else if (compareTo < 0) {
            return findHelp(node.left(), key);
        } else {
            return node;
        }
    }

    private IBinNode<BSTElement> insertHelp(IBinNode<BSTElement> node, BSTElement key) {
        if (node == null) {
            return new LinkBinNode<>(key, null, null);
        }
        int compareTo = key.key().compareTo(node.element().key());
        if (compareTo < 0) {
            node.setLeft(insertHelp(node.left(), key));
            return node;
        } else {
            node.setRight(insertHelp(node.right(), key));
            return node;
        }
    }

    private BSTElement getMinValue(IBinNode<BSTElement> node) {
        if (node.hasLeft()) {
            return getMinValue(node.left());
        } else {
            return node.element();
        }
    }

    private IBinNode<BSTElement> deleteMin(IBinNode<BSTElement> node) {
        if (!node.hasLeft()) {
            return node.right();
        } else {
            node.setLeft(deleteMin(node.left()));
        }
        return node;
    }

    private IBinNode<BSTElement> removeHelp(IBinNode<BSTElement> node, BSTElement elem) {
        if (node == null) {
            return null;
        }
        int compareTo = elem.key().compareTo(node.element().key());
        if (compareTo < 0) {
            node.setLeft(removeHelp(node.left(), elem));
        } else if (compareTo > 0) {
            node.setRight(removeHelp(node.right(), elem));
        } else {
            //found the element
            if (node.isLeaf()) {
                return null;
            } else if (!node.hasRight()) {
                return node.left();
            } else if (!node.hasLeft()) {
                return node.right();
            } else {
                BSTElement minValue = getMinValue(node.right());
                node.setRight(deleteMin(node.right()));
                node.setElement(minValue);
            }
        }
        return node;
    }
}
