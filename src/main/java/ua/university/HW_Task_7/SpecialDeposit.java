package ua.university.HW_Task_7;


/* To create classes that are inheritors of the class Deposit, which determine different
options of deposit interest addition – class BaseDeposit, class SpecialDeposit and
class  LongDeposit.
To  implement  in  each  class  a  constructor  with  parameters
amount and period, which calls constructor of parent class.
For  each  inheritor  class  –  to  implement  own  interest  addition  scheme  and
accordingly profit margin definitions, overriding abstract method Income in each
class.
BaseDeposit implies each month 5% of interest  from current deposit sum. Each
following  month  of  income  is  calculated  from  the  sum,  which  was  received  by
adding to current income sum of the previous month and is rounded to hundredth.
Example: Base amount – 1000,00
In a month – 105,00; income amount – 50,00
In two months – 1102,50; income amount – 102,50
In three months – 1157,62; income amount – 157,62
 SpecialDeposit implies income addition each month, amount of which (in percent)
equals to deposit expiration period. If during the first month 1% is added, during the
second month – 2% from the sum obtained after first month and so on.
Example: Base amount – 1000,00
In a month – 1010,00; income amount – 10,00
In two months – 1030,20; income amount – 30,20
LongDeposit  implies  that  during  first  6  months,  no  percent  is  added  to  client’s
deposit,  but  starting  from  7th  month,  each  month  percent  addition  is  15%  from
current deposit sum, thus encouraging client to make long-term deposits.*/

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SpecialDeposit extends Deposit {

    public SpecialDeposit(BigDecimal depositAmount, int period) {
        super(depositAmount, period);
    }
    public SpecialDeposit(double depositAmount, int period) {
        super(depositAmount,period);
    }

    @Override
    public BigDecimal income() {
        BigDecimal currentDeposit ;
        BigDecimal sumDeposit = BigDecimal.valueOf(0);
        BigDecimal currentDepositAmount = this.getDepositAmount();

        for(int i=1;i<= this.getPeriod();i++) {

            currentDeposit = currentDepositAmount.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(i));
            currentDepositAmount = currentDepositAmount.add(currentDeposit);
            sumDeposit=sumDeposit.add(currentDeposit);
        }
        return sumDeposit.setScale(2, RoundingMode.DOWN);
    }

}

