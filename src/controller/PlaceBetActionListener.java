
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.GUIPlayer;
import view.GameWindow;

public class PlaceBetActionListener implements ActionListener
{
	
		private GameWindow gwView;
		private GameEngine engine;
		private GUIPlayer currentPlayer;
		

		public PlaceBetActionListener(GameEngine engine, GameWindow gwView) 
		{
			this.gwView = gwView;
			this.engine = engine;
		}
	@Override
		public void actionPerformed(ActionEvent e)
		{
			currentPlayer = gwView.getCurrentPlayer();
				
			if(currentPlayer == null)
				{
					JOptionPane.showMessageDialog(gwView, "Please select a player to place a bet!");
				}
			else
				{
					try
						{
							int bet = Integer.parseInt(JOptionPane.showInputDialog("Place your bets!"));
							if(bet > 0 && bet <= currentPlayer.getPoints()) 
							{
								
								engine.placeBet(currentPlayer, bet);
							}
							else if (bet > currentPlayer.getPoints())
								{
									JOptionPane.showMessageDialog(gwView, "That amount exceeds your current Points!");
								}
							else if( bet < 0)
								{
									JOptionPane.showMessageDialog(gwView, "You can't bet with less than 0!" );
									
								}
						}
					catch(Exception a)
						{
							JOptionPane.showMessageDialog(gwView, "Bet placed is an invalid bet...", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
				}
			
			
			gwView.getStatusPanel().updateStatusPanel(0);
			gwView.getPlayerListPanel().updatePlayerListPanel(engine.getAllPlayers());
		}

}