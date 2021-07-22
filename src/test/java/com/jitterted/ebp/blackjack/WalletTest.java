package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class WalletTest {

    @Test
    void testNewWalletIsEmpty() {
        Wallet wallet = new Wallet();
        boolean isEmpty = wallet.isEmpty();
        assertThat(isEmpty).isTrue();
    }

    @Test
    void testAddMoneyThenWalletIsNotEmpty() {
        Wallet wallet = new Wallet();
        wallet.addMoney(1);
        assertThat(wallet.isEmpty()).isFalse();
    }

    @Test
    void testNewWalletHasAZeroBalance() {
        Wallet wallet = new Wallet();
        int result = wallet.getBalance();
        assertThat(result).isZero();
    }

    @Test
    void testNewWalletAdd15HasBalanceOf15() {
        Wallet wallet = new Wallet();
        wallet.addMoney(15);
        int result = wallet.getBalance();
        assertThat(result).isEqualTo(15);
    }

    @Test
    void testNewWalletAdd15Then18HasBalanceOf33() {
        Wallet wallet = new Wallet();
        wallet.addMoney(15);
        wallet.addMoney(18);
        int result = wallet.getBalance();
        assertThat(result).isEqualTo(15 + 18);
    }

    @Test
    void testThatWeCannotPutNegativeIntoAddMoney() {
        Wallet wallet = new Wallet();
        assertThatThrownBy(() -> wallet.addMoney(-40))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testWalletWith12Bet8BalanceIs4() {
        Wallet wallet = new Wallet();
        wallet.addMoney(12);
        wallet.bet(8);
        assertThat(wallet.getBalance()).isEqualTo(4);
    }


    @Test
    void testWalletWith27Bet7AndBet9BalanceIs11() {
        Wallet wallet = new Wallet();
        wallet.addMoney(27);
        wallet.bet(7);
        wallet.bet(9);
        assertThat(wallet.getBalance()).isEqualTo(11);
    }


    @Test
    void testWalletWithMoneyWhenBetInFullIsEmpty() {
        Wallet wallet = new Wallet();
        wallet.addMoney(33);
        wallet.bet(33);
        assertThat(wallet.isEmpty()).isTrue();
    }

    @Test
    void testBetMoreThanBalanceThrowsAnException() {
        Wallet wallet = new Wallet();
        wallet.addMoney(73);
        assertThatThrownBy(() -> wallet.bet(74))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testThatWeCantBetANegativeAmount() {
        Wallet wallet = new Wallet();
        wallet.addMoney(73);
        assertThatThrownBy(() -> wallet.bet(-4))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
