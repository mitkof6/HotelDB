package database;

import java.sql.SQLException;

import javax.swing.JTextArea;

import com.mysql.jdbc.PreparedStatement;

/**
 * This class implements the behavior of executing a insert statement
 * 
 * @author
 *
 */
public class DatabaseInsert extends Database {

	public DatabaseInsert(JTextArea textArea, String command) {
		super(command);
		this.textArea = textArea;
		// execute(command);
	}

	@Override
	public boolean execute() {
		PreparedStatement ps = null;

		try {
			ps = (PreparedStatement) connection.prepareStatement(command);
			ps.executeUpdate();
			textArea.append(command+"\n");
			textArea.append("Record inserted\n");
			return true;
		} catch (SQLException e) {
			textArea.append(e.getErrorCode() + "\n" + e.getMessage() + "\n"
					+ e.getSQLState() + "\n");
			return false;
		} finally {

			try {
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					closeConnection();
				}

			} catch (SQLException ex) {
				textArea.append(ex.getErrorCode() + "\n" + ex.getMessage()
						+ "\n" + ex.getSQLState() + "\n");
			}
		}

	}

}
