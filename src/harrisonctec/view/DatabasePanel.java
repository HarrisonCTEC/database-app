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
		displayPane = new JScrollPane();
		
		setupPanel();
		setupListeners();
		setupLayout();
		setupDisplayPane();
	}

	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.WEST, queryButton, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, queryButton, -10, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, displayArea, 0, SpringLayout.SOUTH, displayPane);
		baseLayout.putConstraint(SpringLayout.WEST, displayArea, 0, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, displayArea, -10, SpringLayout.NORTH, queryButton);
		baseLayout.putConstraint(SpringLayout.EAST, displayArea, 0, SpringLayout.EAST, this);
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
		displayArea = new JTextArea(10,30);
		add(displayArea);
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
