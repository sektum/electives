package ua.epam.groys.electives.servlets;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import ua.epam.groys.electives.commands.AllCoursesCommand;
import ua.epam.groys.electives.commands.AuthorizeCommand;
import ua.epam.groys.electives.commands.AuthorizeFailedCommand;
import ua.epam.groys.electives.commands.Command;
import ua.epam.groys.electives.commands.ExitCommand;
import ua.epam.groys.electives.commands.LecturerStudentCommand;
import ua.epam.groys.electives.commands.StartLecturerCommand;
import ua.epam.groys.electives.commands.NoCommand;
import ua.epam.groys.electives.commands.SaveLecturerStudentCommand;
import ua.epam.groys.electives.commands.StartStudentCommand;
import ua.epam.groys.electives.commands.StudentCourseInfo;
import ua.epam.groys.electives.commands.SubscribeCourseCommand;
import ua.epam.groys.electives.commands.Unsubscrive;
import ua.epam.groys.electives.entities.AuthorizedUser;

/**
 * Helps controller get work with users commands.
 * 
 * @author Sektum
 * @version 1.0 20/06/18
 */
public class RequestHelper {
    /**
     * Describes parameter value for getting type of command. {@value #COMMAND}.
     */
    public static final String COMMAND = "command";
    private static Command exitCommand;
    /**
     * Basics commands for lecturer and student.
     */
    HashMap<String, Command> commands = new HashMap<String, Command>();
    /**
     * Commands for student.
     */
    HashMap<String, Command> studentCommands = new HashMap<String, Command>();
    /**
     * Commands for lecturer.
     */
    HashMap<String, Command> lecturerCommands = new HashMap<String, Command>();

    /**
     * Constructor initialization all commands.
     */
    private RequestHelper() {
	lecturerCommands.put(StartLecturerCommand.COMMAND_TYPE,
		new StartLecturerCommand());
	lecturerCommands.put(LecturerStudentCommand.TYPE_COMMAND,
		new LecturerStudentCommand());
	lecturerCommands.put(SaveLecturerStudentCommand.COMMAND_TYPE,
		new SaveLecturerStudentCommand());
	studentCommands.put(StartStudentCommand.COMMAND_TYPE,
		new StartStudentCommand());
	studentCommands.put(Unsubscrive.COMMAND_TYPE,
			new Unsubscrive());
	studentCommands.put(StudentCourseInfo.COMMAND_TYPE,
		new StudentCourseInfo());
	studentCommands.put(SubscribeCourseCommand.TYPE_COMMAND,
		new SubscribeCourseCommand());
	studentCommands.put(AllCoursesCommand.COMMAND_TYPE,
		new AllCoursesCommand());
	exitCommand = new ExitCommand();
	commands.put(ExitCommand.COMMAND_TYPE, exitCommand);
	commands.put(AuthorizeCommand.COMMAND_TYPE, new AuthorizeCommand());
	commands.put(AuthorizeFailedCommand.COMMAND_TYPE,
		new AuthorizeFailedCommand());
    }

    /**
     * Gets command object.
     * 
     * @param request
     *            the HttpServletRequest object that contains the client's
     *            request
     * @return command.
     */
    public Command getCommand(HttpServletRequest request) {
	String action = (String) request.getAttribute(COMMAND);
	if (action == null) {
	    action = request.getParameter(COMMAND);
	}
	Command command = commands.get(action);
	if (command != null) {
	    return command;
	}
	AuthorizedUser authorizedUser = (AuthorizedUser) request.getSession()
		.getAttribute(AuthorizeCommand.COMMAND_TYPE);
	if (authorizedUser != null) {
	    if (authorizedUser.isLecturer()) {
		command = lecturerCommands.get(action);
	    } else {
		command = studentCommands.get(action);
	    }

	} else {
	    command = exitCommand;
	}
	if (command == null) {
	    command = new NoCommand();
	}
	return command;
    }

    public static class SingletonHolder {
	public static final RequestHelper HOLDER_INSTANCE = new RequestHelper();
    }

    /**
     * Gets singleton of {@link RequestHelper}.
     * 
     * @return singleton instance.
     */
    public static RequestHelper getInstance() {
	return SingletonHolder.HOLDER_INSTANCE;
    }
}
