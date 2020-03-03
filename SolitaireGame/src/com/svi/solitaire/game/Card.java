package com.svi.solitaire.game;


public class Card {
    
	
    final public static String []  SUITOFCARDS = {
    		"C","S","H","D"
    };
    
    final public static String[] RANKOFCARDS ={
    		"A","2","3","4","5","6","7","8","9","T","J","Q","K"
    };
    
    public int suits;
    public int ranks;
    
    final static int red = 0;
    final static int black = 1 ;
    
    public boolean faceUp = false;
    //card constructor
    public Card(int cardRank, int cardSuit){
        this.suits = cardSuit;
        this.ranks = cardRank;
    }
    	
    	public int getSuit(){
    		return suits;
    	}
    	
    	 public int getRanks(){
    		return ranks;
    	 }
    	 
    	 public int cardRankValue(){
    		 return(ranks);
    	 }
    	 
    	 public int cardSuitValue(){
    		 return(suits);
    	 }
    	 
    	 public int cardValue(){
    		 return(suits+1) + (4*ranks);
    	 }
    	 
    	 public int cardColor (){
    	    	if (getSuit() == 2 || getSuit() == 3 ){
    	    		return red; 
    	    	} else { 
    	    		return black;
    	    	  }
    	 }
    	
    	public void  setFaceUp (boolean faceUp){
    		this.faceUp = faceUp;
    	}
    	
    	public int isFaceUp (){
    		if (faceUp) {
    			return 1;
    			} else
    		return 0;
    	}
    	 
    	public String toString(){ 
    		if (faceUp){
    		return "[" + SUITOFCARDS[suits] + "-" + RANKOFCARDS[ranks] + "]";
    		}
    		else {
    			return "[X-X]";
    		}
    	
    	
    	}
     
    	}
		
