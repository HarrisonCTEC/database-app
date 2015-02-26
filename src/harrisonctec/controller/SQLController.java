package harrisonctec.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class SQLController
{
	private String connectionString;
	private Connection databaseConnection;
	private DatabaseController baseController;
	
	public SQLController(DatabaseController baseController)
	{
		this.baseController = baseController;
		this.connectionString = "jdbc:mysql://localhost/h_sample?user=root";
		checkDriver();
		setupConnection();
	}

	private void checkDriver()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception currentException)
		{
			displayErrors(currentException);
			System.exit(1);
		}
		
	}
	
	private void setupConnection()
	{
		try
		{
			databaseConnection = DriverManager.getConnection(connectionString);
		}
		catch(SQLException currentException)
		{
			displayErrors(currentException);
		}
	}
	
	public void closeConnection()
	{
		try
		{
			databaseConnection.close();
		}
		catch (SQLException e)
		{
			displayErrors(e);
		}
	}
	
	public void displayErrors(Exception currentException)
	{
		
		baseController.showMessageDialog("Exception: " + currentException.getMessage());
		if(currentException instanceof SQLException)
		{
			baseController.showMessageDialog("SQL State: " + ((SQLException) currentException).getSQLState());
			baseController.showMessageDialog("SQL Error Code: " + ((SQLException) currentException).getErrorCode());
		}
	}
	
	
}
