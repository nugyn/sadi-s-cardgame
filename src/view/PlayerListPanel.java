package view;


import java.awt.Dimension;
import java.util.Collection;

import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.interfaces.Player;

public class PlayerListPanel extends JPanel
	{
		
	private static final long serialVersionUID = -1568559947274357120L;
	private String playerInGame;
	JTextArea textArea;

		public PlayerListPanel(GameWindow view)
		{
			textArea = new JTextArea();
			textArea.setPreferredSize(new Dimension(200, 700));
			textArea.setEditable(false);
			textArea.setBorder(BorderFactory.createTitledBorder("Players:"));
			add(textArea);
		}
		
		public void updatePlayerListPanel(Collection<Player> playersList)
		{
			playerInGame =  "Players currently in game: \n";
			
			for(Player player: playersList) 
					{
					playerInGame += "\n" + player.toString();
					}
			textArea.setText(playerInGame);
		}	
}
		

