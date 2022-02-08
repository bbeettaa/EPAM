package nau.university.Test_Task_5;

import nau.university.HW_Task_5.ArrayRectangles;
import nau.university.HW_Task_5.Rectangle;
import org.junit.Before;
import org.junit.Test;
import java.util.AbstractMap;
import static org.junit.Assert.*;

public class Test_Array_Rectangles {
    ArrayRectangles arrRect;

    @Before
    public void createRectangleArray() {
        Rectangle rect1 = new Rectangle();
        Rectangle rect2 = new Rectangle(1,1);
        Rectangle rect3 = new Rectangle(2,2);
        Rectangle rect4 = new Rectangle(3,3);
        Rectangle rect5 = new Rectangle(10,19);

        arrRect = new ArrayRectangles(rect1,rect2,rect3,rect4,rect5);
    }

    @Test
    public void testArrayRectanglesNumberSquare() {
        assertEquals("1) error in ArrayRectangle numberSquare",
                3,arrRect.numberSquare());
        assertNotEquals("1) error in ArrayRectangle numberSquare",
                4,arrRect.numberSquare());
    }

    @Test
    public void testNumberMaxArea() {
        AbstractMap.Entry<Integer,Double> my =
                new AbstractMap.SimpleEntry<>(4, 190.0);
        assertEquals("1) error in ArrayRectangle numberMaxArea",
                my, arrRect.numberMaxArea());
    }

    @Test
    public void testNumberMinPerimeter() {
        AbstractMap.Entry<Integer,Double> my =
                new AbstractMap.SimpleEntry<>(1, 4.0);
        assertEquals("1) error in ArrayRectangle numberMinPerimeter",
                my, arrRect.numberMinPerimeter());
    }

    @Test
    public void testAddRectangle() {
        Rectangle rect = new Rectangle(10,20);
        assertFalse("1) error in ArrayRectangle addRectangle", arrRect.addRectangle(rect));

        arrRect = new ArrayRectangles(5);
        assertTrue("2) error in ArrayRectangle addRectangle", arrRect.addRectangle(rect));
    }
}