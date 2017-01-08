package datastructures.trees;

public class Traversal {

    public static String preOrderTraversal(IBinNode root) {
        String toString = root.element().toString();
        if (root.hasLeft()) {
            toString += preOrderTraversal(root.left());
        }
        if (root.hasRight()) {
            toString += preOrderTraversal(root.right());
        }
        return toString;
    }

    public static String postOrderTraversal(IBinNode root) {
        String toString = "";
        if (root.hasLeft()) {
            toString += postOrderTraversal(root.left());
        }
        if (root.hasRight()) {
            toString += postOrderTraversal(root.right());
        }
        toString += root.element().toString();
        return toString;
    }

    public static String inOrderTraversal(IBinNode root) {
        String toString = "";
        if (root.hasLeft()) {
            toString += inOrderTraversal(root.left());
        }
        toString += root.element().toString();
        if (root.hasRight()) {
            toString += inOrderTraversal(root.right());
        }
        return toString;
    }
}
