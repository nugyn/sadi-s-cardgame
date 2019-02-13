package view;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback
{

	private GameWindow gwView;

	public GameEngineCallbackGUI(GameWindow gwView)
	{
		this.gwView = gwView;
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine)
	{
		//Get the score the card
		//Update the player in GUI
		//	getscore of Card - then provide image of cards
		// 	update selected player score(result)
		//	
		//The model will be updated by the Listener
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				int dealtCardScore = 0;
				dealtCardScore += card.getScore();


				CardPanel cardPanel = gwView.getCardPanel();
				cardPanel.updatePlayerPanel(true, false, dealtCardScore);
				cardPanel.updateCardImage(card, true);
			}

		}
				);
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				int playerScore = player.getResult();
				CardPanel cardPanel = gwView.getCardPanel();
				cardPanel.updatePlayerPanel(true, true, playerScore);
				cardPanel.updateCardImage(card, true);
			}
		}
				);
	}

	@Override
	public void result(Player player, int result, GameEngine engine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				gwView.getCardPanel().updatePlayerPanel(true, true, result);
				gwView.getStatusPanel().updateStatusPanel(result);
				gwView.getPlayerListPanel().updatePlayerListPanel(engine.getAllPlayers());
				gwView.getToolBarPanel().updatePlayerComboBox(gwView.getCurrentPlayer());
				gwView.getCardPanel().presentFinalResult(true, result);
			}
		}
				);

	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				int dealtCardScore = 0;
				dealtCardScore += card.getScore();

				CardPanel cardPanel = gwView.getCardPanel();
				cardPanel.updatePlayerPanel(false, false, dealtCardScore);
				cardPanel.updateCardImage(card, false);
			}
		}
				);			
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				int dealtCardScore = 0;
				dealtCardScore += card.getScore();

				CardPanel cardPanel = gwView.getCardPanel();
				cardPanel.updatePlayerPanel(false, true, dealtCardScore);
				cardPanel.updateCardImage(card, false);
			}
		}
				);			
	}


	@Override
	public void houseResult(int result, GameEngine engine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				gwView.getCardPanel().updatePlayerPanel(false, true, result);
				gwView.getPlayerListPanel().updatePlayerListPanel(engine.getAllPlayers());
				gwView.getCardPanel().presentFinalResult(false, result);
				gwView.getCardPanel().getHouseCardPanel().removeAll();
			}
		}
				);

	}

}
