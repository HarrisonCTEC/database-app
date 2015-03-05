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
		baseLayout = new SpringLayout();
		displayArea = new JTextArea(10,30);
		displayPane = new JScrollPane(displayArea);
		
		setupPanel();
		setupListeners();
		setupLayout();
	}

	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, queryButton, 14, SpringLayout.SOUTH, displayPane);
		baseLayout.putConstraint(SpringLayout.WEST, queryButton, 10, SpringLayout.WEST, this);
		
	}

	/**
	 * Setup the panel parameters
	 */
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		queryButton = new JButton("query");
		this.add(queryButton);
		this.add(displayPane);
	}
	
	private void setupDisplayPane()
	{
		displayArea.setWrapStyleWord(true);
		displayArea.setLineWrap(true);
		displayArea.setEditable(false);
	}
	
	private void setupListeners()
	{
		queryButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent click)
			{
				int results = baseController.getDataController().insertSample();
				displayArea.setText(displayArea.getText() + "Rows Affected: " + results);
				
			}
			
		}
		);
	}
}
