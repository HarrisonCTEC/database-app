package harrisonctec.controller;

import java.awt.Component;

import harrisonctec.view.DatabaseFrame;
import harrisonctec.view.DatabasePanel;

public class DatabaseController
{
	private DatabaseFrame baseFrame;
	private DatabasePanel basePanel;
	private SQLController baseSQL;
	
	public DatabaseController()
	{
		baseFrame = new DatabaseFrame(this);
		baseSQL = new SQLController(this);
	}
	
	public void start()
	{
		DatabasePanel basePanel = (DatabasePanel) baseFrame.getContentPane();
	}

	public DatabaseFrame getAppFrame()
	{
		return baseFrame;
	}

	public void showMessageDialog(String Message)
	{
		baseFrame.showMessageDialog(Message);
		
	}
}
