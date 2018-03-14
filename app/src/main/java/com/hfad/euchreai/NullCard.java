package com.hfad.euchreai;

/**
 * Created by Schlenker18 on 3/11/2018.
 */

public class NullCard extends Cards {

    public NullCard() {
        super(Cards.SUIT.CLUBS, Integer.MIN_VALUE);
    }

    @Override
    public String toString() {
        return "";
    }

    public int cardValue (SUIT trump, SUIT leading) {
        return this.value;
    }

    public int biddingValue (SUIT trump) {
        return this.value;
    }

    public int cardValueNoLead (SUIT trump) {
        return this.value;
    }
}
