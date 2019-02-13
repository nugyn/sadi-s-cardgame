package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;
import view.GameEngineCallbackImpl;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine
{
	private Collection<Player> listPlayer = new ArrayList<Player>();
	private List<GameEngineCallback> listGBC = new ArrayList<GameEngineCallback>();
	private Deque<PlayingCard> deck = new LinkedList<PlayingCard>();
	public GameEngineImpl()	
	{
		getShuffledDeck();
	}

	@Override
	public void dealPlayer(Player player, int delay)
	{
		int result = 0;
		boolean deal = true;
		while (deal == true)
		{
			PlayingCard dealtCard = deck.removeFirst();
			int currentResult = dealtCard.getScore();



			if (result + currentResult <= BUST_LEVEL)
			{
				for(GameEngineCallback obj: listGBC) 
				{
					obj.nextCard(player, dealtCard, this);
				}
			}
			else if (result + currentResult >= BUST_LEVEL)
			{
				for(GameEngineCallback obj: listGBC) 
				{
					player.setResult(result);
					obj.bustCard(player, dealtCard, this);
					obj.result(player, result, this);
					deal = false;
				}
			}
			try
			{
				Thread.sleep((long)delay);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			result += currentResult;
		}
	}


	@Override
	public void dealHouse(int delay)
	{
		int houseResult = 0;
		boolean deal = true;

		while (deal == true)
		{
			PlayingCard dealtCard = this.deck.removeFirst();
			int currentHouseResult = dealtCard.getScore();

			if (houseResult + currentHouseResult <= BUST_LEVEL)
			{
				for(GameEngineCallback obj: listGBC) 
				{
					obj.nextHouseCard(dealtCard, this);
				}
			}

			if (houseResult + currentHouseResult > BUST_LEVEL)
			{


				for(GameEngineCallback obj: listGBC) 
				{
					obj.houseBustCard(dealtCard, this);
					deal = false;
				}


				for (Player play : listPlayer)
				{

					int playerResults = play.getResult();

					if (playerResults > houseResult)
					{
						Player winPlayer = play;
						int winnings = winPlayer.getBet();
						int playerPoints = winPlayer.getPoints();
						playerPoints += winnings;
						winPlayer.setPoints(playerPoints);
						winPlayer.resetBet();
					}

					if (playerResults < houseResult)
					{
						Player losePlayer = play;
						int loss = losePlayer.getBet();
						int playerPoints = losePlayer.getPoints();
						playerPoints -= loss;
						losePlayer.setPoints(playerPoints);
						losePlayer.resetBet();
					}
					else
					{
						Player player = play;
						player.resetBet();
					}

				}
				for(GameEngineCallback obj: listGBC)
				{
					obj.houseResult(houseResult, this);
				}
			}

			houseResult += currentHouseResult;

			try
			{
				Thread.sleep((long)delay);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void addPlayer(Player player)
	{
		String playerId = player.getPlayerId();
		int i = 0;
		boolean go = false;
		if(listPlayer.isEmpty())
		{
			listPlayer.add(player);
			go = false;
		}
		else if(!listPlayer.isEmpty())
		{
			go = true;
		}
		if(go  == true)
		{
			for(Player a: listPlayer)
			{
				Player playerInList = a;
				String playerInListId = playerInList.getPlayerId();

				if(playerId.equals(playerInListId))
				{
					listPlayer.remove(i);
					listPlayer.add(player);
					i++;
					break;
				}
				else if(!playerId.equals(playerInListId))
				{
					listPlayer.add(player);
					i++;
					break;
				}
			}
		}	
	}

	@Override
	public Player getPlayer(String id)
	{
		Player returnPlayer = null;
		for (Player player : listPlayer)
		{
			if (player.getPlayerId().equals(id))
			{
				returnPlayer = player;
				break;
			}
		}
		return returnPlayer;
	}

	@Override
	public boolean removePlayer(Player player)
	{
		boolean returnVal = true;


		for (Player obj : listPlayer) {

			if(obj == player)
			{
				listPlayer.remove(player);
			}
			else 
			{
				returnVal = false;
			}

		}
		return returnVal;
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback)
	{
		listGBC.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback)
	{
		listGBC.remove(gameEngineCallback);
		return true;
	}

	@Override
	public Collection<Player> getAllPlayers()
	{
		Collection<Player> unModPlayer = Collections.unmodifiableCollection(listPlayer);
		return unModPlayer;
	}

	@Override
	public boolean placeBet(Player player, int bet)
	{
		boolean ret =
				false;
		if(bet < 0)
		{
			ret = false;
		}
		else if(bet >= 0)
		{
			if(player.getPoints() >= bet) 
			{
				player.placeBet(bet);
				ret = true;
			}
			else 
			{
				ret = false;
			}
		}
		return ret;
	}


	@Override
	public Deque<PlayingCard> getShuffledDeck()
	{
		deck.clear();

		for (int j = 0; j < 4; j++)
		{
			Suit suit = Suit.values()[j];
			for (int i = 0; i < 13; i++)
			{
				Value value = Value.values()[i];
				PlayingCardImpl card = new PlayingCardImpl(suit, value);
				deck.add(card);
			}
		}
		Collections.shuffle((List<?>) deck);
		Deque<PlayingCard> shuffledDeck = new LinkedList<PlayingCard>(deck);
		return shuffledDeck;

	}



}
