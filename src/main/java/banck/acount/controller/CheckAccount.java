package banck.acount.controller;

public class CheckAccount extends Account {

    private float overdraft;

    public CheckAccount(float balance, float annualInterestRate) {
        super(balance, annualInterestRate);
        this.overdraft = 0;
    }

    @Override
    public void withdrawBalance(float amount) {
        if (amount <= this.balance) {
            super.withdrawBalance(amount);
        } else {
            this.overdraft += (amount - this.balance);
            this.balance = 0;
            this.numberOfWithdrawals++;
        }
    }

    @Override
    public void deposit(float amount) {
        if (this.overdraft > 0) {
            if (amount >= this.overdraft) {
                amount -= this.overdraft;
                this.overdraft = 0.0f;
            } else {
                this.overdraft -= amount;
                amount = 0;
            }
        }
        super.deposit(amount);
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Overdraft: " + this.overdraft);
    }
}
