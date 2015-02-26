package harrisonctec.view;

import harrisonctec.controller.DatabaseController;

import javax.swing.JFrame;

public class DatabaseFrame extends JFrame
{
	private DatabasePanel basePanel;
	private DatabaseController baseController;
	
	public DatabaseFrame(DatabaseController baseController)
	{
		setTitle("Database App");
		this.baseController = baseController;
		this.basePanel = new DatabasePanel(baseController);
		
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setSize(1024, 768);
		this.setContentPane(basePanel);
		this.setVisible(true);
		this.setResizable(false);
	}
}
