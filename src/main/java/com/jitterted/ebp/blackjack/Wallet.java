package com.jitterted.ebp.blackjack;
//CMD_SHIFT_T = Toggle Prod & Test
//F2 = Go to red line
//OPT_ENTER = Fix
public class Wallet {

    private int balance;

    public boolean isEmpty() {
        return balance == 0;
    }

    public void addMoney(int amount) {
        if (amount < 0) throw new IllegalArgumentException();
        this.balance += amount;
    }

    public int getBalance() {
        return balance;
    }
}
