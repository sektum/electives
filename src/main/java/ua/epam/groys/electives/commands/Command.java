package ua.epam.groys.electives.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface of basics command methods.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public interface Command {
    /**
     * Method prepares web-page for controller.
     * 
     * @param request
     *            the HttpServletRequest object that contains the client's
     *            request
     * @param response
     *            the HttpServletResponse object that contains the controller
     *            response
     * @return local directory of web-page
     * @throws ServletException
     *             throws when a controller encountered difficulty
     * @throws IOException
     *             throws when method can't find file with directories of
     *             web-pages
     */
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException;

    /**
     * Return type of command.
     * 
     * @return type of command.
     */
    public String getCommandType();
}