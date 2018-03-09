package com.hfad.euchreai;

import java.util.Random;
import java.util.ArrayList;


/**
 * Created by Schlenker18 on 11/6/2017.
 */

public class VirtualPlayer extends SmartVirtualPlayer{
    public String name;
    public static ArrayList<Cards> hand;

    public VirtualPlayer(String name) {
        super(name);
    }

    public Cards playCard(Cards card){  // need to make static???????????????????????/
        boolean[] playableTrueFalse = this.getPlayableCards(Trick.leadingSuit, Trick.trump);
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

    public Cards discardDecider(Cards kitty) {
        Random random = new Random();
        int ind = random.nextInt(6);
        if (ind == 5) {
            return kitty;
        } else {
            return this.hand.get(ind);
        }
    }

    public Cards.SUIT trumpDecider(Cards.SUIT invalid) {
        Cards.SUIT ret = invalid;
        while (ret == invalid) {
            Random random = new Random();
            ret = Cards.SUIT.values()[random.nextInt(4)];
        }
        return ret;
    }

    public boolean goAlone(Cards.SUIT trump){
        Random random = new Random();
        return random.nextBoolean();
    }
}
