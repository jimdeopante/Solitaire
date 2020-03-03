package com.svi.solitaire.game;

import java.util.ArrayList;

public class Foundationzone {
	int id = 0;
	public static int zonenumber = 0;

	public Foundationzone(){
		id = ++zonenumber;
	}
			
	public int getZoneNumber(){
		  return id;
	}
	
	public ArrayList <Card> cardsOfZone = new ArrayList <>();
	
	public ArrayList<Card> getcardsOfZone() {
		return cardsOfZone;
	}
	
	public int zoneSize() {
		return cardsOfZone.size();
	}
	
	public boolean hasCards( ) {
		return cardsOfZone.size() > 0;
	}

	public Card getTopCard(){
		return cardsOfZone.get(0);
	}
	
	public Card removeCard(){
		return cardsOfZone.remove(0);
	}
	

	
}
