package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import application.WindowManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class DbConnections {
	/**
	 * methods opens url connection for database
	 */
	public static final String MYSQL_CONNECTION_URL = "jdbc:mysql://localhost/bdd_cabinet_medical";

	public static final String POSTGRES_CONNECTION_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";

	public static enum ConnectionType {
		MYSQL, POSTGRESQL;
	}

	/**
	 * exception returns this kind of exception SQLException
	 */
	public static Connection getConnection(ConnectionType connectionType) throws SQLException {
		try {
			if (connectionType == ConnectionType.MYSQL) {
				return DriverManager.getConnection(MYSQL_CONNECTION_URL, "root", "root1");
			} else {
				return DriverManager.getConnection(POSTGRES_CONNECTION_URL, "postgres", "123456");
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Connection to server lost");
			alert.setContentText("Ooops, there was an error while connecting to server!");

			alert.showAndWait();

			throw new SQLException(e.getMessage());
		}

	}

	/**
	 * exception returns this kind of exception SQLException
	 */
	public static void closeConnection(Connection conn) throws SQLException {
		conn.close();
	}

}
