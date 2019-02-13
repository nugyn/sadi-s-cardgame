package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.GUIPlayer;
import view.GameEngineCallbackGUI;
import view.GameWindow;

public class DealCardActionListener implements ActionListener
{
	private GameEngineCallbackGUI gecbGUI;
	private GameWindow gwView;
	private GUIPlayer currentPlayer;
	private GameEngine engine;

	public DealCardActionListener(GameEngine engine, GameWindow gwView) 
	{
		this.gwView = gwView;
		this.engine = engine;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{

		currentPlayer = gwView.getCurrentPlayer();
		new Thread()
		{
			public void run()
			{
				engine.dealPlayer(currentPlayer, 1000);
				int alreadyPlayed = 0;


				for(Player player: engine.getAllPlayers())
				{
					if(player.getResult() > 0)
					{
						alreadyPlayed++;
					}
				}


				if(engine.getAllPlayers().size() == alreadyPlayed)
				{
					JOptionPane.showMessageDialog(gwView, "Dealing House...");
					engine.dealHouse(1000);
					try 
					{

						for(Player player: engine.getAllPlayers())
						{
							if(player.getPoints() < 0)
							{
								engine.removePlayer(player);
								gwView.getToolBarPanel().getPlayerComboBox().removeItem(player);
							}
						}
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(gwView, "Restart Game...");
						gwView.dispose();
						new GameWindow();
						gwView.repaint();
					}
					for(Player playerResults: engine.getAllPlayers()) 
					{
						playerResults.setResult(0);
					}

					int dialogButton = JOptionPane.YES_NO_OPTION;
					JOptionPane.showConfirmDialog(gwView, "Are you sure, you want to play another round?","Next Round?", dialogButton);
					if(dialogButton == JOptionPane.YES_OPTION)
					{
						gwView.getCardPanel().getPlayerCardPanel().removeAll();
						gwView.getCardPanel().getHouseCardPanel().removeAll();
						gwView.getCardPanel().repaint();

						for(Player playerResults: engine.getAllPlayers()) 
						{
							playerResults.resetBet();
						}

					}
					if(dialogButton == JOptionPane.NO_OPTION)
					{
						System.exit(0);
					}
				}

			}
		} .start();
	}
	public GameEngineCallbackGUI getGecbGUI()
	{
		return gecbGUI;
	}
	public void setGecbGUI(GameEngineCallbackGUI gecbGUI)
	{
		this.gecbGUI = gecbGUI;
	}
}




