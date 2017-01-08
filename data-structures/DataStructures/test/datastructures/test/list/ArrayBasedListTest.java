/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures.test.list;

import datastructures.lists.ArrayBasedList;
import datastructures.lists.IList;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author sukra
 */
public class ArrayBasedListTest {

    private ArrayBasedList list = new ArrayBasedList();
    private Random random = new Random();

    public ArrayBasedListTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }

    @Test
    public void testAdd() {
        ArrayBasedList arrayList = new ArrayBasedList();
        arrayList.add(1);
        assertEquals(arrayList.toString(), "[1]");
        assertEquals(arrayList.size(), 1);
    }

    @Test
    public void testAddAtPosition() {
        ArrayBasedList arrayList = new ArrayBasedList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(2, 4);
        assertEquals(arrayList.toString(), "[1, 2, 4, 3]");
        assertEquals(arrayList.size(), 4);
    }

    @Test
    public void testRemove() {
        for (int i = 0; i < 10; i++) {
            int pos = random.nextInt(list.size());
            list.remove(pos);
        }
        assertEquals(list.toString(), "[]");
        assertEquals(list.size(), 0);
    }

    @Test
    public void testSet() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            int no = random.nextInt();
            list.set(i, no);
            arrayList.add(no);
        }
        assertEqualList(list, arrayList);
        assertEquals(list.size(), 10);
    }

    @Test
    public void RandomOperationTest() {
        list = new ArrayBasedList();
        ArrayList arrayList = new ArrayList();

        for (int i = 0; i < 16; i++) {
            int rand = i;//random.nextInt();
            arrayList.add(rand);
            list.add(rand);
        }
        assertEqualList(list, arrayList);

        assertEquals(list.remove(5), arrayList.remove(5));
        assertEqualList(list, arrayList);

        assertEquals(list.remove(6), arrayList.remove(6));
        assertEqualList(list, arrayList);

        assertEquals(list.remove(0), arrayList.remove(0));
        assertEqualList(list, arrayList);

        assertEquals(list.remove(0), arrayList.remove(0));
        assertEqualList(list, arrayList);

        assertEquals(list.remove(list.size() - 1), arrayList.remove(arrayList.size() - 1));
        assertEqualList(list, arrayList);

        arrayList.add(0, 800);
        list.add(0, 800);
        assertEqualList(list, arrayList);

        arrayList.add(5, 500);
        list.add(5, 500);
        assertEqualList(list, arrayList);

        arrayList.set(6, 500);
        list.set(6, 500);
        assertEqualList(list, arrayList);

        for (int i = 0; i < arrayList.size(); i++) {
            int rand = random.nextInt(arrayList.size());
            arrayList.remove(rand);
            list.remove(rand);
        }
        assertEqualList(list, arrayList);

        for (int i = 0; i < 64; i++) {
            int rand = random.nextInt();
            arrayList.add(rand);
            list.add(rand);
        }
        assertEqualList(list, arrayList);

    }

    private void assertEqualList(IList list, ArrayList arrayList) {
        assertEquals(list.size(), arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            assertEquals(list.get(i), arrayList.get(i));
        }
        assertEquals(list.toString(), arrayList.toString());
    }

}
