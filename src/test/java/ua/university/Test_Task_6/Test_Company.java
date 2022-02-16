package ua.university.Test_Task_6;

import ua.university.HW_Task_6.Company;
import ua.university.HW_Task_6.Manager;
import org.junit.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static org.junit.Assert.*;

public class Test_Company {
    Company company;

    @Test
    public void testGiveEverybodyBonus_OneManager_NoIncreaseForBonus() {
        Manager manager1 = new Manager("First_Manager",new BigDecimal(99),99);
        company=new Company(manager1);

        company.giveEverybodyBonus(0);
        assertEquals("1) error in Company giveEverybodyBonus",
                new BigDecimal("99.00").setScale(2, RoundingMode.HALF_UP),company.totalToPay());

        company=new Company(manager1);
        company.giveEverybodyBonus(1);
        assertEquals("2) error in Company giveEverybodyBonus",
                new BigDecimal("100.00").setScale(2, RoundingMode.HALF_UP),company.totalToPay());
    }

    @Test
    public void testGiveEverybodyBonus_OneManager_IncreaseForBonus_One() {
        Manager manager1 = new Manager("First_Manager",new BigDecimal(100),100);
        company=new Company(manager1);

        company.giveEverybodyBonus(0);
        assertEquals("1) error in Company giveEverybodyBonus",
                new BigDecimal("600.00").setScale(2, RoundingMode.HALF_UP),company.totalToPay());

        company=new Company(manager1);
        company.giveEverybodyBonus(1);
        assertEquals("2) error in Company giveEverybodyBonus",
                new BigDecimal("601.00").setScale(2, RoundingMode.HALF_UP),company.totalToPay());
    }

    @Test
    public void testGiveEverybodyBonus2_TwoEmployee_NoIncreaseForBonus_Two() {
        Manager manager1 = new Manager("First_Manager",new BigDecimal(100),99);
        Manager manager2 = new Manager("Second_Manager",new BigDecimal(50),99);
        company=new Company(manager1,manager2);

        company.giveEverybodyBonus(0);
        assertEquals("1) error in Company giveEverybodyBonus",
                new BigDecimal("150.00").setScale(2, RoundingMode.HALF_UP),company.totalToPay());

        company=new Company(manager1,manager2);
        company.giveEverybodyBonus(1);
        assertEquals("2) error in Company giveEverybodyBonus",
                new BigDecimal("152.00").setScale(2, RoundingMode.HALF_UP),company.totalToPay());
    }

    @Test
    public void testGiveEverybodyBonus2_TwoEmployee_IncreaseForBonus_Two() {
        Manager manager1 = new Manager("First_Manager",new BigDecimal("100.55"),100);
        Manager manager2 = new Manager("Second_Manager",new BigDecimal("50.00"),100);

        company=new Company(manager1,manager2);
        company.giveEverybodyBonus(0);
        assertEquals("1) error in Company giveEverybodyBonus",
                new BigDecimal("1150.55").setScale(2, RoundingMode.HALF_UP),company.totalToPay());

        company=new Company(manager1,manager2);
        company.giveEverybodyBonus(1);
        assertEquals("2) error in Company giveEverybodyBonus",
                new BigDecimal("1152.55").setScale(2, RoundingMode.HALF_UP),company.totalToPay());
    }


    @Test
      public void testNameMaxSalary(){
        Manager manager1 = new Manager("First_Manager",new BigDecimal(100),100);
        Manager manager2 = new Manager("Second_Manager",new BigDecimal(150),100);

        company=new Company(manager1);
        company.giveEverybodyBonus(0);
        String str = company.nameMaxSalary();
        assertEquals("1) error in Company NameMaxSalary",
                "First_Manager",str);

        company=new Company(manager1,manager2);
        company.giveEverybodyBonus(50);
        str = company.nameMaxSalary();
        assertEquals("2) error in Company NameMaxSalary",
                "Second_Manager",str);
    }



    @Test
    public void testTotalToPay(){
        Manager manager1 = new Manager("First_Manager",new BigDecimal("100.55"),99);
        Manager manager2 = new Manager("Second_Manager",new BigDecimal(150),99);

        company=new Company(manager1,manager2);
        company.giveEverybodyBonus(0);
        BigDecimal bg = company.totalToPay();
        assertEquals("1) error in Company TotalToPay",
                new BigDecimal("250.55").setScale(2, RoundingMode.HALF_UP),bg);
    }




}
