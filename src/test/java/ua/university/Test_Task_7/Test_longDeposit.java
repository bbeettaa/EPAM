package ua.university.Test_Task_7;

import ua.university.HW_Task_7.LongDeposit;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class Test_longDeposit {

    @Test
    public void testClient_addDeposit() {
        LongDeposit longDeposit = new LongDeposit(1000, 6);
        LongDeposit longDeposit1 = new LongDeposit(1000, 7);
        LongDeposit longDeposit2 = new LongDeposit(1000, 8);

        assertEquals("1) error in LongDeposit addDeposit",
                BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP), longDeposit.income());

        assertEquals("2) error in LongDeposit addDeposit",
                BigDecimal.valueOf(150).setScale(2, RoundingMode.HALF_UP), longDeposit1.income());

        assertEquals("3) error in LongDeposit addDeposit",
                BigDecimal.valueOf(322.50).setScale(2, RoundingMode.HALF_UP), longDeposit2.income());
    }
}