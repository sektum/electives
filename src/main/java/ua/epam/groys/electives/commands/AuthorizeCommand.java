package ua.epam.groys.electives.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.groys.electives.dao.DaoFactory;
import ua.epam.groys.electives.entities.AuthorizedUser;
import ua.epam.groys.electives.servlets.RequestHelper;

/**
 * AuthorizeCommand a command of controller which makes authorization of uses.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class AuthorizeCommand implements Command {
    /**
     * Describes a type of command as AuthorizeCommand. Value of field
     * {@value #COMMAND_TYPE}.
     */
    public static final String COMMAND_TYPE = "authorizedUser";
    /**
     * Describes a parameter value for getting user login Value of field
     * {@value #PARAM_NAME_LOGIN}.
     */
    private static final String PARAM_NAME_LOGIN = "userName";
    /**
     * Describes a parameter value for getting user password Value of field
     * {@value #PARAM_NAME_PASSWORD}.
     */
    private static final String PARAM_NAME_PASSWORD = "password";
    /**
     * Describes a parameter value for getting number of loop call of method
     * execute Value of field {@value #LOOP_PROTECTION}.
     */
    private static final String LOOP_PROTECTION = "loopProtection";

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String login = request.getParameter(PARAM_NAME_LOGIN);
	String pass = request.getParameter(PARAM_NAME_PASSWORD);
	// проверка логина и пароля
	DaoFactory daoFactory = DaoFactory.getDaoFactory();
	AuthorizedUser authorizedUser = daoFactory.getAuthorizeUser()
		.authorize(login, pass);
	request.removeAttribute(PARAM_NAME_LOGIN);
	request.removeAttribute(PARAM_NAME_PASSWORD);
	if (request.getAttribute(LOOP_PROTECTION) == null) {
	    request.setAttribute(LOOP_PROTECTION, new Boolean(true));
	} else {
	    return null;
	}
	if (authorizedUser != null) {
	    request.getSession().setAttribute(COMMAND_TYPE, authorizedUser);
	    if (authorizedUser.isLecturer()) {
		request.setAttribute(RequestHelper.COMMAND,
			StartLecturerCommand.COMMAND_TYPE);
	    } else {
		request.setAttribute(RequestHelper.COMMAND,
			StartStudentCommand.COMMAND_TYPE);
	    }
	} else {
	    request.setAttribute(RequestHelper.COMMAND,
		    AuthorizeFailedCommand.COMMAND_TYPE);
	}
	return RequestHelper.getInstance().getCommand(request)
		.execute(request, response);
    }

    @Override
    public String getCommandType() {
	return COMMAND_TYPE;
    }

}
