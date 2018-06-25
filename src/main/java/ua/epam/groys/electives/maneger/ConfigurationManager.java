package ua.epam.groys.electives.maneger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

/**
 * Class designed as configuration manger for web-pages.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class ConfigurationManager extends HttpServlet {
    private static final long serialVersionUID = 2370899765469068776L;
    private static final Logger LOGGER = Logger
	    .getLogger(ConfigurationManager.class);
    /**
     * Describes key which contains directory to Error page
     * {@value #ERROR_PAGE_PATH}.
     */
    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    /**
     * Describes key which contains directory to Login page
     * {@value #LOGIN_PAGE_PATH}.
     */
    public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    /**
     * Describes key which contains directory to Unknown Command page
     * {@value #NO_SUCH_COMMAND}.
     */
    public static final String NO_SUCH_COMMAND = "NO_SUCH_COMMAND";
    /**
     * Describes key which contains directory to Start page
     * {@value #START_PAGE_PATH}.
     */
    public static final String START_PAGE_PATH = "START_PAGE_PATH";
    /**
     * Describes key which contains directory to Login Failed page
     * {@value #LOGIN_FAILED_PATH}.
     */
    public static final String LOGIN_FAILED_PATH = "LOGIN_FAILED_PATH";
    /**
     * Describes key which contains directory to Lecturer Main page
     * {@value #LECTURER_MAIN_PAGE_PATH}.
     */
    public static final String LECTURER_MAIN_PAGE_PATH = "LECTURER_MAIN_PAGE_PATH";
    /**
     * Describes key which contains directory to Student Main page
     * {@value #STUDENT_MAIN_PAGE_PATH}.
     */
    public static final String STUDENT_MAIN_PAGE_PATH = "STUDENT_MAIN_PAGE_PATH";
    /**
     * Describes key which contains directory to Lecturer Student page
     * {@value #LECTURER_STUDENT_PAGE_PATH}.
     */
    public static final String LECTURER_STUDENT_PAGE_PATH = "LECTURER_STUDENT_PAGE_PATH";
    /**
     * Describes key which contains directory to Student Course page
     * {@value #STUDENT_COURSE_PAGE_PATH}.
     */
    public static final String STUDENT_COURSE_PAGE_PATH = "STUDENT_COURSE_PAGE_PATH";
    /**
     * Describes key which contains directory to All Courses page
     * {@value #STUDENT_ALL_COURSES_PAGE_PATH}.
     */
    public static final String STUDENT_ALL_COURSES_PAGE_PATH = "STUDENT_ALL_COURSES_PAGE_PATH";
    /**
     * Field contains directory to file with web-pages directories.
     */
    public static String path;
    /**
     * Singleton of {@link ConfigurationManager}
     */
    private static ConfigurationManager instance;
    /**
     * Properties of file with web-pages directories.
     */
    private Properties props;

    /**
     * Initialization directory to file.
     */
    public void init() {
	String prefix = getServletContext().getRealPath("/");
	String file = getInitParameter("config-file");
	if (file != null) {
	    path = prefix + file;
	} else {
	    LOGGER.error("Configuration file directory not find" + prefix
		    + file);
	}
    }

    /**
     * Gets singleton of {@link ConfigurationManager}.
     * 
     * @return singleton of {@link ConfigurationManager}.
     */
    public static ConfigurationManager getInstance() {
	if (instance == null) {
	    instance = new ConfigurationManager();
	    instance.props = new Properties();
	    try (FileInputStream inStream = new FileInputStream(new File(path))) {
		instance.props.load(inStream);
	    } catch (FileNotFoundException e) {
		LOGGER.error("File not exist", e);
	    } catch (IOException e) {
		LOGGER.error("Prooblem with eccess to file", e);
	    }
	}
	return instance;
    }

    /**
     * Gets directory of web-page from configuration file by key.
     * @param key of directory page.
     * @return directory of page.
     */
    public String getProperty(String key) {
	return (String) props.getProperty(key);
    }
}