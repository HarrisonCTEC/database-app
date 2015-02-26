package harrisonctec.view;

import harrisonctec.controller.DatabaseController;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

public class DatabasePanel extends JPanel
{
	private DatabaseController baseController;
	private SpringLayout baseLayout;
	
	/**
	 * The main layout
	 * @param baseController The main controller
	 */
	public DatabasePanel(DatabaseController baseController)
	{
		this.baseController = baseController;
		this.baseLayout = new SpringLayout();
		
		setupPanel();
	}

	/**
	 * Setup the panel parameters
	 */
	private void setupPanel()
	{
		this.setLayout(baseLayout);
	}
}
