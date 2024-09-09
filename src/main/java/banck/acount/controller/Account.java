package banck.acount.controller;

public class Account {

    protected float balance;
    protected int numberOfDeposits;
    protected int numberOfWithdrawals;
    protected float annualInterestRate;
    protected float monthlyServiceCharge;

    public Account(float balance, float annualInterestRate) {
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.numberOfDeposits = 0;
        this.numberOfWithdrawals = 0;
        this.monthlyServiceCharge = 0;
    }

    public void deposit(float amount) {
        this.balance += amount;
        this.numberOfDeposits++;
    }

    public void withdrawBalance(float amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            this.numberOfWithdrawals++;
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void monthlyInterest() {
        float monthlyInterestRate = this.annualInterestRate / 12;
        float monthlyInterest = this.balance * monthlyInterestRate;
        this.balance += monthlyInterest;
    }

    public void monthlyProcess() {
        this.balance -= this.monthlyServiceCharge;
        this.monthlyInterest();
    }

    public void print() {
        System.out.println("Balance: " + this.balance);
        System.out.println("Number of deposits: " + this.numberOfDeposits);
        System.out.println("Number of withdrawals: " + this.numberOfWithdrawals);
        System.out.println("Annual interest rate: " + this.annualInterestRate);
        System.out.println("Monthly service charge: " + this.monthlyServiceCharge);
    }
}
