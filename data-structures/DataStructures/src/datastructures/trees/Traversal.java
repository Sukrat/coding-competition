package datastructures.trees;

import java.util.ArrayList;
import java.util.List;

public class Traversal {

    public static List<String> preOrderTraversal(IBinNode root) {
        List<String> list = new ArrayList<>();
        list.add(root.element().toString());
        if (root.hasLeft()) {
            list.addAll(preOrderTraversal(root.left()));
        }
        if (root.hasRight()) {
            list.addAll(preOrderTraversal(root.right()));
        }

        return list;
    }

    public static List<String> postOrderTraversal(IBinNode root) {
        List<String> list = new ArrayList<>();
        if (root.hasLeft()) {
            list.addAll(postOrderTraversal(root.left()));
        }
        if (root.hasRight()) {
            list.addAll(postOrderTraversal(root.right()));
        }
        list.add(root.element().toString());
        return list;
    }

    public static List<String> inOrderTraversal(IBinNode root) {
        List<String> list = new ArrayList<>();
        if (root.hasLeft()) {
            list.addAll(inOrderTraversal(root.left()));
        }
        list.add(root.element().toString());
        if (root.hasRight()) {
            list.addAll(inOrderTraversal(root.right()));
        }
        return list;
    }
}
