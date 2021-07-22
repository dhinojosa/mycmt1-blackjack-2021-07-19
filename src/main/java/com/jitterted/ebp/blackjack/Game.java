package com.jitterted.ebp.blackjack;

import org.fusesource.jansi.Ansi;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Game {

    private final Deck deck;
    private final Hand playerHand = new Hand();
    private final Hand dealerHand = new Hand();
    private int playerWalletAmount;
    private int currentBet;

    public static void main(String[] args) {
        Game game = new Game();
        displayWelcomeScreen();
        game.initialDeal();
        game.play();
        resetScreen();
    }

    private static void resetScreen() {
        System.out.println(ansi().reset());
    }

    private static void displayWelcomeScreen() {
        System.out.println(ansi()
                                   .bgBright(Ansi.Color.WHITE)
                                   .eraseScreen()
                                   .cursor(1, 1)
                                   .fgGreen().a("Welcome to")
                                   .fgRed().a(" Jitterted's")
                                   .fgBlack().a(" BlackJack"));
    }

    public Game() {
        deck = new Deck();
    }

    public void initialDeal() {
        dealRound();
        dealRound();
    }

    private void dealRound() {
        playerHand.dealCardFrom(deck);
        dealerHand.dealCardFrom(deck);
    }

    public void play() {
        boolean playerBusted = playerTurn();
        dealerTurn(playerBusted);
        displayFinalGameState();
        determineOutcome(playerBusted);
    }

    private boolean playerTurn() {
        // get Player's decision: hit until they stand, then they're done (or they go bust)
        boolean playerBusted = false;
        while (!playerBusted) {
            displayGameState();
            String playerChoice = inputFromPlayer().toLowerCase();
            if (playerStands(playerChoice)) {
                break;
            }
            if (playerHits(playerChoice)) {
                playerHand.dealCardFrom(deck);
                if (playerHand.isBust()) {
                    playerBusted = true;
                }
            } else {
                System.out.println("You need to [H]it or [S]tand");
            }
        }
        return playerBusted;
    }

    private void determineOutcome(boolean playerBusted) {
        if (playerBusted) {
            System.out.println("You Busted, so you lose.  💸");
            //playerLoses();
        } else if (dealerHand.isBust()) {
            System.out.println("Dealer went BUST, Player wins! Yay for you!! 💵");
            //playerWins();
        } else if (playerHand.beats(dealerHand)) {
            System.out.println("You beat the Dealer! 💵");
            //playerWins();
        } else if (playerHand.pushes(dealerHand)) {
            System.out.println("Push: You tie with the Dealer. 💸");
            //playerPushes()
        } else {
            System.out.println("You lost to the Dealer. 💸");
            //playerLoses()
        }
    }

    private void dealerTurn(boolean playerBusted) {
        // Dealer makes its choice automatically based on a simple heuristic (<=16, hit, 17>=stand)
        if (!playerBusted) {
            while (dealerHand.isBeneathThreshold()) {
                dealerHand.dealCardFrom(deck);
            }
        }
    }

    private boolean playerHits(String playerChoice) {
        return playerChoice.startsWith("h");
    }

    private boolean playerStands(String playerChoice) {
        return playerChoice.startsWith("s");
    }

    private String inputFromPlayer() {
        System.out.println("[H]it or [S]tand?");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void displayGameState() {
        dealerHand.displayFirstCard();
        displayBackOfCard();
        playerHand.displayHand();
    }

    private void displayBackOfCard() {
        System.out.print(
                ansi()
                        .cursorUp(7)
                        .cursorRight(12)
                        .a("┌─────────┐").cursorDown(1).cursorLeft(11)
                        .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                        .a("│░ J I T ░│").cursorDown(1).cursorLeft(11)
                        .a("│░ T E R ░│").cursorDown(1).cursorLeft(11)
                        .a("│░ T E D ░│").cursorDown(1).cursorLeft(11)
                        .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                        .a("└─────────┘"));
    }

    private void displayFinalGameState() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        System.out.println("Dealer has: ");
        dealerHand.displayHand();
        dealerHand.displayValue();
        playerHand.displayHand();
    }

    public int playerBalance() {
        return playerWalletAmount;
    }

    public void playerDeposits(int amount) {
        this.playerWalletAmount = amount;
    }

    public void playerBets(int amount) {
        this.playerWalletAmount -= amount;
        this.currentBet = amount;
    }

    public void playerWins() {
        processBet(2);
    }

    public void playerLoses() {
        processBet(0);
    }

    public void playerPushes() {
        processBet(1);
    }

    private void processBet(int i) {
        this.playerWalletAmount += (currentBet * i);
        this.currentBet = 0;
    }
}
