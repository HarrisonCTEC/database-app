package harrisonctec.controller;

import harrisonctec.view.DatabaseFrame;
import harrisonctec.view.DatabasePanel;

public class DatabaseController
{
	private DatabaseFrame baseFrame;
	private DatabasePanel basePanel;
	
	public DatabaseController()
	{
		baseFrame = new DatabaseFrame(this);
	}
	
	public void start()
	{
		DatabasePanel basePanel = (DatabasePanel) baseFrame.getContentPane();
	}
}
