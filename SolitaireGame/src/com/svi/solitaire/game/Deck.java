package com.svi.solitaire.game;

import java.util.ArrayList;

public class Deck {
    ArrayList <Card> deckOfCards = new ArrayList <Card>();
    
    public Deck(){
    	//stores 52 cards in the ArrayList
    	   
    	for (int suits = 3; suits >= 0; suits--) {
    		for (int ranks = 12; ranks >= 0; ranks--) {
  			  deckOfCards.add(new Card(ranks, suits));
  			
    		}
    	}
    } 
}
