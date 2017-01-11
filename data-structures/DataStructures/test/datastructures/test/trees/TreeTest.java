package datastructures.test.trees;

import datastructures.trees.IBinNode;
import datastructures.trees.LinkBinNode;
import datastructures.trees.Traversal;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TreeTest {

    public TreeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Test
    public void testTraversal() {
        String[] values = new String[]{"a", "b", "c", "d", null, "e", "f", null, null, null, null, "g", null, "h",
            "i"};
        IBinNode root = createTree(values);
        List<String> preOrderTraversal = Traversal.preOrderTraversal(root);
        List<String> postOrderTraversal = Traversal.postOrderTraversal(root);
        List<String> inOrderTraversal = Traversal.inOrderTraversal(root);
        assertEquals("Pre order failed", "[a, b, d, c, e, g, f, h, i]", preOrderTraversal.toString());
        assertEquals("Post order failed", "[d, b, g, e, h, i, f, c, a]", postOrderTraversal.toString());
        assertEquals("In order failed", "[d, b, a, g, e, c, h, f, i]", inOrderTraversal.toString());
    }

    private IBinNode createTree(Object[] values) {
        IBinNode root = new LinkBinNode(values[0]);
        Queue<IBinNode> nodes = new LinkedBlockingQueue<>();
        nodes.add(root);
        for (int i = 1; i < values.length; i += 2) {
            IBinNode parent = nodes.remove();
            if (parent.element() == null) {
                continue;
            }
            if (values[i] != null) {
                IBinNode node = new LinkBinNode(values[i]);
                parent.setLeft(node);
                nodes.add(node);
            } else {
                nodes.add(new LinkBinNode(null));
            }
            if (values[i + 1] != null) {
                IBinNode node = new LinkBinNode(values[i + 1]);
                parent.setRight(node);
                nodes.add(node);
            } else {
                nodes.add(new LinkBinNode(null));
            }
        }
        return root;
    }

}