package ua.epam.groys.electives.dao.jdbc;

import org.apache.log4j.Logger;

import ua.epam.groys.electives.dao.AuthorizeUserDao;
import ua.epam.groys.electives.dao.ContractDao;
import ua.epam.groys.electives.dao.CourseDao;
import ua.epam.groys.electives.dao.DaoFactory;
import ua.epam.groys.electives.dao.LecturerDao;
import ua.epam.groys.electives.dao.StudentDao;

/**
 * Class extends {@link DaoFactory}
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class DaoJdbcFactory extends DaoFactory {
    private static final Logger LOGGER = Logger.getLogger(DaoJdbcFactory.class);

    @Override
    public LecturerDao getLecturerDao() {
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Get lecturer DAO");
	}
	return new LecturerJdbcDao();
    }

    @Override
    public StudentDao getStudentDao() {
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Get student DAO");
	}
	return new StudentJdbcDao();
    }

    @Override
    public CourseDao getCourseDao() {
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Get course DAO");
	}
	return new CourseJdbcDao();
    }

    @Override
    public ContractDao getContractDao() {
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Get contract DAO");
	}
	return new ContractJdbcDao();
    }

    @Override
    public AuthorizeUserDao getAuthorizeUser() {
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Get authorize user DAO");
	}
	return new AuthorizeUserJdbcDao();
    }
}