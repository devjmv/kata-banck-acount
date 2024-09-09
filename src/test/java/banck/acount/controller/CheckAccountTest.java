package banck.acount.controller;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CheckAccountTest {

    private CheckAccount checkAccount;

    @Before
    public void setUp() {
        checkAccount = new CheckAccount(5000.0f, 0.03f);
    }

    @Test
    public void testGetOverdraft() {
        assertEquals(0.0f, checkAccount.getOverdraft(), 0.01);
    }

    @Test
    public void testWithdrawBalance_SufficientFunds() {
        checkAccount.withdrawBalance(3000.0f);
        assertEquals(2000.0f, checkAccount.balance, 0.01);
        assertEquals(1, checkAccount.numberOfWithdrawals);
        assertEquals(0.0f, checkAccount.getOverdraft(), 0.01);
    }

    @Test
    public void testWithdrawBalance_Overdraft() {
        checkAccount.withdrawBalance(6000.0f);
        assertEquals(0.0f, checkAccount.balance, 0.01);
        assertEquals(1, checkAccount.numberOfWithdrawals);
        assertEquals(1000.0f, checkAccount.getOverdraft(), 0.01);
    }

    @Test
    public void testDeposit_NoOverdraft() {
        checkAccount.deposit(2000.0f);
        assertEquals(7000.0f, checkAccount.balance, 0.01);
        assertEquals(0.0f, checkAccount.getOverdraft(), 0.01);
        assertEquals(1, checkAccount.numberOfDeposits);
    }

    @Test
    public void testDeposit_CoverOverdraftPartially() {
        checkAccount.withdrawBalance(6000.0f);
        checkAccount.deposit(500.0f);
        assertEquals(0.0f, checkAccount.balance, 0.01);
        assertEquals(500.0f, checkAccount.getOverdraft(), 0.01);
        assertEquals(1, checkAccount.numberOfDeposits);
    }

    @Test
    public void testDeposit_CoverOverdraftFully() {
        checkAccount.withdrawBalance(6000.0f);
        checkAccount.deposit(1500.0f);
        assertEquals(500.0f, checkAccount.balance, 0.01);
        assertEquals(0.0f, checkAccount.getOverdraft(), 0.01);
        assertEquals(1, checkAccount.numberOfDeposits);
    }

    @Test
    public void testMonthlyInterest_WithOverdraft() {
        checkAccount.withdrawBalance(6000.0f);
        checkAccount.monthlyInterest();
        assertEquals(0.0f, checkAccount.balance, 0.01);
        assertEquals(1000.0f, checkAccount.getOverdraft(), 0.01);
    }

    @Test
    public void testPrint() {
        checkAccount.print();
    }
}
