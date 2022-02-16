package ua.advanced.Practical1.ArrayTest;

import ua.advanced.Practical1.Task2.ArrayImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ArrayTest {
    ArrayImpl array;

    @Before
    public void initArr() {
        array = new ArrayImpl();
    }

    @Test
    public void add_withResize() {
        array = new ArrayImpl(0);
        String str;

        array.add("10");
        str = array.toString();
        assertEquals("[10]", str);

        array.add("20");
        str = array.toString();
        assertEquals("[10, 20]", str);
    }

    @Test
    public void set() {
        String str;
        array.add("10");
        array.add("20");

        array.set(0, "30");
        str = array.toString();
        assertEquals("[30, 10, 20]", str);

        array.set(1, "40");
        str = array.toString();
        assertEquals("[30, 40, 10, 20]", str);
    }

    @Test
    public void set_NoSuchElementException(){
        array = new ArrayImpl();
        assertThrows(NoSuchElementException.class,()-> array.set(2,"20"));
        assertThrows(NoSuchElementException.class,()-> array.set(-1,"20"));
    }

    @Test
    public void set_withResize() {
        String str;
        array = new ArrayImpl(5);

        assertEquals(0,array.size());

        array = new ArrayImpl(0);
        array.set(0,"10");
        array.set(0,"20");
        array.set(0,"30");
        assertEquals(3,array.size());
    }

    @Test
    public void get() {
        Object obj;
        array.add("30");
        array.add("40");
        array.add("10");
        array.add("20");

        obj = array.get(0);
        assertEquals("30", obj);

        obj = array.get(3);
        assertEquals("20", obj);

        obj = array.get(2);
        assertEquals("10", obj);
    }

    @Test
    public void get_NoSuchElementException() {
        assertThrows(NoSuchElementException.class,()->array.get(2));
    }

    @Test
    public void indexOf() {
        int index;

        array.add("30");
        array.add("40");
        array.add("10");
        array.add("20");

        index = array.indexOf("10");
        assertEquals(2, index);

        index = array.indexOf("30");
        assertEquals(0, index);

        index = array.indexOf("20");
        assertEquals(3, index);
    }

    @Test
    public void indexOf_wrongValue(){
        int index;
        index = array.indexOf("666");

        assertEquals(-1, index);
    }

    @Test
    public void remove() {
        String str;
        array.add("30");
        array.add("40");
        array.add("10");
        array.add("20");

        array.remove(0);
        str = array.toString();
        assertEquals("[40, 10, 20]", str);

        array.remove(2);
        str = array.toString();
        assertEquals("[40, 10]", str);
    }

    @Test
    public void remove_NoSuchElementException() {
        assertThrows(NoSuchElementException.class,()->array.remove(0));
        array.add(1);
        assertThrows(NoSuchElementException.class,()->array.remove(1));
    }

    @Test
    public void clear(){
        array.add("30");
        array.add("40");
        array.add("10");
        array.add("20");

        array.clear();
        String str = array.toString();
        assertEquals("[]", str);
    }


    @Test
    public void test_toString() {
        String str = array.toString();
        assertEquals("[]", str);

        array.add("30");
        array.add("40");
        array.add("10");
        array.add("20");
        str = array.toString();
        assertEquals("[30, 40, 10, 20]", str);
    }

}
