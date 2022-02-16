package ua.advanced.Practical2.Task1;

import ua.advanced.Practical2.Task1.ListImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListImplTest {

    ListImpl list;

    @Before
    public void setUp() {
        list = new ListImpl();
    }

    @Test
    public void clear() {
        list.addFirst("1");
        list.addFirst("2");
        list.addFirst("3");
        list.addFirst("4");

        list.clear();

        assertEquals(0,list.size());
    }

    @Test
    public void size() {
        list.addFirst("1");
        list.addFirst("2");
        list.addFirst("3");
        list.addFirst("4");

        assertEquals(4,list.size());
    }

    @Test
    public void iterator() {
        assertNotNull(list.iterator());
    }

    @Test
    public void addFirst() {
        list.addFirst("1");
        assertEquals("1",list.getFirst());
        list.addFirst("2");
        assertEquals("2",list.getFirst());
    }

    @Test
    public void addLast() {
        list.addLast("1");
        list.addLast("2");
        assertEquals("2",list.getLast());
    }

    @Test
    public void removeFirst() {
        list.addFirst("1");
        list.addFirst("2");
        list.removeFirst();

        assertEquals("1",list.getFirst());
    }

    @Test
    public void removeLast() {
        list.addFirst("1");
        list.addFirst("2");
        list.addFirst("3");
        list.removeLast();
        list.removeLast();

        assertEquals("3",list.getLast());
    }

    @Test
    public void getFirst() {
        list.addFirst("1");
        assertEquals("1",list.getFirst());
        list.addFirst("2");
        assertEquals("2",list.getFirst());
    }

    @Test
    public void getLast() {
        list.addFirst("1");
        assertEquals("1",list.getLast());
        list.addFirst("2");
        assertEquals("1",list.getLast());
    }

    @Test
    public void search() {
        list.addFirst("1");
        list.addLast("2");
        list.addLast("3");
        assertEquals("3",list.search("3"));
    }

    @Test
    public void search_firstPos() {
        list.addFirst("1");
        list.addFirst("2");
        list.addFirst("3");
        assertEquals("3",list.search("3"));
    }

    @Test
    public void searchNull() {
        list.addFirst("1");
        list.addFirst("2");
        list.addFirst("3");
        assertNull(list.search("4"));
    }


    @Test
    public void remove_True() {
        list.addFirst("1");
        list.addLast("2");
        list.addLast("3");

        assertTrue(list.remove("3"));
        assertEquals("2",list.getLast());
    }

    @Test
    public void remove_False() {
        list.addFirst("1");
        list.addLast("2");
        list.addLast("3");

        assertFalse(list.remove("4"));
        assertEquals("3",list.getLast());
    }
    @Test
    public void toStringTest() {
        list.addFirst("1");
        list.addLast("2");
        list.addLast("3");

        assertEquals("[1, 2, 3]",list.toString());
    }
}