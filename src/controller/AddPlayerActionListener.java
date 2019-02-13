package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import model.GUIPlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameWindow;
import view.PlayerListPanel;
import view.ToolBarPanel;


public class AddPlayerActionListener implements ActionListener
	{
		private GameWindow gwView;
		private GUIPlayer newPlayer;
		private GameEngine engine;
		private PlayerListPanel plPanel;
		private ToolBarPanel tbPanel;
		private Collection<Player> playerList;
		

		public AddPlayerActionListener(GameEngine gameEngine, GameWindow gwView) 
		{
			this.gwView = gwView;
			this.engine = gameEngine;
		}
	@Override
		public void actionPerformed(ActionEvent e)
		{
			String playerName = gwView.getPlayerName();
			int points = gwView.getPlayerPoints();
			newPlayer = new GUIPlayer("1", playerName, points);
			//create the GUI version of the player
			
			engine.addPlayer(newPlayer);
			//model update
			
			gwView.setCurrentPlayer(newPlayer);
			plPanel = gwView.getPlayerListPanel();
			gwView.getStatusPanel().updateStatusPanel(0);
			/*updates the status panel, and places the currentPlayer as the one added
			 * the current player is the one currently being dealt to
			 * 
			 */
			
			playerList = engine.getAllPlayers();
			plPanel.updatePlayerListPanel(playerList);
			
			
			tbPanel = gwView.getToolBarPanel();
			tbPanel.setPlayerComboBox(newPlayer);
			tbPanel.selectionPlayerComboBox(newPlayer);
			tbPanel.updatePlayerComboBox(newPlayer);
			/*
			 * Update the COMBOBOX
			 * 
			 * 
			 */
		}
		

	}
