package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * This is class is the parent of all database classes. It contains the behavior
 * of making a connection and closing it.
 * 
 * @author 
 */
public abstract class Database{
    
       
    private static final String URL = "jdbc:mysql://localhost:3306/hotelDB";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";
    
    protected JTextArea textArea;
    protected Connection connection;
    private boolean connectionState = false;
    
    public Database(){
        connectionState = this.makeConnection();
    }
    
    /**
     * This method make a connection to the database.
     * 
     * @return true if connection is established, else false
     */
    private boolean makeConnection(){
    	try {
    		connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			textArea.append(e.getErrorCode()+"\n"+e.getMessage()+
					"\n"+e.getSQLState()+"\n");
			return false;
		}
        return true;
    }
    
    /**
     * DB connection accessor.
     * 
     * @return the connection object
     */
    public Connection getConnection(){
        return this.connection;
    }
    
    /**
     * DB connection state accessor.
     * 
     * @return the state of a connection
     */
    public boolean getConnectionState(){
        return this.connectionState;
    }
    
    /**
     * This method close the database connection.
     * 
     */
    public void closeConnection(){
        try {
            if(connection!=null){
                connection.close();
            }  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Can't close connection", 
                    "Error close", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * This abstract method is the action to implement on the database.
     * 
     * @param command query to execute
     */
    abstract public void execute(String command);

}