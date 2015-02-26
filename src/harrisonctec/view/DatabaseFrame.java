package harrisonctec.view;

import harrisonctec.controller.DatabaseController;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DatabaseFrame extends JFrame
{
	private DatabasePanel basePanel;
	private DatabaseController baseController;
	
	/**
	 * The main window
	 * @param baseController The main controller
	 */
	public DatabaseFrame(DatabaseController baseController)
	{
		setTitle("Database App");
		this.baseController = baseController;
		this.basePanel = new DatabasePanel(baseController);
		
		setupFrame();
	}
	
	/**
	 * Setup the window parameters
	 */
	private void setupFrame()
	{
		this.setSize(1024, 768);
		this.setContentPane(basePanel);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	/**
	 * Display a popup message with JOptionPane
	 * @param message
	 */
	public void showMessageDialog(String message)
	{
		JOptionPane.showMessageDialog(this, message);
	}
}
