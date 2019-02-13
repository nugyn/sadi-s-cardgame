package view;



import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.GUIPlayer;
import model.interfaces.GameEngine;

import java.awt.BorderLayout;


public class GameWindow extends JFrame
	{
		private static final long serialVersionUID = -510635498812330566L;
		private GameEngine engine;
		private ToolBarPanel toolBarPanel;
		private StatusPanel statusPanel;
		private PlayerListPanel playerListPanel;
		private CardPanel cardPanel;
		private GUIPlayer currentPlayer;
		
		
		public GameWindow(GameEngine engine)
			{
				super("A2 s3429599");
				this.engine = engine;
				initialize(this);
			}
		public GameWindow()
			{
			}

		/**
		 * Initialize the contents of the frame.
		 */
		private void initialize(GameWindow gwView)
			{
				createPanels();
				
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(300, 300, 750, 600);
				
				setLayout(new BorderLayout());
			
				add(toolBarPanel, BorderLayout.NORTH);
				add(cardPanel, BorderLayout.CENTER);
				add(playerListPanel, BorderLayout.WEST);
				add(statusPanel, BorderLayout.SOUTH);
				
				engine.addGameEngineCallback(new GameEngineCallbackGUI(this));
				setVisible(true);
			}
		
		private void createPanels()
		{
			toolBarPanel = new ToolBarPanel(this, engine);
			statusPanel = new StatusPanel(this, engine);
			playerListPanel = new PlayerListPanel(this);
			cardPanel = new CardPanel(this, engine);
		}
				
		public String getPlayerName()
		{
			return JOptionPane.showInputDialog(this, "New player,\n Please enter your name:");
		}
		
		public int getPlayerPoints()
		{
			int points = Integer.parseInt(JOptionPane.showInputDialog(this, "Please enter your stake, minimum 100"));
			return points;
		}
		
		public int getPlayerBet()
		{
			int bet = Integer.parseInt(JOptionPane.showInputDialog(this, "Please place your bets!"));
			return bet;
		}
		
		public GUIPlayer setCurrentPlayer()
		{
			currentPlayer = (GUIPlayer) toolBarPanel.getPlayerComboBox().getSelectedItem();
			return currentPlayer;

		}
		public GUIPlayer getCurrentPlayer()
		{
			return currentPlayer;
		}
		
		public PlayerListPanel getPlayerListPanel()
		{
			return playerListPanel;
		}
		
		public StatusPanel getStatusPanel()
		{
			return statusPanel;
		}
		
		public ToolBarPanel getToolBarPanel()
		{
			return toolBarPanel;
		}
		
		public CardPanel getCardPanel()
		{
			return cardPanel;
		}

		public void setCurrentPlayer(GUIPlayer newPlayer)
		{
				this.currentPlayer = newPlayer;
		}
		
		

				
	}
				
			


