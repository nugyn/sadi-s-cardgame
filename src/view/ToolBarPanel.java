package view;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import controller.AddPlayerActionListener;
import controller.DealCardActionListener;
import controller.PlaceBetActionListener;
import controller.StatusUpdateItemListener;
import model.GUIPlayer;
import model.interfaces.GameEngine;

public class ToolBarPanel extends JPanel
{
	private static final long serialVersionUID = 4268951943328073987L;
	private GameWindow gwView;
	private JButton btnAddPlayer, btnPlaceBet;
	private JComboBox<GUIPlayer> playerComboBox;

	private GameEngine engine;
	public ToolBarPanel(GameWindow gwView, GameEngine engine)
	{
		this.gwView = gwView;
		this.engine = engine;	
		makeToolBar();
		setVisible(true);
	}

	public void makeToolBar()
	{
		setPreferredSize(new Dimension(1000, 30));


		btnAddPlayer = new JButton("Add Player");
		btnPlaceBet = new JButton("Place Bet");
		JButton btnDealCards = new JButton("Deal Card");

		btnAddPlayer.setPreferredSize(new Dimension(120, 25));
		btnAddPlayer.addActionListener(new AddPlayerActionListener(engine, gwView));

		btnPlaceBet.setPreferredSize(new Dimension(120, 25));
		btnPlaceBet.addActionListener(new PlaceBetActionListener(engine, gwView));

		btnDealCards.setPreferredSize(new Dimension(120, 25));
		btnDealCards.addActionListener(new DealCardActionListener(engine, gwView));

		this.add(btnAddPlayer);
		this.add(btnPlaceBet);
		this.add(btnDealCards);

		add(Box.createHorizontalStrut(100));


		playerComboBox = new JComboBox<GUIPlayer>();
		this.playerComboBox.setPreferredSize(new Dimension(250, 25));
		this.add(playerComboBox);
		playerComboBox.addItemListener(new StatusUpdateItemListener(gwView));

	}

	public JComboBox<GUIPlayer> getPlayerComboBox()
	{
		return playerComboBox;
	}

	public void setPlayerComboBox(GUIPlayer newPlayer)
	{
		playerComboBox.addItem(newPlayer);
	}

	public void updatePlayerComboBox(GUIPlayer player)
	{
		playerComboBox.setSelectedItem(player);
	}

	public void selectionPlayerComboBox(GUIPlayer selectPlayer)
	{
		StatusPanel statusPanel = gwView.getStatusPanel();
		statusPanel.updateStatusPanel(0);
		//updates the StatusBar as well
	}





}
