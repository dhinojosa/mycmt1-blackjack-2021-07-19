package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class GameBetPayoffTest {

    @Test
    void testPlayerBalance() {
        Game game = new Game();
        int balance = game.playerBalance();
        assertThat(balance).isZero();
    }

    @Test
    void testPlayerWith100HasBalanceIs100() {
        Game game = new Game();
        game.playerDeposits(100);
        int balance = game.playerBalance();
        assertThat(balance).isEqualTo(100);
    }

    @Test
    void testPlayerWith150HasBalanceIs150() {
        Game game = new Game();
        game.playerDeposits(150);
        int balance = game.playerBalance();
        assertThat(balance).isEqualTo(150);
    }

    @Test
    void testPlayerWith150With50BetHas100Balance() {
        Game game = new Game();
        game.playerDeposits(150);
        game.playerBets(50);
        int balance = game.playerBalance();
        assertThat(balance).isEqualTo(100);
    }

    @Test
    void testPlayerWith100Bet50AndWinsThenBalanceIs150() {
        Game game = new Game();
        game.playerDeposits(100);
        game.playerBets(50);

        game.playerWins();

        assertThat(game.playerBalance()).isEqualTo(100 - 50 + 100);
    }

    @Test
    void testPlayerWith100Bet50AndWinsButAnotherWinHappens() {
        Game game = new Game();
        game.playerDeposits(100);
        game.playerBets(50);
        game.playerWins();

        //I made no bets
        game.playerWins();

        assertThat(game.playerBalance()).isEqualTo(100 - 50 + 100);
    }

    @Test
    void testPlayerWith100Bet50AndLosesThenBalanceIs50() {
        Game game = new Game();
        game.playerDeposits(100);
        game.playerBets(50);
        game.playerLoses();

        assertThat(game.playerBalance()).isEqualTo(100 - 50);
    }

    @Test
    void testPlayerWith100Bet50AndLosesButThenWinHappens() {
        Game game = new Game();
        game.playerDeposits(100);
        game.playerBets(50);
        game.playerLoses();

        //I made no bets
        game.playerWins();

        assertThat(game.playerBalance()).isEqualTo(100 - 50);
    }

    @Test
    void testPlayerWith100Bet50AndLosesButAnotherLosesHappens() {
        Game game = new Game();
        game.playerDeposits(100);
        game.playerBets(50);
        game.playerLoses();

        //I made no bets
        game.playerLoses();

        assertThat(game.playerBalance()).isEqualTo(100 - 50);
    }

    @Test
    void testPlayerWith100Bet50AndPushesTheyShouldHave100() {
        Game game = new Game();
        game.playerDeposits(100);
        game.playerBets(50);
        game.playerPushes();

        assertThat(game.playerBalance()).isEqualTo(100 - 50 + 50);
    }

    @Test
    void testPlayerWith100Bet50AndPushesButAfterWinHappens() {
        Game game = new Game();
        game.playerDeposits(100);
        game.playerBets(50);
        game.playerPushes();

        //I made no bets
        game.playerWins();

        assertThat(game.playerBalance()).isEqualTo(100 - 50 + 50);
    }
}
