package com.jitterted.ebp.blackjack;

//CMD_SHIFT_T = Toggle Prod & Test
//F2 = Go to red line
//OPT_ENTER = Fix
public class Wallet {

    private int balance;

    public boolean isEmpty() {
        return this.balance == 0;
    }

    public void addMoney(int amount) {
        ensureAmountIsPositive(amount);
        this.balance += amount;
    }

    private void ensureAmountIsPositive(int amount) {
        if (amount < 0) throw new IllegalArgumentException();
    }

    public int getBalance() {
        return this.balance;
    }

    public void bet(int amount) {
        ensureAmountIsPositive(amount);
        ensureAmountDoesntExceedBalance(amount);
        this.balance -= amount;
    }

    private void ensureAmountDoesntExceedBalance(int amount) {
        if (amount > this.balance) throw new IllegalArgumentException();
    }
}
