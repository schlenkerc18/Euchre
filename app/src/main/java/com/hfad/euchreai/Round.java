package com.hfad.euchreai;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Schlenker18 on 12/4/2017.
 */

public class Round {
    public LinkedList<Cards> deck = new LinkedList<Cards>();
    public ArrayList<Cards> allCards;
    public ArrayList<Player> players;
    public Cards.SUIT trump;
    public int[] trickCount = new int[2];
    public int dealer;
    public Cards turnedUpCard;
    public Cards.SUIT[] callableSuits = new Cards.SUIT[3];

    public Round(ArrayList<Cards> allCardsIn, ArrayList<Player> playersIn, int dealerIn){
        allCards = allCardsIn;
        players = playersIn;
        shuffle();
        deal();
        turnedUpCard = deck.pop();
        dealer = dealerIn;
        trickCount[0] = 0;
        trickCount[1] = 0;
    }

    public void shuffle(){
        Random rand = new Random();
        boolean[] used = new boolean[24];
        for (int i = 0; i < 24; i++){
            used[i] = false;
        }

        for (int j = 0; j < 24; j++){
            int y = rand.nextInt(24);

            while (used[y]){
                y = rand.nextInt(24);
            }

            deck.push(allCards.get(y));
            used[y] = true;
        }
    }

    public void deal(){
        for (int i = 0; i < 5; i++){
            for(int j = 0; j < 4; j++){
                players.get(j).hand.add(draw());
            }
        }
    }

    public Cards draw(){
        return deck.pop();
    }
}
