package banck.acount.controller;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() {
        account = new Account(1000.0f, 0.05f);
    }

    @Test
    public void testDeposit() {
        account.deposit(500.0f);
        assertEquals(1500.0f, account.balance, 0.01);
        assertEquals(1, account.numberOfDeposits);
    }

    @Test
    public void testWithdrawBalance_SufficientFunds() {
        account.withdrawBalance(200.0f);
        assertEquals(800.0f, account.balance, 0.01);
        assertEquals(1, account.numberOfWithdrawals);
    }

    @Test
    public void testWithdrawBalance_InsufficientFunds() {
        account.withdrawBalance(1200.0f);
        assertEquals(1000.0f, account.balance, 0.01);
        assertEquals(0, account.numberOfWithdrawals);
    }

    @Test
    public void testMonthlyInterest() {
        account.monthlyInterest();
        assertEquals(1004.17f, account.balance, 0.01);
    }

    @Test
    public void testMonthlyProcess_NoServiceCharge() {
        account.monthlyProcess();
        assertEquals(1004.17f, account.balance, 0.01);
    }

    @Test
    public void testMonthlyProcess_WithServiceCharge() {
        account.monthlyServiceCharge = 10.0f;
        account.monthlyProcess();
        assertEquals(994.125, account.balance, 0.01);
    }

    @Test
    public void testPrint() {
        account.print();
    }
}
