package ua.epam.groys.electives.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.groys.electives.maneger.ConfigurationManager;

/**
 * NoCommand a command of controller which forms page for unknown command.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class NoCommand implements Command {
    /**
     * Describes a type of command as NoCommand. Value of field
     * {@value #COMMAND_TYPE}.
     */
    public static final String COMMAND_TYPE = "no command";

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	/*
	 * в случае прямого обращения к контроллеру переадресация на страницу
	 * ввода логина
	 */
	String page = ConfigurationManager.getInstance().getProperty(
		ConfigurationManager.NO_SUCH_COMMAND);
	return page;
    }

    @Override
    public String getCommandType() {
	return COMMAND_TYPE;
    }
}
