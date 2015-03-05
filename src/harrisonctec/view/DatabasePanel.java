package harrisonctec.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import harrisonctec.controller.DatabaseController;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

public class DatabasePanel extends JPanel
{
	private DatabaseController baseController;
	private SpringLayout baseLayout;
	
	JButton queryButton;
	JTextArea textArea;
	JScrollPane displayPane;
	JTextArea displayArea;
	
	/**
	 * The main layout
	 * @param baseController The main controller
	 */
	public DatabasePanel(DatabaseController baseController)
	{
		this.baseController = baseController;
		this.baseLayout = new SpringLayout();
		
		queryButton = new JButton("query");
		displayArea = new JTextArea(10,30);
		displayPane = new JScrollPane(displayArea);
		
		setupPanel();
	}

	/**
	 * Setup the panel parameters
	 */
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.add(displayPane);
		this.add(queryButton);
	}
	
	private void setupListeners()
	{
		queryButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent click)
			{
				String results = baseController.getDataController().displayTables();
				displayArea.setText(results);
				
			}
			
		}
		);
	}
}
