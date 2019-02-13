package view;


import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.GUIPlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

public class CardPanel extends JPanel
	{
		
	private static final long serialVersionUID = -1568559947274357120L;
	private JLabel playerNamePanel;
	private JLabel houseNamePanel;
	private JLabel houseCardPanel;
	private JLabel playerCardPanel;
	private JPanel statusPanel;
	private JLabel cardInHand;
	private GameWindow gwView;
	private ImageIcon dealtCard;
	

		public CardPanel(GameWindow gwView, GameEngine engine)
		{
			this.gwView = gwView;
			makeCardPanel();
		}
		
		public void makeCardPanel()
		{
			setLayout(new GridLayout(2, 2));
			setBorder(BorderFactory.createEtchedBorder());
			
			houseNamePanel = new JLabel();
			houseNamePanel.setBorder(BorderFactory.createEtchedBorder());
			add(houseNamePanel);
			
			
			houseCardPanel = new JLabel();
			houseCardPanel.setBorder(BorderFactory.createEtchedBorder());
			add(houseCardPanel);
			
			
			playerNamePanel = new JLabel();
			playerNamePanel.setBorder(BorderFactory.createEtchedBorder());
			add(playerNamePanel);
			
			playerCardPanel = new JLabel();
			playerCardPanel.setBorder(BorderFactory.createEtchedBorder());
			add(playerCardPanel);
			
			
			houseCardPanel.setLayout(new FlowLayout());
			houseCardPanel.setBorder(BorderFactory.createEtchedBorder());

			
			playerCardPanel.setLayout(new FlowLayout());
			playerCardPanel.setBorder(BorderFactory.createEtchedBorder());

			//constructs Panel, 4 "Panels" JLabels within to represent hand, and player
			
		}
		
		public void updatePlayerPanel(boolean isPlayerPanel, boolean bust, int score)
		{
			if(isPlayerPanel == true)
			{
				ToolBarPanel toolBarPanel= gwView.getToolBarPanel();
				JComboBox<GUIPlayer> playerComboBox = toolBarPanel.getPlayerComboBox();
				Player player = (Player) playerComboBox.getSelectedItem();
				if(bust == true)
				{
				playerNamePanel.setText(player.getPlayerName() +" has Busted. \n" + "Score: " + Integer.toString(score));
				gwView.getStatusPanel().updateStatusPanel(score);
				}
				else
				{
					playerNamePanel.setText(player.getPlayerName() + "'s current Score is: " + Integer.toString(score));
					gwView.getStatusPanel().updateStatusPanel(score);
				}
			}
			else if(isPlayerPanel == false)
			{
				houseNamePanel.setText("House \n Score: " + score);
			}
			
		}   
		//updatePlayerPanel updates the information given to the user in realtime
		
		public void presentFinalResult(boolean isPlayer, int result)
		{
			if(isPlayer == true)
			{
			JOptionPane.showMessageDialog(gwView, "The final result for" + gwView.getCurrentPlayer().getPlayerName() + " is: " + result);
			}
			else 
			{
			JOptionPane.showMessageDialog(gwView, "The final result for the House" + " is: " + result);
			}
		}
		//present to the user in dialog box of the final result
		
		public void updateCardImage(PlayingCard cardInPlay, boolean player)
		{
			
				if(player == false)
					{
						cardInHand = new JLabel();
						ImageIcon imageCard = makeCardImage(cardInPlay);
						cardInHand.setIcon(imageCard);
						houseCardPanel.add(cardInHand);
					}
				else if(player == true)
				{
						cardInHand = new JLabel();
						ImageIcon imageCard = makeCardImage(cardInPlay);
						cardInHand.setIcon(imageCard);
						playerCardPanel.add(cardInHand);
				}
		}
		//places an image of respective cards on card components, to represent the hand
		
		
		private ImageIcon makeCardImage(PlayingCard card)
		{
	
			String getCard = "images/" + card.getValue() + "_" + card.getSuit() + ".png";
			
			
			ImageIcon secondCard = new ImageIcon(getCard);
			Image cardImage = secondCard.getImage();
			
			
			BufferedImage cardBuffImg = new BufferedImage(100, 150, BufferedImage.TYPE_INT_ARGB);
			
	
			Graphics cardModel = cardBuffImg.createGraphics();
			cardModel.drawImage(cardImage, 0, 0, 100, 150, null);
			dealtCard = new ImageIcon(cardBuffImg);
			return dealtCard;
		}
		//retrieve directory, through getting values of card
		//the card image icon is created with desired dimensions
		//drawn onto model of the card, and then is returned to be attached to the label
		
		public JLabel getPlayerNamePanel()
		{
			return playerNamePanel;
		}
		public JLabel getHouseNamePanel()
		{
			return houseNamePanel;
		}
		public JLabel getPlayerCardPanel()
		{
			return playerCardPanel;
		}
		public JLabel getHouseCardPanel()
		{
			return houseCardPanel;
		}
		public JPanel getStatusPanel()
		{
			return statusPanel;
		}
	}
