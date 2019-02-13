package model;

import model.interfaces.Player;

public class SimplePlayer implements Player
	{

		private String playerName;
		private String playerID;
		private int points;
		private int bet;
		private int result;
	
		

		public SimplePlayer(String playerID, String playerName, int points)
			{
				this.playerID = playerID; //TO-DO Make sure that playerID is unique 
				this.playerName = playerName;
				this.points = points;
			}

		@Override
		public String getPlayerName()
			{
				return playerName;
			}

		@Override
		public void setPlayerName(String playerName)
			{
				this.playerName = playerName;
			}

		@Override
		public int getPoints()
			{
				return points;
			}

		@Override
		public void setPoints(int points)
			{
				this.points = points;

			}

		@Override
		public String getPlayerId()
			{
				return playerID;
			}

		@Override
		public boolean placeBet(int bet)
			{
				if (bet > 0)
					{
						this.bet = bet;
						return true;
					} 
				else
					{
						return false;
					}
			}

		@Override
		public int getBet()
			{
				return bet;
			}

		@Override
		public void resetBet()
			{
				bet = 0;

			}

		@Override
		public int getResult()
			{
				return result;
			}

		@Override
		public void setResult(int result)
			{
				this.result=result;
			}
		
		public String toString()
			{
				return String.format("%s %s %s %s %s %d", "id =", getPlayerId() + "," , "name:", getPlayerName() + "," ,"points=", getPoints());

			}
	}
