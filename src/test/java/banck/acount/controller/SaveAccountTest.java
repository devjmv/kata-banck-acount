package banck.acount.controller;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SaveAccountTest {

    private SaveAccount activeAccount;
    private SaveAccount inactiveAccount;

    @Before
    public void setUp() {
        activeAccount = new SaveAccount(15000.0f, 0.05f);
        inactiveAccount = new SaveAccount(5000.0f, 0.05f);
    }
    @Test
    public void testDeposit_ActiveAccount() {
        activeAccount.deposit(500.0f);
        assertEquals(15500.0f, activeAccount.balance, 0.01);
        assertEquals(1, activeAccount.numberOfDeposits);
    }

    @Test
    public void testDeposit_InactiveAccount() {
        inactiveAccount.deposit(500.0f);
        assertEquals(5000.0f, inactiveAccount.balance, 0.01);
        assertEquals(0, inactiveAccount.numberOfDeposits);
    }

    @Test
    public void testWithdrawBalance_ActiveAccount() {
        activeAccount.withdrawBalance(2000.0f);
        assertEquals(13000.0f, activeAccount.balance, 0.01);
        assertEquals(1, activeAccount.numberOfWithdrawals);
    }

    @Test
    public void testWithdrawBalance_InactiveAccount() {
        inactiveAccount.withdrawBalance(500.0f);
        assertEquals(5000.0f, inactiveAccount.balance, 0.01);
        assertEquals(0, inactiveAccount.numberOfWithdrawals);
    }

    @Test
    public void testMonthlyProcess_NoExcessWithdrawals() {
        activeAccount.monthlyProcess();
        assertEquals(15062.5f, activeAccount.balance, 0.01);
        assertEquals(0.0f, activeAccount.monthlyServiceCharge, 0.01);
    }
}
