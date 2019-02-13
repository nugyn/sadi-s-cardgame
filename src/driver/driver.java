package driver;

import java.awt.EventQueue;

import model.GameEngineImpl;
import view.GameWindow;

public class driver
	{

		public static void main(String[] args)
			{
				EventQueue.invokeLater(new Runnable()
					{
						public void run()
							{
								try
									{
										GameEngineImpl engine = new GameEngineImpl();
										@SuppressWarnings("unused")
										GameWindow gWindow = new GameWindow(engine);
									} catch (Exception e)
									{
										e.printStackTrace();
									}
							}
					});

				

			}

	}
