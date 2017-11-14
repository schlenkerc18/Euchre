package com.hfad.euchreai;

/**
 * Created by Schlenker18 on 11/13/2017.
 */

public class Cards {

    public enum SUIT {
        CLUBS,
        SPADES,
        DIAMONDS,
        HEARTS
    }

    public final SUIT suit;
    public final int value;

    public Cards(SUIT suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public static SUIT getSameColorSuit(SUIT trump) {
        SUIT sameColor = SUIT.HEARTS;
        switch(trump) {
            case CLUBS:
                sameColor = SUIT.SPADES;
                break;
            case SPADES:
                sameColor = SUIT.CLUBS;
                break;
            case HEARTS:
                sameColor = SUIT.DIAMONDS;
                break;
            case DIAMONDS:
                sameColor = SUIT.HEARTS;
                break;
        }
        return sameColor;
    }

    public int biddingValue(SUIT trump) {
        SUIT sameColor = getSameColorSuit(trump);

        return 5;
    }

    public int cardValue(SUIT trump, SUIT lead) {
        SUIT sameColor = getSameColorSuit(trump);

        return 5;
    }
}
