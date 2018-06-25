package ua.epam.groys.electives.commands;

import ua.epam.groys.electives.maneger.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthorizeFailedCommand a command of controller which forms page when
 * authorization of user failed.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class AuthorizeFailedCommand implements Command {
    /**
     * Describes a type of command as AuthorizeFailedCommand.
     */
    public static final String COMMAND_TYPE = "errorLogIn";

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String page = null;
	request.setAttribute(COMMAND_TYPE, new Boolean(true));
	page = ConfigurationManager.getInstance().getProperty(
		ConfigurationManager.LOGIN_FAILED_PATH);
	return page;
    }

    @Override
    public String getCommandType() {
	return COMMAND_TYPE;
    }
}
