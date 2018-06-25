package ua.epam.groys.electives.dao;

import org.apache.log4j.Logger;

import ua.epam.groys.electives.dao.jdbc.DaoJdbcFactory;

/**
 * Abstract class which gives objects of all DAO.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public abstract class DaoFactory {
    private static final Logger LOGGER = Logger.getLogger(DaoFactory.class);
    /**
     * Singleton object of class {@link DaoFactory}
     */
    private static DaoFactory currDaoFactory;

    /**
     * Create object of lecture DAO class {@link LecturerDao}.
     * 
     * @return object of lecturer DAO.
     */
    public abstract LecturerDao getLecturerDao();

    /**
     * Create object of student DAO class {@link StudentDao}.
     * 
     * @return object of student DAO.
     */
    public abstract StudentDao getStudentDao();

    /**
     * Create object of course DAO class {@link CourseDao}.
     * 
     * @return object of course DAO.
     */
    public abstract CourseDao getCourseDao();

    /**
     * Create object of contract DAO class {@link ContractDao}.
     * 
     * @return object of contract DAO.
     */
    public abstract ContractDao getContractDao();

    /**
     * Create object of authorize user DAO class {@link AuthorizeUserDao}.
     * 
     * @return object of authorize user DAO.
     */
    public abstract AuthorizeUserDao getAuthorizeUser();

    /**
     * Gives singleton object of {@link DaoFactory} and if not exist create it.
     * 
     * @return object of {@link DaoFactory}
     */
    public static DaoFactory getDaoFactory() {
	if (currDaoFactory == null) {
	    currDaoFactory = new DaoJdbcFactory();
	}
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("DaoJdbcFactory created");
	}
	return currDaoFactory;
    }
}
