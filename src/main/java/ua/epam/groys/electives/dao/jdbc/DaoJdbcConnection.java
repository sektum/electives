package ua.epam.groys.electives.dao.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;

/**
 * Class present poolConnection to database. Also class has method for creation
 * of local connection to database for JUnit test.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class DaoJdbcConnection {
    private static final Logger LOGGER = Logger
	    .getLogger(DaoJdbcConnection.class);
    /**
     * Pool connection.
     */
    private static DataSource dataSource;
    /**
     * Flag of program JUnit testing.
     */
    private static boolean isTest;

    /**
     * Create poolConection if it not created and returns connection to
     * database.
     * 
     * @return connection to database.
     */
    protected static Connection getConnection() {
	Connection connection = null;
	if (!isTest) { // For JUnit tests
	    try {
		if (dataSource == null) {
		    InitialContext initContext = new InitialContext();
		    dataSource = (DataSource) initContext
			    .lookup("java:comp/env/jdbc/TestDB");
		}
		connection = dataSource.getConnection();
	    } catch (NamingException exception) {
		LOGGER.error(
			"getConnection failed get data source(Connection pool)",
			exception);
	    } catch (SQLException e) {
		LOGGER.error("getConnection failed get connection", e);
	    }
	} else {
	    connection = getConnectionForTest();
	}
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("getConnection connection=" + connection);
	}
	return connection;
    }

    /**
     * Only for JUnit tests. Set flag {@link isTest} to true.
     */
    public static void startTestJdbcDao() {
	isTest = true;
    }

    /**
     * Only for JUnit tests. Create one connection to database.
     * 
     * @return connection to database.
     */
    private static Connection getConnectionForTest() {
	Connection conn = null;
	// Need to make reconnection because connection closes in methods which
	// operate with database
	try (FileInputStream in = new FileInputStream(
		"./WebContent/META-INF/context.xml")) {
	    // new DataSource() have issue when try to create object of
	    // class for JUnit tests
	    /*
	     * Magic exceptions on empty place Properties props = new
	     * Properties(); props.load(in);
	     * Class.forName("com.mysql.jdbc.Driver"); conn =
	     * DriverManager.getConnection(props.getProperty("url"),
	     * props.getProperty("username"), props.getProperty("password"));
	     */
	    conn = DriverManager.getConnection(
		    "jdbc:mysql://localhost:3306/electives_database?"
			    + "useUnicode=true&characterEncoding=UTF-8",
		    "root", "1234");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return conn;
    }

    /**
     * Only for JUnit tests. Set flag {@link isTest} to false.
     */
    public static void stopTestJdbcDao() {
	isTest = false;
    }

    /**
     * Clean database table from all rows.
     * 
     * @param tableName
     *            name of database table.
     */
    public static void cleanTable(String tableName) {
	try (Statement query = (Statement) DaoJdbcConnection.getConnection()
		.createStatement()) {
	    query.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
	    query.executeUpdate("TRUNCATE " + tableName + ";");
	    query.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
	} catch (SQLException e) {
	    LOGGER.error("Truncate table " + tableName, e);
	}
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Truncate table " + tableName);
	}
    }
}
