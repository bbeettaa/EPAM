package nau.university.HW_Task_6;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Employee {
    protected String lastName;
    protected BigDecimal salary;
    protected int bonus;

    public Employee(String lastName, BigDecimal salary) {
        this.lastName = lastName;
        setSalary(salary);
    }

    public BigDecimal toPay() {
        return salary.add(BigDecimal.valueOf(bonus));
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary.add(BigDecimal.valueOf(bonus));
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary.setScale(2, RoundingMode.HALF_UP);
    }

    public abstract void setBonus(int bonus);
}
