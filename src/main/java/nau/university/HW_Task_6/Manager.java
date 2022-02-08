package nau.university.HW_Task_6;

import java.math.BigDecimal;

public class Manager extends Employee {
    protected int clientAmount;

    public Manager(String lastName, BigDecimal salary, int clientAmount) {
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
    }
}
