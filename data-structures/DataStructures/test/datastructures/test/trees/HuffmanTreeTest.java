/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures.test.trees;

import datastructures.trees.HuffmanTree;

import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import datastructures.trees.HuffmanTree.HuffmanNode;
import datastructures.trees.IBinNode;
import datastructures.trees.Traversal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sukra
 */
public class HuffmanTreeTest {

    public HuffmanTreeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testHuffmanTree() throws Exception {
        List<HuffmanNode> list = createList();
        IBinNode<HuffmanNode> root = HuffmanTree.createHuffmanTree(list);
        String toString = Arrays
                .toString(Traversal.preOrderTraversal(root).stream()
                        .filter((x) -> x != null)
                        .toArray());
        assertEquals("[E, U, D, L, C, Z, K, F]", toString);
    }

    @Test
    public void testHuffmanTreeCodeMap() throws Exception {
        List<HuffmanNode> list = createList();
        IBinNode<HuffmanNode> root = HuffmanTree.createHuffmanTree(list);
        Map<String, String> codeMap = HuffmanTree.getCodeMap(root);
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("C", "1110");
        expectedMap.put("D", "101");
        expectedMap.put("E", "0");
        expectedMap.put("F", "11111");
        expectedMap.put("K", "111101");
        expectedMap.put("L", "110");
        expectedMap.put("U", "100");
        expectedMap.put("Z", "111100");
        assertEquals(expectedMap, codeMap);
    }

    private List<HuffmanNode> createList() {
        List<HuffmanNode> list = new ArrayList<>();
        list.add(new HuffmanNode(32, "C"));
        list.add(new HuffmanNode(42, "D"));
        list.add(new HuffmanNode(120, "E"));
        list.add(new HuffmanNode(24, "F"));
        list.add(new HuffmanNode(7, "K"));
        list.add(new HuffmanNode(42, "L"));
        list.add(new HuffmanNode(37, "U"));
        list.add(new HuffmanNode(2, "Z"));
        return list;
    }

}
