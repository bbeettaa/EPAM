package ua.university.Test_Task_7;

import ua.university.HW_Task_7.BaseDeposit;
import ua.university.HW_Task_7.Client;
import ua.university.HW_Task_7.Deposit;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestClient {


    @Test
    public void testClient_addDeposit() {
        Client client = new Client();
        Deposit baseDeposit = new BaseDeposit(1000,3);
        Deposit baseDeposit1 = new BaseDeposit(1000,2);
        client.addDeposit(baseDeposit);
        client.addDeposit(baseDeposit1);

        assertEquals("1) error in Client addDeposit",
                BigDecimal.valueOf(157.62).setScale(2,RoundingMode.HALF_UP), client.getIncomeByNumber(1));

        assertEquals("2) error in Client addDeposit",
                BigDecimal.valueOf(102.50).setScale(2,RoundingMode.HALF_UP), client.getIncomeByNumber(2));

        assertEquals("3) error in Client addDeposit",
                BigDecimal.valueOf(0).setScale(2,RoundingMode.HALF_UP), client.getIncomeByNumber(3));
    }

    @Test
    public void testClient_maxIncome() {
        Client client = new Client();
        Deposit baseDeposit = new BaseDeposit(1000,3);
        Deposit baseDeposit1 = new BaseDeposit(1000,2);

        client.addDeposit(baseDeposit);
        assertEquals("1) error in Client maxIncome",
                BigDecimal.valueOf(157.62).setScale(2,RoundingMode.HALF_UP), client.maxIncome());

        client.addDeposit(baseDeposit1);
        assertEquals("2) error in Client maxIncome",
                BigDecimal.valueOf(157.62).setScale(2,RoundingMode.HALF_UP), client.maxIncome());
    }

    @Test
    public void testClient_totalIncome() {
        Client client = new Client();
        Deposit baseDeposit = new BaseDeposit(1000,3);
        Deposit baseDeposit1 = new BaseDeposit(1000,2);

        client.addDeposit(baseDeposit);
        client.addDeposit(baseDeposit1);

        BigDecimal totalIncome=client.totalIncome();

        assertEquals("1) error in Client totalIncome()",
                BigDecimal.valueOf(260.12).setScale(2,RoundingMode.HALF_UP), totalIncome);

    }
}
