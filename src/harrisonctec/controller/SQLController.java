package harrisonctec.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

import javax.swing.JOptionPane;

public class SQLController
{
	private String connectionString;
	private Connection databaseConnection;
	private DatabaseController baseController;
	
	private String query;
	
	/**
	 * Connect to and control a database
	 * @param baseController the main app controller
	 */
	public SQLController(DatabaseController baseController)
	{
		this.baseController = baseController;
		this.connectionString = "jdbc:mysql://localhost/performance_schema?user=root";
		checkDriver();
		setupConnection();
	}
	
	public void connectionStringBuilder(String pathToDBServer, String databaseName, String userName, String password)
	{
		connectionString = "jdbc:mysql://";
		connectionString += pathToDBServer;
		connectionString += "/" + databaseName;
		connectionString += "?=user" + "userName";
		connectionString += "&password=" + password;
	}

	/**
	 * Check and see if the MySQL dirver is installed
	 */
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
	
	/**
	 * Connect to the database
	 */
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
	
	/**
	 * Returns a display of the table 
	 * @return
	 */
	public String displayTables()
	{
		String tableNames = "";
		String query = "SHOW TABLES";
		
		try
		{
			Statement firstStatement = databaseConnection.createStatement();
			ResultSet answers = firstStatement.executeQuery(query);
			
			while(answers.next())
			{
				tableNames += answers.getString(1) + "\n";
			}
		}
		catch(SQLException currentError)
		{
			displayErrors(currentError);
		}
		
		return tableNames;
	}
	
	/**
	 * Inserts a pre-defined test string into the database
	 * @return rows affected
	 */
	public int insertSample()
	{
		int rowsAffected = -1;
		
		String query = "INSERT INTO `h_sample`.`cities` (`name`, `population`) VALUES ('Me',1)";
		
		
		try
		{
			Statement insertStatement = databaseConnection.createStatement();
			rowsAffected = insertStatement.executeUpdate(query);
			insertStatement.close();
		}
		catch(SQLException currentError)
		{
			
		}
		return rowsAffected;
		
	}
	
	/**
	 * Disconnect from the database
	 */
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
	
	/**
	 * Get metadata about the result set
	 * @return
	 */
	public String[] getMetaDataTitles()
	{
		String[] columns;
		String query = "SHOW TABLES";
		
		try
		{
			Statement firstStatement = databaseConnection.createStatement();
			ResultSet answers = firstStatement.executeQuery(query);
			ResultSetMetaData answerData = answers.getMetaData();
			
			columns = new String[answerData.getColumnCount()];
			
			for(int column = 0; column < answerData.getColumnCount(); column++)
			{
				columns[column] = answerData.getColumnName(column+1);
			}
			
			answers.close();
			firstStatement.close();
		}
		catch(SQLException currentException)
		{
			columns = new String[] {};
			displayErrors(currentException);
		}
		
		return columns;
	}
	
	public String[][] realResults()
	{
		String[][] results;
		String query = "SELECT * FROM `INNODB_SYS_COLUMNS";
			
		try
		{
			Statement firstStatement = databaseConnection.createStatement();
			ResultSet answers = firstStatement.executeQuery(query);
			int columnCount = answers.getMetaData().getColumnCount();
				
			answers.last();
			int numberOfRows = answers.getRow();
			answers.beforeFirst();
				
			results = new String [numberOfRows][1];
				
			while(answers.next())
			{
				for(int col = 0; col< columnCount; col++)
				{
					results[answers.getRow()-1][0] = answers.getString(1);
				}
			}
				
			answers.close();
			firstStatement.close();
		}
		catch(SQLException currentException)
		{
			results = new String[][] {{}};
			displayErrors(currentException);
		}
		
		return results;
	}
	
	private boolean checkQueryForDataViolation()
	{
		if(query.toUpperCase().contains(" DROP ")
		   || query.toUpperCase().contains(" TRUNCATE ")
		   || query.toUpperCase().contains(" SET ")
		   || query.toUpperCase().contains(" ALTER "))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String[][] selectQueryResults(String query)
	{
		String[][] results;
		this.query = query;
			
		try
		{
			if(checkQueryForDataViolation())
			{
				throw new SQLException("Data Violation!", "", Integer.MIN_VALUE);
			}
			
			Statement firstStatement = databaseConnection.createStatement();
			ResultSet answers = firstStatement.executeQuery(query);
			int columnCount = answers.getMetaData().getColumnCount();
				
			answers.last();
			int numberOfRows = answers.getRow();
			answers.beforeFirst();
				
			results = new String [numberOfRows][1];
				
			while(answers.next())
			{
				for(int col = 0; col< columnCount; col++)
				{
					results[answers.getRow()-1][0] = answers.getString(1);
				}
			}
				
			answers.close();
			firstStatement.close();
		}
		catch(SQLException currentException)
		{
			results = new String[][] {{}};
			displayErrors(currentException);
		}
		
		return results;
	}
	
	private boolean checkForStructureViolation()
	{
		if(query.toUpperCase().contains(" DATABASE "))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void dropStatement()
	{
		String results;
		try
		{
			if(checkForStructureViolation())
			{
				throw new SQLException("exception!", "", Integer.MIN_VALUE);
			}
			
			if(query.toUpperCase().contains(" INDEX "))
			{
				results = "The index was";
			}
			else
			{
				results = "The table was";
			}
			
			Statement dropStatement = databaseConnection.createStatement();
			int affected = dropStatement.executeUpdate(query);
			
			dropStatement.close();
			
			if(affected == 0)
			{
				results += "dropped";
			}
			JOptionPane.showMessageDialog(baseController.getAppFrame(), "?");
		}
	}
	
	/**
	 * Get info about the tables
	 * @return
	 */
	public String[][] testResults()
	{
		String[][] results;
		String query = "SHOW TABLES";
		
		try
		{
			Statement firstStatement = databaseConnection.createStatement();
			ResultSet answers = firstStatement.executeQuery(query);
			
			answers.last();
			int numberOfRows = answers.getRow();
			answers.beforeFirst();
			
			results = new String [numberOfRows][1];
			
			while(answers.next())
			{
				results[answers.getRow()-1][0] = answers.getString(1);
			}
			
			answers.close();
			firstStatement.close();
		}
		catch(SQLException currentException)
		{
			results = new String[][] {{}};
			displayErrors(currentException);
		}
		
		return results;
	}
	
	/**
	 * Send SQL related errors to the baseController to be displayed
	 * @param currentException The error to be displayed
	 */
	public void displayErrors(Exception currentException)
	{
		
		baseController.showMessageDialog("Exception: " + currentException.toString());
		if(currentException instanceof SQLException)
		{
			baseController.showMessageDialog("SQL State: " + ((SQLException) currentException).getSQLState());
			baseController.showMessageDialog("SQL Error Code: " + ((SQLException) currentException).getErrorCode());
		}
	}
	
	
}
