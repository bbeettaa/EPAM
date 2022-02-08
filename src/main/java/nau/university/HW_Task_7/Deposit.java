package nau.university.HW_Task_7;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
* To create abstract class Deposit and declare within it:

•  Public money property only for reading Amount (deposit amount)
•  Public integer property only for reading Period (time of deposit in months)
•  Constructor (for calling in class-inheritor) with parameters depositAmount
and depositPeriod, which creates object deposit with specified sum for
specified period.
•  Abstract method income, which returns money value – amount of income
from deposit. Income is the difference between sum, withdrawn from
deposit upon expiration date and deposited sum.  */
public abstract class Deposit {
    public BigDecimal depositAmount;
    public int period;


    public BigDecimal getDepositAmount() {
        return depositAmount.setScale(2, RoundingMode.HALF_UP);
    }
    public int getPeriod() {
        return period;
    }

    public Deposit(BigDecimal depositAmount, int period) {
        this.depositAmount = depositAmount;
        this.period = period;
    }

    public Deposit(double depositAmount, int period) {
        this(BigDecimal.valueOf(depositAmount),period);
    }

    abstract BigDecimal income();
}
