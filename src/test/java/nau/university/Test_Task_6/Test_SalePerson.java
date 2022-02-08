package nau.university.Test_Task_6;

import nau.university.HW_Task_6.SalesPerson;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class Test_SalePerson {
    @Test
    public void test_getLastName() {
        SalesPerson salesPerson1 = new SalesPerson("SalesPerson1", new BigDecimal(120), 99);
        salesPerson1.setLastName("NewName");
        assertEquals("1) error in Manager setBonus",
                "NewName", salesPerson1.getLastName());
    }

    @Test
    public void test_toPay() {
        SalesPerson salesPerson1 = new SalesPerson("SalesPerson1", new BigDecimal(120), 99);
        salesPerson1.setLastName("NewName");
        salesPerson1.setBonus(50);
        assertEquals("1) error in Manager setBonus",
                new BigDecimal(170).setScale(2, RoundingMode.HALF_UP), salesPerson1.toPay());

        salesPerson1 = new SalesPerson("SalesPerson1", new BigDecimal(120), 100);
        salesPerson1.setBonus(50);
        assertEquals("2) error in Manager setBonus",
                new BigDecimal(220).setScale(2, RoundingMode.HALF_UP), salesPerson1.toPay());

        salesPerson1 = new SalesPerson("SalesPerson1", new BigDecimal(120), 200);
        salesPerson1.setBonus(50);
        assertEquals("3) error in Manager setBonus",
                new BigDecimal(270).setScale(2, RoundingMode.HALF_UP), salesPerson1.toPay());
    }
}
