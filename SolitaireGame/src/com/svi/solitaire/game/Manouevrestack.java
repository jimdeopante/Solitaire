package com.svi.solitaire.game;

import java.util.ArrayList;

public class Manouevrestack {
	int id = 0;
	public static int stacknumber = 0;

	public Manouevrestack(){
		id = ++stacknumber;
	}
		

	public void addAt(int index, Card card){
		cardsOfStack.add(0, card);
	}
	
	public int getStackNumber(){
		  return id;
	}
	
	public ArrayList <Card> cardsOfStack = new ArrayList <>();
	
	public ArrayList<Card> getCardsOfStack() {
		return cardsOfStack;
	}
	
	public int stackSize() {
		return cardsOfStack.size();
	}
	
	public boolean hasCards( ) {
		return cardsOfStack.size() > 0;
	}

	public Card getTopCard(){
		
		return cardsOfStack.get(0);

	}
	
	
	public Card removeCard(){
		return cardsOfStack.remove(0);
	}

}