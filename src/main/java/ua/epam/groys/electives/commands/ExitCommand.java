package ua.epam.groys.electives.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.groys.electives.maneger.ConfigurationManager;

/**
 * ExitCommand a command of controller which makes user exit from the system.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class ExitCommand implements Command {
    /**
     * Describes a type of command as ExitCommand. Value of field
     * {@value #COMMAND_TYPE}.
     */
    public static final String COMMAND_TYPE = "exit";

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	request.getSession().removeAttribute(AuthorizeCommand.COMMAND_TYPE);
	String page = ConfigurationManager.getInstance().getProperty(
		ConfigurationManager.START_PAGE_PATH);
	return page;
    }

    @Override
    public String getCommandType() {
	return COMMAND_TYPE;
    }

}
