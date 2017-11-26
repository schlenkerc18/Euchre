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
        SUIT sameColor= getSameColorSuit(trump);

        if(this.suit == trump){
            switch(this.value){
                case 14: //trump ace
                    return 10;
                case 13: //trump king
                    return 8;
                case 12: //trump queen
                    return 7;
                case 11: //trump jack
                    return 12;
                case 10: //trump ten
                    return 5;
                case 9: //trump nine
                    return 4;
                default:
                    return -1;
            }
        } else if (this.suit == sameColor && this.value == 11){
                return 11;
          }
            switch(this.value){
                case 14: //random ace
                    return 6;
                case 13: //random king
                    return 2;
            }
            return 0;
    }

    public int cardValue(SUIT trump, SUIT lead) {
        SUIT sameColor= getSameColorSuit(trump);

        if (this.suit == trump){
            if(this.value == 11) //Jack of trump suit
                return 44;
            return this.value + 28;
        }
        if ((this.suit == sameColor) && (this.value == 11)) //Jack of same color
        {
            return 43;
        }
        if (this.suit == lead)
        {
            return this.value + 14;
        }
        return this.value;
    }

    public int cardValueNotLead(SUIT trump) {
        SUIT sameColor= getSameColorSuit(trump);

        if (this.suit == trump){
            if(this.value == 11) //Jack of trump suit
                return 30;
            return this.value + 14;
        }
        if ((this.suit == sameColor) && (this.value == 11)) //Jack of same color
        {
            return 29;
        }
        return this.value;
    }

    public boolean greater(Cards other, SUIT trump, SUIT lead) {
        
        return this.cardValue(trump, lead) > other.cardValue(trump, lead);
    }
}
