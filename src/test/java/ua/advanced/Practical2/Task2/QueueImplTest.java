package ua.advanced.Practical2.Task2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueueImplTest {
    QueueImpl queue;

    @Before
    public void setUp(){
        queue = new QueueImpl();
    }

    @Test
    public void clear() {
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");

        queue.clear();

        assertEquals(0,queue.size());
    }

    @Test
    public void size() {
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");

        assertEquals(4,queue.size());
    }

    @Test
    public void iterator()  {
        assertNotNull(queue.iterator());
    }

    @Test
    public void enqueue() {
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");

        assertEquals(4,queue.size());
    }

    @Test
    public void dequeue() {
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");

        assertEquals("1", queue.dequeue());
        assertEquals("2", queue.dequeue());
        assertEquals("3", queue.dequeue());
        assertEquals("4", queue.dequeue());
        assertNull( queue.dequeue());
    }

    @Test
    public void top() {
        queue.enqueue("1");
        queue.enqueue("2");

        assertEquals("1",queue.top());
    }

    @Test
    public void toStringTest() {
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");

        assertEquals("[1, 2, 3]",queue.toString());
    }
}