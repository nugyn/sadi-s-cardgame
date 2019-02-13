package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import model.GUIPlayer;
import view.GameWindow;
import view.ToolBarPanel;

public class StatusUpdateItemListener implements ItemListener
{

	private GameWindow gwView;
	public StatusUpdateItemListener(GameWindow gwView)
	{
		this.gwView = gwView;
	}
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		ToolBarPanel toolBarPanel = gwView.getToolBarPanel();
		gwView.setCurrentPlayer();
		GUIPlayer selectPlayer = gwView.getCurrentPlayer();
		toolBarPanel.selectionPlayerComboBox(selectPlayer);
		gwView.getCardPanel().getPlayerCardPanel().removeAll();
		gwView.getCardPanel().repaint();
		/*
		 * the current player is set again, when the user makes a item change in the combobox
		 * The cardPanels are reset, to make room for a new round of dealing to another player
		 * 
		 * 
		 */
	}

}
