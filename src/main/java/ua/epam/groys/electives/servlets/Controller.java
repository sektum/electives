package ua.epam.groys.electives.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.groys.electives.commands.AuthorizeFailedCommand;
import ua.epam.groys.electives.commands.Command;
import ua.epam.groys.electives.commands.ExitCommand;
import ua.epam.groys.electives.maneger.ConfigurationManager;

/**
 * Class describe controller.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class Controller extends HttpServlet implements Servlet {
    private static final long serialVersionUID = 1L;
    /**
     * Singleton of class {@link Controller}.
     */
    private RequestHelper requestHelper = RequestHelper.getInstance();

    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Method generated answers for command from user.
     * 
     * @param request
     *            the HttpServletRequest object that contains the client's
     *            request
     * @param response
     *            the HttpServletResponse object that contains the controller
     *            response
     * @throws ServletException
     *             throws when a controller encountered difficulty
     * @throws IOException
     *             throws when method can't find directories to web-pages
     */
    private void processRequest(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String page = null;
	Command command = null;
	try {
	    command = requestHelper.getCommand(request);
	    page = command.execute(request, response);
	} catch (Exception e) {
	    page = ConfigurationManager.getInstance().getProperty(
		    ConfigurationManager.ERROR_PAGE_PATH);
	}
	if ((command != null)
		&& ((command.getCommandType() == ExitCommand.COMMAND_TYPE) || (AuthorizeFailedCommand.COMMAND_TYPE
			.equals(request.getAttribute(RequestHelper.COMMAND))))) {
	    response.sendRedirect(page);
	} else {
	    RequestDispatcher dispatcher = getServletContext()
		    .getRequestDispatcher(page);
	    dispatcher.forward(request, response);
	}
    }
}
