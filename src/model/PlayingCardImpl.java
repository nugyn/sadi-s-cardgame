package model;


import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;

public class PlayingCardImpl implements PlayingCard
	{
		
		Suit suit;
		Value value;
		int score;
		public static final int DECK_SIZE = 52;
		
		
		public PlayingCardImpl(Suit suit, Value value)
		{
			this.suit = suit;
			this.value = value;
		}
		
		
	@Override
	public Suit getSuit()
		{
			return suit;
		}	

	@Override
	public Value getValue()
		{
			return value;
		}

	@Override
	public int getScore()
		{
			Value value = getValue();
			score = 0;
			switch(value)
			{
				case ACE: score = 1;
				break;
				case TWO: score = 2;
				break;
				case THREE: score = 3;
				break;
				case FOUR: score = 4;
				break;
				case FIVE: score = 5;
				break;
				case SIX: score = 6;
				break;
				case SEVEN: score = 7;
				break;
				case EIGHT: score = 8;
				break;
				case NINE: score = 9;
				break;
				case TEN: score = 10;
				break;
				case JACK: score = 10;
				break;
				case QUEEN: score = 10;
				break;
				case KING: score = 10;
				break;
			}
			return score;
			
		}

	@Override 
	public boolean equals(PlayingCard card)
	{
		return equals((Object) card);
	}
	
	@Override
	public boolean equals(Object obj)
		{
			if(this == obj)
				return true;
				
			if(obj == null) 
			return false;
				
			if(getClass() != obj.getClass())
				return false;
				
			PlayingCardImpl other = (PlayingCardImpl) obj;
			
			if(suit!= other.suit)
				return false;
			
			return true;
		}
	
	//public int hashCode()
	//{
		//final int prime = 31; 
		//int result = 1;
		//result = prime * result + ((suit == null) ? 0 : suit.hashCode());
	//return result;
	//}
	
	public String toString()
	{
		return String.format( "%s", " Suit: "
				+ getSuit() + "," + " Value: " + getValue() + "," + " Score: " + getScore());
	}
	
//	public int compareTo(PlayingCard card)
	//{
		//return value.ordinal() - card.getValue().ordinal();
	//}
	
	
}
