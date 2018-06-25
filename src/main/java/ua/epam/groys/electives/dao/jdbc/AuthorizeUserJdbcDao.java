package ua.epam.groys.electives.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import org.apache.log4j.Logger;

import ua.epam.groys.electives.dao.AuthorizeUserDao;
import ua.epam.groys.electives.entities.AuthorizedUser;
import ua.epam.groys.electives.entities.Lecturer;
import ua.epam.groys.electives.entities.Student;

/**
 * Class implements {@link AuthorizeUserDao}.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class AuthorizeUserJdbcDao implements AuthorizeUserDao {
    private static final Logger LOGGER = Logger
	    .getLogger(AuthorizeUserJdbcDao.class);
    /**
     * Contains information about organization of database table
     * {@link Lecturer}.
     */
    private Lecturer.LecturerTableInfo lecturerTableInfo = new Lecturer.LecturerTableInfo();
    /**
     * Contains information about organization of database table {@link Student}
     * .
     */
    private Student.StudentTableInfo studentTableInfo = new Student.StudentTableInfo();

    @Override
    public AuthorizedUser authorize(String name, String pwd) {
	AuthorizedUser authorizedUser = null;
	try {
	    authorizedUser = authorizeUser(name, pwd,
		    lecturerTableInfo.getTableName());
	    if (authorizedUser != null) {
		authorizedUser.setLecturer(true);
	    } else {
		authorizedUser = authorizeUser(name, pwd,
			studentTableInfo.getTableName());
		if (authorizedUser != null) {
		    authorizedUser.setLecturer(false);
		}
	    }
	} catch (Exception e1) {
	    // I catch exception in my log in method authorizeUser(..)
	}
	return authorizedUser;
    }

    /**
     * Makes authorization for user lecturer or student.
     * 
     * @param name
     *            user login.
     * @param pwd
     *            user password.
     * @param tableName
     *            name of table(student or lecturer).
     * @return object of {@link AuthorizedUser} or null if authorization failed.
     * @throws Exception
     *             when in database found more then one user with same
     *             {@link name} and {@link pwd}.
     */
    private AuthorizedUser authorizeUser(String name, String pwd,
	    String tableName) throws Exception {
	AuthorizedUser authorizedUser = new AuthorizedUser(name, pwd);
	Connection conn = DaoJdbcConnection.getConnection();
	try (Statement query = (Statement) conn.createStatement()) {
	    ResultSet rs = query.executeQuery("SELECT id FROM " + tableName
		    + " WHERE BINARY "
		    + lecturerTableInfo.getTableFieldsName()[1] + "='"
		    + authorizedUser.getFullName() + "' AND BINARY "
		    + lecturerTableInfo.getTableFieldsName()[2] + "='"
		    + authorizedUser.getPwd() + "'");
	    if (rs.next()) {
		authorizedUser.setId(rs.getInt(1));
	    } else {
		authorizedUser = null;
	    }
	    if (rs.next()) {
		LOGGER.error("Authorized user repeats name=" + name);
		throw new Exception();
	    }
	} catch (SQLException e) {
	    LOGGER.error("Authorized user name=" + name, e);
	} finally {
	    if (conn != null) {
		try {
		    conn.close();
		} catch (Exception ignore) {
		}
	    }
	}
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Authorized user name=" + name);
	}
	return authorizedUser;
    }
}
