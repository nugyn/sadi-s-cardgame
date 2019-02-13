package view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GUIPlayer;
import model.interfaces.GameEngine;

public class StatusPanel extends JPanel
	{

	private static final long serialVersionUID = -1568559947274357120L;
	private GameWindow gwView;
	private JLabel playerName;
	private JLabel playerPoints;
	private JLabel playerScore;
	private GUIPlayer currentPlayer;
	public StatusPanel(GameWindow gwView, GameEngine engine)
		{
		
			this.gwView = gwView;
			makeStatusPanel();
		}
		
		public void makeStatusPanel()
		{
			setLayout(new GridLayout(1, 3));
			playerName = new JLabel("Player name: ", JLabel.LEFT);
			playerPoints = new JLabel("Player points: ", JLabel.LEFT);
			playerScore = new JLabel("Player score: ", JLabel.LEFT);
			
			add(playerName);
			add(playerPoints);
			add(playerScore);		
		}
		
		public void updateStatusPanel(int score)
		{
			currentPlayer = gwView.getCurrentPlayer();
			String strPlayerName = currentPlayer.getPlayerName();
			int strPlayerPoints = currentPlayer.getPoints();
			
			playerName.setText(strPlayerName);
			playerPoints.setText(strPlayerName  + "Points: " + strPlayerPoints);
			playerScore.setText(strPlayerName + "Score:" + Integer.toString(score));
		}
		//updates bottom info of current player in Play

	
		
	}
