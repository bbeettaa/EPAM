package ua.university.Test_Task_7;

import ua.university.HW_Task_7.SpecialDeposit;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class Test_specialDeposit {

    @Test
    public void testClient_addDeposit() {
        SpecialDeposit specialDeposit = new SpecialDeposit(1000, 1);
        SpecialDeposit specialDeposit1 = new SpecialDeposit(1000, 2);

        assertEquals("1) error in LongDeposit addDeposit",
                BigDecimal.valueOf(10).setScale(2, RoundingMode.HALF_UP), specialDeposit.income());

        assertEquals("2) error in LongDeposit addDeposit",
                BigDecimal.valueOf(30.20).setScale(2, RoundingMode.HALF_UP), specialDeposit1.income());

    }
    @Test
    public void testClient_addDeposit1() {
        SpecialDeposit specialDeposit = new SpecialDeposit(1000, 1);
        SpecialDeposit specialDeposit1 = new SpecialDeposit(1000, 2);

        assertEquals("1) error in LongDeposit addDeposit",
                BigDecimal.valueOf(10).setScale(2, RoundingMode.HALF_UP), BigDecimal.valueOf(10).setScale(2, RoundingMode.HALF_UP));

        assertEquals("2) error in LongDeposit addDeposit",
                BigDecimal.valueOf(30.20).setScale(2, RoundingMode.HALF_UP), specialDeposit1.income());

    }
}
