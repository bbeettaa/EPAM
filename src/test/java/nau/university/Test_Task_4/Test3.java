package nau.university.Test_Task_4;

import org.junit.Test;
import static nau.university.HW_Task_4.Task3.Task3.*;
import static org.junit.Assert.*;

public class Test3 {
    @Test
    public void multiArithmeticElementsTestPass() {
        assertEquals("1) multiArithmeticElements",6160,multiArithmeticElements(5,3,4));
        assertEquals("2) multiArithmeticElements",0,multiArithmeticElements(0,3,4));
        assertEquals("3) multiArithmeticElements",125,multiArithmeticElements(5,0,3));
        assertEquals("4) multiArithmeticElements",0,multiArithmeticElements('5',3,0));
        assertEquals("5) multiArithmeticElements",0,multiArithmeticElements(5,0,0));
        assertEquals("6) multiArithmeticElements",5,multiArithmeticElements(5,0,1));
        assertEquals("7) multiArithmeticElements",40,multiArithmeticElements(-5,3,4));
        assertEquals("8) multiArithmeticElements",0,multiArithmeticElements(5,3,-4));
    }
    @Test
    public void transformTestThrow() {
        assertThrows("1) throw multiArithmeticElements",
                Exception.class, () ->multiArithmeticElements(Integer.parseInt("s"), 1, 20));

    }
}
