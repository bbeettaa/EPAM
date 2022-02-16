package ua.university.Test_Task_4;

import org.junit.Test;

import static ua.university.HW_Task_4.Task4.Task4.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class Test4 {
    @Test
    public void multiArithmeticElementsTestPass() {
        try {
            assertEquals("1) multiArithmeticElements", 175, multiArithmeticElements(100, 0.5, 20));
            assertEquals("2) multiArithmeticElements", 0, multiArithmeticElements(30, 0.2, 25));
            assertEquals("3) multiArithmeticElements", 316, multiArithmeticElements(169, 0.5, 20));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void transformTestThrow() {
        assertThrows("1) throw multiArithmeticElements",
                Exception.class, () ->multiArithmeticElements(169, 1, 20));
        assertThrows("2) throw multiArithmeticElements",
                Exception.class, () -> multiArithmeticElements(5, 0, 0));
    }
}
