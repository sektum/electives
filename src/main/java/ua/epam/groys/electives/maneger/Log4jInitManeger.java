package ua.epam.groys.electives.maneger;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
/**
 * Class designed as configuration manger for web-pages.
 * @author Sektum
 * version 1.0 20/06/18
 */
public class Log4jInitManeger extends HttpServlet {
    private static final Logger LOGGER = Logger
	    .getLogger(Log4jInitManeger.class);
    private static final long serialVersionUID = 1L;

    /**
     * Initialization configuration for Log4J.
     */
    public void init() {
	String prefix = getServletContext().getRealPath("/");
	String file = getInitParameter("log4j-init-file");

	// if the log4j-init-file context parameter is not set, then no point in
	// trying
	if (file != null) {
	    DOMConfigurator.configure(prefix + file);
	    if (LOGGER.isDebugEnabled()) {
		LOGGER.debug("Log4J Logging started: " + prefix + file);
	    }
	} else {
	    LOGGER.error("Log4J Is not configured for your Application: "
		    + prefix + file);
	}
    }
}