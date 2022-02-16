package ua.university.HW_Task_6;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Company {
    protected Employee[] employees;

    public Company(Employee... employees) {
        this.employees = employees;
    }

    public void giveEverybodyBonus(int bonus) {
        for (var obj : employees) {
            obj.setBonus(bonus);
        }
    }

    public BigDecimal totalToPay() {
        BigDecimal salaries = BigDecimal.valueOf(0.00);

        for (var obj : employees)
            salaries = salaries.add(obj.getSalary());

        return salaries.setScale(2, RoundingMode.HALF_UP);
    }

    public String nameMaxSalary() {
        Employee currentEmployee = employees[0];

        for (var obj : employees)
            if (currentEmployee.getSalary().compareTo(obj.getSalary()) < 0)
                currentEmployee = obj;

        return currentEmployee.getLastName();
    }
}
