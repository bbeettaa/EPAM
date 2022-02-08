package nau.university.Test_Task_6;

import nau.university.HW_Task_6.Manager;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class Test_Manager {

    @Test
    public void test_getSalary() {
        Manager manager1 = new Manager("Manager1", new BigDecimal(120), 99);
        assertEquals("1) error in Manager setBonus",
                new BigDecimal(120).setScale(2, RoundingMode.HALF_UP), manager1.getSalary());
    }

    @Test
    public void test_setBonus() {
        Manager manager1 = new Manager("Manager1", new BigDecimal(120), 99);
        manager1.setBonus(50);
        assertEquals("1) error in Manager setBonus",
                new BigDecimal(170).setScale(2, RoundingMode.HALF_UP), manager1.getSalary());

        manager1 = new Manager("Manager1", new BigDecimal(120), 100);
        manager1.setBonus(50);
        assertEquals("2) error in Manager setBonus",
                new BigDecimal(670).setScale(2, RoundingMode.HALF_UP), manager1.getSalary());

        manager1 = new Manager("Manager1", new BigDecimal(120), 150);
        manager1.setBonus(50);
        assertEquals("2) error in Manager setBonus",
                new BigDecimal(1170).setScale(2, RoundingMode.HALF_UP), manager1.getSalary());
    }

    @Test
    public void testGiveEverybodyBonus_OneManager_NoIncreaseForBonus1() {

    }
}

/*    public Manager(String lastName, BigDecimal salary, int clientAmount) {
        super(lastName, salary);
        this.clientAmount = clientAmount;
    }

    @Override
    public void setBonus(int bonus) {
        if (clientAmount >= 150)
            bonus += 1000;
        else if (clientAmount >= 100)
            bonus += 500;

        this.bonus = bonus;
    }*/