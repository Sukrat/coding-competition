package datastructures.test.trees;

import datastructures.trees.BinarySearchTree;
import datastructures.trees.BinarySearchTree.BSTElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

public class BinarySearchTreeTest {

    private static class BSTValue implements BSTElement<Integer> {

        private int value;

        public BSTValue(int value) {
            this.value = value;
        }

        @Override
        public Integer key() {
            return value;
        }

        @Override
        public String toString() {
            return Integer.toString(value);
        }
    }

    @Test
    public void testInsert() {
        BinarySearchTree bstTree = new BinarySearchTree();
        List<BSTValue> list = createList();
        for (BSTValue bSTValue : list) {
            bstTree.insert(bSTValue);
        }
        assertEquals(Arrays.toString(new int[]{2, 7, 24, 32, 35, 37, 40, 42, 42, 120}), bstTree.toString());
    }

    @Test
    public void testRemove() {
        BinarySearchTree bstTree = new BinarySearchTree();
        List<BSTValue> list = createList();
        for (BSTValue bSTValue : list) {
            bstTree.insert(bSTValue);
        }
        assertEquals(Arrays.toString(new int[]{2, 7, 24, 32, 35, 37, 40, 42, 42, 120}), bstTree.toString());

        bstTree.remove(new BSTValue(24));
        assertEquals(Arrays.toString(new int[]{2, 7, 32, 35, 37, 40, 42, 42, 120}), bstTree.toString());

        bstTree.remove(new BSTValue(42));
        assertEquals(Arrays.toString(new int[]{2, 7, 32, 35, 37, 40, 42, 120}), bstTree.toString());
    }

    private List<BSTValue> createList() {
        List<BSTValue> bstList = new ArrayList<>();
        bstList.add(new BSTValue(37));
        bstList.add(new BSTValue(24));
        bstList.add(new BSTValue(42));
        bstList.add(new BSTValue(35));
        bstList.add(new BSTValue(32));
        bstList.add(new BSTValue(7));
        bstList.add(new BSTValue(2));
        bstList.add(new BSTValue(42));
        bstList.add(new BSTValue(120));
        bstList.add(new BSTValue(40));
        return bstList;
    }

}
