package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HandValueAceTest {

    private final Suit DUMMY_SUIT = Suit.HEARTS;

    @Test
    public void handWithOneAceTwoCardsIsValuedAt11() throws Exception {
        List<Card> cards = List.of(new Card(DUMMY_SUIT, "A"),
                                   new Card(DUMMY_SUIT, "5"));

        Hand hand = new Hand(cards);
        assertThat(hand.hasValueOf(11 + 5)).isTrue();
    }

    @Test
    public void handWithOneAceAndOtherCardsEqualTo11IsValuedAt1() throws Exception {
        List<Card> cards = List.of(new Card(DUMMY_SUIT, "A"),
                                   new Card(DUMMY_SUIT, "8"),
                                   new Card(DUMMY_SUIT, "3"));

        Hand hand = new Hand(cards);
        assertThat(hand.hasValueOf(1 + 8 + 3)).isTrue();
    }

    //Ace + 10 or Face Card (J/Q/K) should result in a total of 21


    @Test
    void testAceAndFaceShouldBe21() {
        List<Card> cards = List.of(new Card(DUMMY_SUIT, "A"),
                                   new Card(DUMMY_SUIT, "Q"));
        Hand hand = new Hand(cards);
        assertThat(hand.hasValueOf(21)).isTrue();
    }

    @Test
    void testAceAndValueCardShouldBe13() {
        List<Card> cards = List.of(new Card(DUMMY_SUIT, "A"),
                                   new Card(DUMMY_SUIT, "5"),
                                   new Card(DUMMY_SUIT, "7"));

        Hand hand = new Hand(cards);
        assertThat(hand.hasValueOf(13)).isTrue();
    }


}
