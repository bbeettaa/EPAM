package nau.university.HW_Task_6;

import java.math.BigDecimal;

public class SalesPerson extends Employee {
    private int percentOfPlanExecution;

    public SalesPerson(String lastName, BigDecimal salary, int percentOfPlanExecution) {
        super(lastName, salary);
        this.percentOfPlanExecution = percentOfPlanExecution;
    }

    @Override
    public void setBonus(int bonus) {
        if (percentOfPlanExecution >= 200)
            bonus *= 3;
        else if (percentOfPlanExecution >= 100)
            bonus *= 2;

        this.bonus = bonus;
    }
}
