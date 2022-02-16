package ua.university.HW_Task_7;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*To create class Client (bank client) and declare within it:
•  Private field deposits (client deposits) – objects array of type Deposit
•  Constructor without parameters, which creates empty array deposits
consisting of 10 elements
•  Method addDeposit with parameter deposit for adding regular, special or
long-term account into array on the first empty spot and returning true, or
returning false, if accounts number limit is depleted (no empty space in
array).
•  Method totalIncome, returning total income amount based on all client’s
deposits upon deposits expiration.
•  Method maxIncome, returning maximum deposit income of all client’s
deposits upon deposits expiration.
•  Method getIncomeByNumber with integer parameter number (deposit
number, which equals its index in array, increased by one), returning income
from deposit with such number. If deposit with such number does not exist,
method returns 0 value.*/
public class Client {
    Deposit[] deposits;

    public Client() {
        this.deposits = new Deposit[10];
    }

    public boolean addDeposit(Deposit deposit) {
        for (int i = 0; i < this.deposits.length; i++)
            if (this.deposits[i] == null) {
                this.deposits[i] = deposit;
                return true;
            }
        return false;
    }

    public BigDecimal totalIncome() {
        BigDecimal totalIncome = BigDecimal.valueOf(0);
        try {
            for (var obj : this.deposits) {
                totalIncome = totalIncome.add(obj.income());
            }

        } catch (Exception ex) {
        }
        return totalIncome.setScale(2, RoundingMode.DOWN);
    }

    public BigDecimal maxIncome() {
        BigDecimal maxIncome = BigDecimal.valueOf(0);
        try {
            for (var obj : this.deposits) {
                if (maxIncome.compareTo(obj.income()) < 0)
                    maxIncome = (obj.income());
            }
        } catch (Exception ex) {
        }
        return maxIncome.setScale(2, RoundingMode.DOWN);
    }

    public BigDecimal getIncomeByNumber(int num) {
        BigDecimal income = BigDecimal.valueOf(0);
        try {
            income = deposits[--num].income();
        } catch (Exception ex) {
        }
        return income.setScale(2, RoundingMode.DOWN);
    }

}
