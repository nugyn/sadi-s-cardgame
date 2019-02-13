package model;

public class GUIPlayer extends SimplePlayer
	{
	
		public GUIPlayer(String playerID, String playerName, int points)
		{
			super(playerID, playerName, points);
		}

	@Override
	public String getPlayerName()
		{
			// TODO Auto-generated method stub
			return super.getPlayerName();
		}

	@Override
	public void setPlayerName(String playerName)
		{
			// TODO Auto-generated method stub
			super.setPlayerName(playerName);
		}

	@Override
	public int getPoints()
		{
			// TODO Auto-generated method stub
			return super.getPoints();
		}

	@Override
	public void setPoints(int points)
		{
			// TODO Auto-generated method stub
			super.setPoints(points);
		}

	@Override
	public String getPlayerId()
		{
			// TODO Auto-generated method stub
			return super.getPlayerId();
		}

	@Override
	public boolean placeBet(int bet)
		{
			// TODO Auto-generated method stub
			return super.placeBet(bet);
		}

	@Override
	public int getBet()
		{
			// TODO Auto-generated method stub
			return super.getBet();
		}

	@Override
	public void resetBet()
		{
			// TODO Auto-generated method stub
			super.resetBet();
		}

	@Override
	public int getResult()
		{
			// TODO Auto-generated method stub
			return super.getResult();
		}

	@Override
	public void setResult(int result)
		{
			// TODO Auto-generated method stub
			super.setResult(result);
		}

	@Override
	public String toString()
		{
			return this.getPlayerName() + "/" + this.getPoints() + "/" + this.getBet();
		}
		
}