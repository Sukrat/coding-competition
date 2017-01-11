/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures.test.trees;

import datastructures.trees.Heap;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sukra
 */
public class HeapTest {

    @Test
    public void testAddListRemove() {
        List<Integer> list = createList();
        Heap heap = new Heap(list);

        list.sort((x, y) -> {
            return Integer.compare(y, x);
        });

        for (Integer integer : list) {
            assertEquals(integer, heap.removeMax());
        }
    }

    @Test
    public void testAddRemove() {
        List<Integer> list = createList();
        Heap heap = new Heap();
        for (Integer integer : list) {
            heap.insert(integer);
        }

        list.sort((x, y) -> {
            return Integer.compare(y, x);
        });

        for (Integer integer : list) {
            assertEquals(integer, heap.removeMax());
        }
    }

    @Test
    public void testRemove() {
        List<Integer> list = createList();
        Heap<Integer> heap = new Heap<>();
        for (Integer integer : list) {
            heap.insert(integer);
        }

        list.sort((x, y) -> {
            return Integer.compare(y, x);
        });

        Integer remove = heap.remove(5);
        list.remove(remove);

        remove = heap.remove(2);
        list.remove(remove);

        for (Integer integer : list) {
            assertEquals(integer, heap.removeMax());
        }
    }

    private List<Integer> createList() {
        List<Integer> list = new ArrayList<>();
        list.add(37);
        list.add(24);
        list.add(42);
        list.add(35);
        list.add(32);
        list.add(7);
        list.add(2);
        list.add(42);
        list.add(120);
        list.add(40);
        return list;
    }
}
