package com.hfad.euchreai;

import java.util.Random;
import java.util.ArrayList;


/**
 * Created by Schlenker18 on 11/6/2017.
 */

public class VirtualPlayer {
    public String name;
    public ArrayList<Cards> hand;

    public VirtualPlayer(String name) {
        this.name = name;
        hand = new ArrayList<Cards>();
    }

    public Cards playCard(Cards card){
        boolean[] playableTrueFalse = this.getPlayableCards(leadingSuit, trump);
        ArrayList<Integer> playableCards = new ArrayList<Integer>();
        for (int i = 0 ; i < playableTrueFalse.length;i++)
        {
            boolean b = playableTrueFalse[i];
            if (b == true)
                playableCards.add(i);
        }
        Random random = new Random();
        int r = random.nextInt(playableCards.size());
        Cards c = hand.get(playableCards.get(r));
        this.removeCardFromHand(c);
        return c;
    }

    public boolean pickUp(Cards kitty){

        Random random = new Random();
        return random.nextBoolean();
    }

    public boolean pickUpAsDealer(Cards kitty){

        Random random = new Random();
        return random.nextBoolean();
    }

    public boolean call(Cards.SUIT invalid){

        Random random = new Random();
        return random.nextBoolean();
    }

    public boolean goAlone(Cards.SUIT trump){

        Random random = new Random();
        return random.nextBoolean();
    }
}
