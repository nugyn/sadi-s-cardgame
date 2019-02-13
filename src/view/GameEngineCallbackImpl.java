package view;


import java.util.Collection;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

/**
 * 	
 * Skeleton/Partial example implementation of GameEngineCallback showing Java
 * logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
	{
		private final Logger logger = Logger.getLogger(this.getClass().getName());

		public GameEngineCallbackImpl()
			{
				// FINE shows dealing output, INFO only shows result
				for(Handler handler : logger.getHandlers())
							{
								logger.removeHandler(handler);
							}
				Handler systemOut = new ConsoleHandler();
				systemOut.setLevel(Level.ALL);
				logger.addHandler(systemOut);
				logger.setLevel(Level.ALL);
				
				logger.setUseParentHandlers(false);
				
				
			}

		@Override
		public void bustCard(Player player, PlayingCard card, GameEngine engine)
			{
				String log = "Card dealt to "  + player.toString() + card.toString() + "..." + "you BUSTED!";
				logger.log(Level.FINE, log);

			}

		@Override
		public void nextHouseCard(PlayingCard card, GameEngine engine)
			{	
				String log = "Card dealt to House..." + card.toString();
				logger.log(Level.FINE, log);
			}

		@Override
		public void houseBustCard(PlayingCard card, GameEngine engine)
			{
				String log = "Card dealt to House..." + card.toString() + "...HOUSE BUSTED!";
				logger.log(Level.FINE, log);
			}

		@Override
		public void houseResult(int result, GameEngine engine)
			{
				String log = "House" + ", final result =" + result;
				
				logger.log(Level.INFO, log);
				Collection<Player> players = engine.getAllPlayers();
				String log1 = "";
				
				for(Player obj:players) {
		
					log1 += "\n" + obj.toString();
				

				}
				logger.log(Level.INFO, log1);
				
				


			}

		@Override
		public void nextCard(Player player, PlayingCard card, GameEngine engine)
			{

				String log = "Card dealt to "  + player.toString() + "..." + card.toString();

				logger.log(Level.FINE, log);
			}

		@Override
		public void result(Player player, int result, GameEngine engine)
			{
				// final results logged at Level.INFO
				
				String log = player.getPlayerName() + ","+ "final result =" + result;
				logger.log(Level.INFO, log);
	
			}


		// TODO implement the rest of the GameEngineCallback interface
	}
