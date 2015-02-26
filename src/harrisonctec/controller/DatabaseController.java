package harrisonctec.controller;

import java.awt.Component;

import harrisonctec.view.DatabaseFrame;
import harrisonctec.view.DatabasePanel;

public class DatabaseController
{
	private DatabaseFrame baseFrame;
	private DatabasePanel basePanel;
	private SQLController baseSQL;
	
	/**
	 * The main project controller
	 */
	public DatabaseController()
	{
		baseFrame = new DatabaseFrame(this);
		baseSQL = new SQLController(this);
	}
	
	/**
	 * Open the main window
	 */
	public void start()
	{
		DatabasePanel basePanel = (DatabasePanel) baseFrame.getContentPane();
	}

	/**
	 * Returns the main window frame
	 * @return main window frame
	 */
	public DatabaseFrame getAppFrame()
	{
		return baseFrame;
	}

	/**
	 * Show a message in a popup window
	 * @param message The text to show
	 */
	public void showMessageDialog(String message)
	{
		baseFrame.showMessageDialog(message);
		
	}
}
