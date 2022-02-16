package ua.advanced.Practical2.Task3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackImplTest {
StackImpl stack;
    @Before
    public void setUp()  {
        stack= new StackImpl();
    }

    @Test
    public void clear() {
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");

        stack.clear();

        assertEquals(0,stack.size());
    }

    @Test
    public void size() {
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");

        assertEquals(4,stack.size());
    }

    @Test
    public void iterator() {
    }

    @Test
    public void push() {
        stack.push("1");
        stack.push("2");
        stack.push("3");

        assertEquals(3,stack.size());
        assertEquals("[3, 2, 1]",stack.toString());
    }

    @Test
    public void pop() {
        stack.push("1");
        stack.push("2");
        stack.push("3");

        assertEquals("3",stack.pop());
        assertEquals("2",stack.pop());
        assertEquals("1",stack.pop());

        assertEquals(0,stack.size());
    }

    @Test
    public void iteratorTest() {
        assertNotNull(stack.iterator());
    }

    @Test
    public void top() {
        stack.push("1");
        stack.push("2");
        stack.push("3");
        assertEquals("3",stack.top());
    }

    @Test
    public void toStringTest() {
        stack.push("1");
        stack.push("2");
        stack.push("3");

        assertEquals("[3, 2, 1]",stack.toString());
    }
}