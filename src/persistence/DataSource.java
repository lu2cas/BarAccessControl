package persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataSource {
	/*private String host = "mysql.lu2cas.com.br";
	private String port = "3306";
	private String driver = "com.mysql.jdbc.Driver";
	private String database = "lu2cas02";
	private String user = "lu2cas02";
	private String password = "alpro3";*/

	private String host = "127.0.0.1";
	private String port = "3306";
	private String driver = "com.mysql.jdbc.Driver";
	private String database = "bar_access_control";
	private String user = "root";
	private String password = "";

	private static DataSource dataSource = null;

	private Connection connection;

	private DataSource() throws DataSourceException {
		try {
			String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database;
			Class.forName(this.driver);
			this.connection = DriverManager.getConnection(url, this.user, this.password);
		} catch (Exception e) {
			throw new DataSourceException(e.getMessage());
		}
	}

	public static DataSource getInstance() throws DataSourceException {
		if (dataSource == null) {
			dataSource = new DataSource();
		}
		return dataSource;
	}

	public Connection getConnection() {
		return this.connection;
	}

}