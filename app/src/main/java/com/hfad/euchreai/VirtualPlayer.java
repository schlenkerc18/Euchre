package com.hfad.euchreai;

import java.util.Random;
import java.util.ArrayList;


/**
 * Created by Schlenker18 on 11/6/2017.
 */

public class VirtualPlayer {
    public String name;

    public VirtualPlayer(String name) {
        this.name = name;
    }

    public Cards playCard(Cards card){
        return card; //stub function
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
