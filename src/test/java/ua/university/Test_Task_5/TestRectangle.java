package ua.university.Test_Task_5;

import ua.university.HW_Task_5.Rectangle;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestRectangle {

    @Test
    public void testRectangleSettersAndGetters() {
        Rectangle rect = new Rectangle();
        rect.setSideA(1.1);
        rect.setSideB(1.2);
        assertEquals("1) error in setSideA", 1.1,rect.getSideA(),1);
        assertEquals("2) error in setSideB", 1.2,rect.getSideB(),1);
    }
    @Test
    public void test_rectangle_constructors() {
        Rectangle rect = new Rectangle(0.5,25.2);
        assertEquals("1) error in Rectangle Constructors", 0.5,rect.getSideA(),1);
        assertEquals("2) error in Rectangle Constructors", 25.2,rect.getSideB(),1);

        rect = new Rectangle(1);
        assertEquals("3) error in Rectangle Constructors", 1,rect.getSideA(),1);

        rect = new Rectangle();
        assertEquals("4) error in Rectangle Constructors", 4,rect.getSideA(),1);
        assertEquals("5) error in Rectangle Constructors", 3,rect.getSideB(),1);
    }
    @Test
    public void test_Rectangle_area() {
        Rectangle rect = new Rectangle();
        assertEquals("1) error in Rectangle area",12.0,rect.area(),1);
    }
    @Test
    public void test_Rectangle_perimeter() {
        Rectangle rect = new Rectangle();
        assertEquals("1) error in Rectangle perimeter",14,rect.perimeter(),1);
    }
    @Test
    public void test_Rectangle_isSquare() {
        Rectangle rect = new Rectangle();
        assertFalse("1) error in Rectangle isSquare",rect.isSquare());
        rect = new Rectangle(2,2);
        assertTrue("2) error in Rectangle isSquare",rect.isSquare());
    }

    @Test
    public void test_Rectangle_replaceSides() {
        Rectangle rect = new Rectangle(10,1);
        rect.replaceSides();
        assertEquals("1) error in Rectangle replaceSides",1,rect.getSideA(),1);
        assertEquals("2) error in Rectangle replaceSides",10,rect.getSideB(),1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentException() {
        Rectangle rect = new Rectangle(-1, 0);
    }
}
